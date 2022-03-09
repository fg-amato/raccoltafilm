package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/utente/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// estraggo input
//		String nomeParam = request.getParameter("nome");
//		String cognomeParam = request.getParameter("cognome");
//		String usernameParam = request.getParameter("username");
//		String passwordParam = request.getParameter("password");
//		String[] ruoliParam = request.getParameterValues("ruoli");
//
//		// preparo un bean (che mi serve sia per tornare in pagina
//		// che per inserire) e faccio il binding dei parametri
//		Utente utenteInstance = new Utente(usernameParam, nomeParam, cognomeParam, passwordParam);
//		
//		List<Ruolo> ruoli =  new ArrayList<>();
//		
//		try {
//			ruoli =  MyServiceFactory.getRuoloServiceInstance().listAll();
//			 //se la validazione non risulta ok
//			if (!UtilityForm.validateUtenteBean(utenteInstance)) {
//				request.setAttribute("insert_utente_attr", utenteInstance);
//				// questo mi serve per la select di ruolo in pagina
//				request.setAttribute("ruoli_list_attribute",ruoli);
//				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
//				request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
//				return;
//			}
//			
//			
//			if (ruoliParam.length != 0) {
//				for (int i = 0; i < ruoliParam.length ; i++) {
//					if (NumberUtils.isCreatable(ruoliParam[i])) {
//						utenteInstance.addToRuoli(MyServiceFactory.getRuoloServiceInstance()
//								.caricaSingoloElemento(Long.parseLong(ruoliParam[i])));
//					}
//				}
//			}
//			
//			
//			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
//			// occupiamoci delle operazioni di business
//			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInstance);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
//			request.setAttribute("ruoli_list_attribute",ruoli);
//
//			request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
//			return;
//		}
//
//		// andiamo ai risultati
//		// uso il sendRedirect con parametro per evitare il problema del double save on
//		// refresh
//		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
