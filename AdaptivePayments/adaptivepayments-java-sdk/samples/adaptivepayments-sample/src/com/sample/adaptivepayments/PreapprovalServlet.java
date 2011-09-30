package com.sample.adaptivepayments;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.PreapprovalRequest;
import com.paypal.svcs.types.ap.PreapprovalResponse;
import com.paypal.svcs.types.common.AckCode;
import com.paypal.svcs.types.common.ErrorData;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * Servlet implementation class PreapprovalServlet
 */
public class PreapprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PreapprovalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext()
				.getRequestDispatcher("/Preapproval.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		PreapprovalRequest req = new PreapprovalRequest();
		req.setCancelUrl(request.getParameter("cancelURL"));
		req.setCurrencyCode(request.getParameter("currencyCode"));
		req.setReturnUrl(request.getParameter("returnURL"));
		req.setRequestEnvelope(requestEnvelope);
		req.setStartingDate(request.getParameter("startingDate"));
		AdaptivePaymentsService service = new AdaptivePaymentsService(this
				.getServletContext().getRealPath("/")
				+ "/WEB-INF/sdk_config.properties");
		try {
			PreapprovalResponse resp = service.preapproval(req);
			response.setContentType("text/html");
			response.getWriter().println(
					"Ack:" + resp.getResponseEnvelope().getAck());

			if (resp.getResponseEnvelope().getAck().equals(AckCode.SUCCESS)) {

				response.getWriter().println(
						"<br/>PreapprovalKey:" + resp.getPreapprovalKey());
				response.getWriter()
						.println(
								"<a href=https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-preapproval&preapprovalkey="
										+ resp.getPreapprovalKey()
										+ ">RedirectURL</a>");
			} else {
				Iterator iterator = resp.getError().iterator();
				while (iterator.hasNext()) {
					ErrorData error = (ErrorData) iterator.next();
					response.getWriter().println("<br/>" + error.getMessage());
				}
			}
			response.getWriter().println("<br/><a href='index.html'>Home</a>");
		} catch (SSLConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}