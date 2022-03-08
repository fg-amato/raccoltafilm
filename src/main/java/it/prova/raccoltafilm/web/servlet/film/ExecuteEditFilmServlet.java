package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteEditFilmServlet")
public class ExecuteEditFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String titoloParam = request.getParameter("titolo");
		String genereParam = request.getParameter("genere");
		String dataPubblicazioneParam = request.getParameter("dataPubblicazione");
		String minutiDurataParam = request.getParameter("minutiDurata");
		String registaIdParam = request.getParameter("regista.id");
		String idFilmParam = request.getParameter("idFilm");

		if (!NumberUtils.isCreatable(idFilmParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore. CATCH ID");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Film filmInstance = UtilityForm.createFilmFromParams(titoloParam, genereParam, minutiDurataParam,
				dataPubblicazioneParam, registaIdParam);
		filmInstance.setId(Long.parseLong(idFilmParam));

		// istanzio una lista vuota nel caso in cui la modifica vada male nella
		// validazione ho una lista di registi da riempire
		List<Regista> registiInLista = new ArrayList<Regista>();

		try {
			registiInLista = MyServiceFactory.getRegistaServiceInstance().listAllElements();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Qualcosa è andato storto");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		// se la validazione non risulta ok
		if (!UtilityForm.validateFilmBean(filmInstance)) {
			request.setAttribute("update_film_attr", filmInstance);
			request.setAttribute("registi_list_attribute", registiInLista);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/update.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getFilmServiceInstance().aggiorna(filmInstance);
			request.setAttribute("film_list_attribute", MyServiceFactory.getFilmServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}
