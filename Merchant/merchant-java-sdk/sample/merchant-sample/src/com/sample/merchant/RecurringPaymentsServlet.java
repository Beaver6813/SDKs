package com.sample.merchant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import urn.ebay.api.PayPalAPI.BillOutstandingAmountReq;
import urn.ebay.api.PayPalAPI.BillOutstandingAmountRequestType;
import urn.ebay.api.PayPalAPI.BillOutstandingAmountResponseType;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileReq;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileRequestType;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileResponseType;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsReq;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsResponseType;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusReq;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusRequestType;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.UpdateRecurringPaymentsProfileReq;
import urn.ebay.api.PayPalAPI.UpdateRecurringPaymentsProfileRequestType;
import urn.ebay.api.PayPalAPI.UpdateRecurringPaymentsProfileResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.ActivationDetailsType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.AutoBillType;
import urn.ebay.apis.eBLBaseComponents.BillOutstandingAmountRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingPeriodDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingPeriodDetailsType_Update;
import urn.ebay.apis.eBLBaseComponents.BillingPeriodType;
import urn.ebay.apis.eBLBaseComponents.CreateRecurringPaymentsProfileRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.CreditCardDetailsType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.FailedPaymentActionType;
import urn.ebay.apis.eBLBaseComponents.ManageRecurringPaymentsProfileStatusRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.RecurringPaymentsProfileDetailsType;
import urn.ebay.apis.eBLBaseComponents.ScheduleDetailsType;
import urn.ebay.apis.eBLBaseComponents.StatusChangeActionType;
import urn.ebay.apis.eBLBaseComponents.UpdateRecurringPaymentsProfileRequestDetailsType;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;

/**
 * Servlet implementation class RecurringPaymentsServlet
 */
public class RecurringPaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RecurringPaymentsServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getRequestURI().contains("CreateRecurringPaymentsProfile"))
			getServletConfig()
					.getServletContext()
					.getRequestDispatcher(
							"/RecurringPayments/CreateRecurringPaymentsProfile.jsp")
					.forward(request, response);
		else if (request.getRequestURI().contains(
				"GetRecurringPaymentsProfileDetails"))
			getServletConfig()
					.getServletContext()
					.getRequestDispatcher(
							"/RecurringPayments/GetRecurringPaymentsProfileDetails.jsp")
					.forward(request, response);
		else if (request.getRequestURI().contains(
				"ManageRecurringPaymentsProfileStatus"))
			getServletConfig()
					.getServletContext()
					.getRequestDispatcher(
							"/RecurringPayments/ManageRecurringPaymentsProfileStatus.jsp")
					.forward(request, response);
		if (request.getRequestURI().contains("UpdateRecurringPaymentsProfile"))
			getServletConfig()
					.getServletContext()
					.getRequestDispatcher(
							"/RecurringPayments/UpdateRecurringPaymentsProfile.jsp")
					.forward(request, response);
		if (request.getRequestURI().contains("BillOutstandingAmount"))
			getServletConfig()
					.getServletContext()
					.getRequestDispatcher(
							"/RecurringPayments/BillOutstandingAmount.jsp")
					.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("url", request.getRequestURI());
		session.setAttribute(
				"relatedUrl",
				"<ul><li><a href='RP/CreateRecurringPaymentsProfile'>CreateRecurringPaymentsProfile</a></li><li><a href='RP/GetRecurringPaymentsProfileDetails'>GetRecurringPaymentsProfileDetails</a></li><li><a href='RP/ManageRecurringPaymentsProfileStatus'>ManageRecurringPaymentsProfileStatus</a></li><li><a href='RP/UpdateRecurringPaymentsProfile'>UpdateRecurringPaymentsProfile</a></li><li><a href='RP/BillOutstandingAmount'>BillOutstandingAmount</a></li></ul>");
		response.setContentType("text/html");
		CurrencyCodeType currency = CurrencyCodeType.fromValue("USD");
		try {
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(
					this.getServletContext().getRealPath("/")
							+ "/WEB-INF/sdk_config.properties");
			if (request.getRequestURI().contains(
					"CreateRecurringPaymentsProfile")) {

				CreateRecurringPaymentsProfileReq req = new CreateRecurringPaymentsProfileReq();
				CreateRecurringPaymentsProfileRequestType reqType = new CreateRecurringPaymentsProfileRequestType();
				// Populate Recurring Payments Profile Details
				RecurringPaymentsProfileDetailsType profileDetails = new RecurringPaymentsProfileDetailsType(
						request.getParameter("billingStartDate")
								+ "T00:00:00:000Z");
				if (request.getParameter("subscriberName") != "") {
					profileDetails.setSubscriberName(request
							.getParameter("subscriberName"));
				} else if (request.getParameter("shippingName") != "") {
					AddressType shippingAddr = new AddressType();
					shippingAddr.setName(request.getParameter("shippingName"));
					shippingAddr.setStreet1(request
							.getParameter("shippingStreet1"));
					shippingAddr.setStreet2(request
							.getParameter("shippingStreet2"));
					shippingAddr
							.setPhone(request.getParameter("shippingPhone"));
					shippingAddr.setCityName(request
							.getParameter("shippingCity"));
					shippingAddr.setStateOrProvince(request
							.getParameter("shippingState"));
					shippingAddr.setCountryName(request
							.getParameter("shippingCountry"));
					shippingAddr.setPostalCode(request
							.getParameter("shippingPostalCode"));
					profileDetails.setSubscriberShippingAddress(shippingAddr);
				}

				// Populate schedule details
				ScheduleDetailsType scheduleDetails = new ScheduleDetailsType();
				scheduleDetails.setDescription(request
						.getParameter("profileDescription"));
				if (request.getParameter("maxFailedPayments") != "") {
					scheduleDetails
							.setMaxFailedPayments(Integer.parseInt(request
									.getParameter("maxFailedPayments")));
				}
				if (request.getParameter("autoBillOutstandingAmount") != "") {
					scheduleDetails
							.setAutoBillOutstandingAmount(AutoBillType.fromValue(request
									.getParameter("autoBillOutstandingAmount")));
				}
				if (request.getParameter("initialAmount") != "") {
					ActivationDetailsType activationDetails = new ActivationDetailsType(
							new BasicAmountType(currency,
									request.getParameter("initialAmount")));
					if (request.getParameter("failedInitialAmountAction") != "") {
						activationDetails
								.setFailedInitialAmountAction(FailedPaymentActionType.fromValue(request
										.getParameter("failedInitialAmountAction")));
					}
					scheduleDetails.setActivationDetails(activationDetails);
				}
				if (request.getParameter("trialBillingAmount") != "") {
					int frequency = Integer.parseInt(request
							.getParameter("trialBillingFrequency"));
					BasicAmountType paymentAmount = new BasicAmountType(
							currency,
							request.getParameter("trialBillingAmount"));
					BillingPeriodType period = BillingPeriodType
							.fromValue(request
									.getParameter("trialBillingPeriod"));
					int numCycles = Integer.parseInt(request
							.getParameter("trialBillingCycles"));

					BillingPeriodDetailsType trialPeriod = new BillingPeriodDetailsType(
							period, frequency, paymentAmount);
					trialPeriod.setTotalBillingCycles(numCycles);
					if (request.getParameter("trialShippingAmount") != "") {
						trialPeriod.setShippingAmount(new BasicAmountType(
								currency, request
										.getParameter("trialShippingAmount")));
					}
					if (request.getParameter("trialTaxAmount") != "") {
						trialPeriod.setTaxAmount(new BasicAmountType(currency,
								request.getParameter("trialTaxAmount")));
					}

					scheduleDetails.setTrialPeriod(trialPeriod);
				}
				if (request.getParameter("billingAmount") != "") {
					int frequency = Integer.parseInt(request
							.getParameter("billingFrequency"));
					BasicAmountType paymentAmount = new BasicAmountType(
							currency, request.getParameter("billingAmount"));
					BillingPeriodType period = BillingPeriodType
							.fromValue(request.getParameter("billingPeriod"));

					int numCycles = Integer.parseInt(request
							.getParameter("totalBillingCycles"));

					BillingPeriodDetailsType paymentPeriod = new BillingPeriodDetailsType(
							period, frequency, paymentAmount);
					paymentPeriod.setTotalBillingCycles(numCycles);
					if (request.getParameter("trialShippingAmount") != "") {
						paymentPeriod.setShippingAmount(new BasicAmountType(
								currency, request
										.getParameter("trialShippingAmount")));
					}
					if (request.getParameter("trialTaxAmount") != "") {
						paymentPeriod.setTaxAmount(new BasicAmountType(
								currency, request
										.getParameter("trialTaxAmount")));
					}
					scheduleDetails.setPaymentPeriod(paymentPeriod);
				}

				CreateRecurringPaymentsProfileRequestDetailsType reqDetails = new CreateRecurringPaymentsProfileRequestDetailsType(
						profileDetails, scheduleDetails);
				// Set EC-Token or Credit card details
				if (request.getParameter("token") != "")
					reqDetails.setToken(request.getParameter("token"));
				else if (request.getParameter("creditCardNumber") != "") {
					CreditCardDetailsType cc = new CreditCardDetailsType();
					cc.setCreditCardNumber(request
							.getParameter("creditCardNumber"));
					cc.setCVV2(request.getParameter("cvv"));
					cc.setExpMonth(Integer.parseInt(request
							.getParameter("expMonth")));
					cc.setExpYear(Integer.parseInt(request
							.getParameter("expYear")));
					reqDetails.setCreditCard(cc);
				}

				reqType.setCreateRecurringPaymentsProfileRequestDetails(reqDetails);

				req.setCreateRecurringPaymentsProfileRequest(reqType);
				CreateRecurringPaymentsProfileResponseType resp = service
						.createRecurringPaymentsProfile(req);
				if (resp != null) {
					session.setAttribute("lastReq", service.getLastRequest());
					session.setAttribute("lastResp", service.getLastResponse());
					if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						map.put("Profile ID",
								resp.getCreateRecurringPaymentsProfileResponseDetails()
										.getProfileID());
						map.put("Transaction ID",
								resp.getCreateRecurringPaymentsProfileResponseDetails()
										.getTransactionID());
						map.put("Profile Status",
								resp.getCreateRecurringPaymentsProfileResponseDetails()
										.getProfileStatus());
						session.setAttribute("map", map);
						response.sendRedirect("/merchant-sample/Response.jsp");
					} else {
						session.setAttribute("Error", resp.getErrors());
						response.sendRedirect("/merchant-sample/Error.jsp");
					}
				}

			} else if (request.getRequestURI().contains(
					"GetRecurringPaymentsProfileDetails")) {
				GetRecurringPaymentsProfileDetailsReq req = new GetRecurringPaymentsProfileDetailsReq();
				GetRecurringPaymentsProfileDetailsRequestType reqType = new GetRecurringPaymentsProfileDetailsRequestType(
						request.getParameter("profileID"));
				req.setGetRecurringPaymentsProfileDetailsRequest(reqType);
				GetRecurringPaymentsProfileDetailsResponseType resp = service
						.getRecurringPaymentsProfileDetails(req);
				if (resp != null) {
					session.setAttribute("lastReq", service.getLastRequest());
					session.setAttribute("lastResp", service.getLastResponse());
					if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						map.put("Profile ID",
								resp.getGetRecurringPaymentsProfileDetailsResponseDetails()
										.getProfileID());
						map.put("Profile Status",
								resp.getGetRecurringPaymentsProfileDetailsResponseDetails()
										.getProfileStatus());
						session.setAttribute("map", map);
						response.sendRedirect("/merchant-sample/Response.jsp");
					} else {
						session.setAttribute("Error", resp.getErrors());
						response.sendRedirect("/merchant-sample/Error.jsp");
					}
				}

			} else if (request.getRequestURI().contains(
					"ManageRecurringPaymentsProfileStatus")) {
				ManageRecurringPaymentsProfileStatusReq req = new ManageRecurringPaymentsProfileStatusReq();
				ManageRecurringPaymentsProfileStatusRequestType reqType = new ManageRecurringPaymentsProfileStatusRequestType();
				ManageRecurringPaymentsProfileStatusRequestDetailsType reqDetails = new ManageRecurringPaymentsProfileStatusRequestDetailsType(
						request.getParameter("profileID"),
						StatusChangeActionType.fromValue(request
								.getParameter("action")));
				reqDetails.setNote("change");
				reqType.setManageRecurringPaymentsProfileStatusRequestDetails(reqDetails);
				req.setManageRecurringPaymentsProfileStatusRequest(reqType);
				ManageRecurringPaymentsProfileStatusResponseType resp = service
						.manageRecurringPaymentsProfileStatus(req);
				if (resp != null) {
					session.setAttribute("lastReq", service.getLastRequest());
					session.setAttribute("lastResp", service.getLastResponse());
					if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						map.put("Profile ID",
								resp.getManageRecurringPaymentsProfileStatusResponseDetails()
										.getProfileID());
						session.setAttribute("map", map);
						response.sendRedirect("/merchant-sample/Response.jsp");
					} else {
						session.setAttribute("Error", resp.getErrors());
						response.sendRedirect("/merchant-sample/Error.jsp");
					}
				}
			} else if (request.getRequestURI().contains(
					"UpdateRecurringPaymentsProfile")) {
				UpdateRecurringPaymentsProfileReq req = new UpdateRecurringPaymentsProfileReq();
				UpdateRecurringPaymentsProfileRequestType reqType = new UpdateRecurringPaymentsProfileRequestType();
				UpdateRecurringPaymentsProfileRequestDetailsType reqDetails = new UpdateRecurringPaymentsProfileRequestDetailsType(
						request.getParameter("profileID"));
				reqDetails.setNote("change");
				if (request.getParameter("creditCardNumber") != "") {
					CreditCardDetailsType cc = new CreditCardDetailsType();
					cc.setCreditCardNumber(request
							.getParameter("creditCardNumber"));
					cc.setCVV2(request.getParameter("cvv"));
					cc.setExpMonth(Integer.parseInt(request
							.getParameter("expMonth")));
					cc.setExpYear(Integer.parseInt(request
							.getParameter("expYear")));
					reqDetails.setCreditCard(cc);
				}
				reqDetails.setBillingStartDate(request
						.getParameter("billingStartDate") + "T00:00:00:000Z");

				if (request.getParameter("trialBillingAmount") != "") {
					int frequency = Integer.parseInt(request
							.getParameter("trialBillingFrequency"));
					BasicAmountType paymentAmount = new BasicAmountType(
							currency,
							request.getParameter("trialBillingAmount"));
					BillingPeriodType period = BillingPeriodType
							.fromValue(request
									.getParameter("trialBillingPeriod"));
					int numCycles = Integer.parseInt(request
							.getParameter("trialBillingCycles"));

					BillingPeriodDetailsType_Update trialPeriod = new BillingPeriodDetailsType_Update();
					trialPeriod.setBillingPeriod(period);
					trialPeriod.setBillingFrequency(frequency);
					trialPeriod.setAmount(paymentAmount);
					trialPeriod.setTotalBillingCycles(numCycles);
					if (request.getParameter("trialShippingAmount") != "") {
						trialPeriod.setShippingAmount(new BasicAmountType(
								currency, request
										.getParameter("trialShippingAmount")));
					}
					if (request.getParameter("trialTaxAmount") != "") {
						trialPeriod.setTaxAmount(new BasicAmountType(currency,
								request.getParameter("trialTaxAmount")));
					}

					reqDetails.setTrialPeriod(trialPeriod);
				}
				if (request.getParameter("billingAmount") != "") {
					int frequency = Integer.parseInt(request
							.getParameter("billingFrequency"));
					BasicAmountType paymentAmount = new BasicAmountType(
							currency, request.getParameter("billingAmount"));
					BillingPeriodType period = BillingPeriodType
							.fromValue(request.getParameter("billingPeriod"));

					int numCycles = Integer.parseInt(request
							.getParameter("totalBillingCycles"));

					BillingPeriodDetailsType_Update paymentPeriod = new BillingPeriodDetailsType_Update();
					paymentPeriod.setBillingPeriod(period);
					paymentPeriod.setBillingFrequency(frequency);
					paymentPeriod.setAmount(paymentAmount);
					paymentPeriod.setTotalBillingCycles(numCycles);
					if (request.getParameter("trialShippingAmount") != "") {
						paymentPeriod.setShippingAmount(new BasicAmountType(
								currency, request
										.getParameter("trialShippingAmount")));
					}
					if (request.getParameter("trialTaxAmount") != "") {
						paymentPeriod.setTaxAmount(new BasicAmountType(
								currency, request
										.getParameter("trialTaxAmount")));
					}
					reqDetails.setPaymentPeriod(paymentPeriod);
				}
				if (request.getParameter("maxFailedPayments") != "") {
					reqDetails.setMaxFailedPayments(Integer.parseInt(request
							.getParameter("maxFailedPayments")));
				}
				if (request.getParameter("profileDescription") != "") {
					reqDetails.setDescription(request
							.getParameter("profileDescription"));
				}
				if (request.getParameter("additionalBillingCycles") != "") {
					reqDetails.setAdditionalBillingCycles(Integer
							.parseInt(request
									.getParameter("additionalBillingCycles")));
				}
				if (request.getParameter("autoBillOutstandingAmount") != "") {
					reqDetails
							.setAutoBillOutstandingAmount(AutoBillType.fromValue(request
									.getParameter("autoBillOutstandingAmount")));
				}

				if (request.getParameter("amount") != "") {
					reqDetails.setAmount(new BasicAmountType(currency, request
							.getParameter("amount")));
				}
				if (request.getParameter("shippingAmount") != "") {
					reqDetails.setAmount(new BasicAmountType(currency, request
							.getParameter("shippingAmount")));
				}
				if (request.getParameter("taxAmount") != "") {
					reqDetails.setAmount(new BasicAmountType(currency, request
							.getParameter("taxAmount")));
				}

				if (request.getParameter("subscriberName") != "") {
					reqDetails.setSubscriberName(request
							.getParameter("subscriberName"));
				} else if (request.getParameter("shippingName") != "") {
					AddressType shippingAddr = new AddressType();
					shippingAddr.setName(request.getParameter("shippingName"));
					shippingAddr.setStreet1(request
							.getParameter("shippingStreet1"));
					shippingAddr.setStreet2(request
							.getParameter("shippingStreet2"));
					shippingAddr
							.setPhone(request.getParameter("shippingPhone"));
					shippingAddr.setCityName(request
							.getParameter("shippingCity"));
					shippingAddr.setStateOrProvince(request
							.getParameter("shippingState"));
					shippingAddr.setCountryName(request
							.getParameter("shippingCountry"));
					shippingAddr.setPostalCode(request
							.getParameter("shippingPostalCode"));
					reqDetails.setSubscriberShippingAddress(shippingAddr);
				}

				reqType.setUpdateRecurringPaymentsProfileRequestDetails(reqDetails);
				req.setUpdateRecurringPaymentsProfileRequest(reqType);
				UpdateRecurringPaymentsProfileResponseType resp = service
						.updateRecurringPaymentsProfile(req);
				if (resp != null) {
					session.setAttribute("lastReq", service.getLastRequest());
					session.setAttribute("lastResp", service.getLastResponse());
					if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						map.put("Profile ID",
								resp.getUpdateRecurringPaymentsProfileResponseDetails()
										.getProfileID());
						session.setAttribute("map", map);
						response.sendRedirect("/merchant-sample/Response.jsp");
					} else {
						session.setAttribute("Error", resp.getErrors());
						response.sendRedirect("/merchant-sample/Error.jsp");
					}
				}
			} else if (request.getRequestURI()
					.contains("BillOutstandingAmount")) {
				BillOutstandingAmountReq req = new BillOutstandingAmountReq();
				BillOutstandingAmountRequestType reqType = new BillOutstandingAmountRequestType();
				BillOutstandingAmountRequestDetailsType reqDetails = new BillOutstandingAmountRequestDetailsType(
						request.getParameter("profileID"));
				if (request.getParameter("amt") != "")
					reqDetails.setAmount(new BasicAmountType(currency, request
							.getParameter("amt")));
				reqType.setVersion("84");
				reqType.setBillOutstandingAmountRequestDetails(reqDetails);
				req.setBillOutstandingAmountRequest(reqType);
				BillOutstandingAmountResponseType resp = service
						.billOutstandingAmount(req);
				if (resp != null) {
					session.setAttribute("lastReq", service.getLastRequest());
					session.setAttribute("lastResp", service.getLastResponse());
					if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						map.put("Profile ID", resp
								.getBillOutstandingAmountResponseDetails()
								.getProfileID());
						session.setAttribute("map", map);
						response.sendRedirect("/merchant-sample/Response.jsp");
					} else {
						session.setAttribute("Error", resp.getErrors());
						response.sendRedirect("/merchant-sample/Error.jsp");
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SSLConfigurationException e) {
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (HttpErrorException e) {
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			e.printStackTrace();
		} catch (OAuthException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
