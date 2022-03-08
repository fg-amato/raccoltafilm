package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareEditFilmServlet
 */
@WebServlet("/PrepareEditFilmServlet")
public class PrepareEditFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idFilmParam = request.getParameter("idFilm");

		if (!NumberUtils.isCreatable(idFilmParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		try {
			//metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("update_film_attr", MyServiceFactory.getFilmServiceInstance().caricaSingoloElementoEager(Long.parseLong(idFilmParam)));
			// questo mi serve per la select di registi in pagina
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/film/update.jsp").forward(request, response);
	}

}
