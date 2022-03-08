package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteEditRegistaServlet")
public class ExecuteEditRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String nickNameParam = request.getParameter("nickName");
		String dataDiNascitaParam = request.getParameter("dataDiNascita");
		String sessoParam = request.getParameter("sesso");
		String idStringParam = request.getParameter("idRegista");
		
		if (!NumberUtils.isCreatable(idStringParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore. CATCH ID");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Regista registaInstance = UtilityForm.createRegistaFromParams(nomeParam, cognomeParam, nickNameParam,
				dataDiNascitaParam, sessoParam);
		Long idFilmDaAggiornare = Long.parseLong(idStringParam);
		registaInstance.setId(idFilmDaAggiornare);

		// se la validazione non risulta ok
		if (!UtilityForm.validateRegistaBean(registaInstance)) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getRegistaServiceInstance().aggiorna(registaInstance);
			request.setAttribute("registi_list_attribute", MyServiceFactory.getRegistaServiceInstance().listAllElements());
		
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");
	}

}
