<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>PayPal Invoicing SDK - UpdateInvoice</title>
	<link rel="stylesheet" type="text/css" href="sdk.css"/> 
	<script type="text/javascript" src="sdk.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h3>UpdateInvoice</h3>
			<div id="apidetails">The UpdateInvoice API operation is used to
				update an invoice.</div>
		</div>
		<form method="POST">
			<div id="request_form">
				<div class="params">
					<div class="param_name">
						Invoice ID* (Get Invoice ID via <a href='CreateInvoice'>CreateInvoice</a>
						or <a href='CreateInvoice'>CreateAndSendInvoice</a>)
					</div>
					<div class="param_value">
						<input type="text" name="invoiceId"
							value="INV2-8QPU-J739-KZHX-66TS" size="50" maxlength="260" />
					</div>
					<div class="section_header">Invoice Details *</div>
					<div class="param_name">Merchant Email</div>
					<div class="param_value">
						<input type="text" name="merchantEmail"
							value="jb-us-seller@paypal.com" size="50" maxlength="260" />
					</div>
					<div class="param_name">Payer Email</div>
					<div class="param_value">
						<input type="text" name="payerEmail" value="sender@yahoo.com"
							size="50" maxlength="260" />
					</div>
					<div class="param_name">Item1 Name</div>
					<div class="param_value">
						<input type="text" name="item_name1" value="item1" size="30"
							maxlength="30" />
					</div>
					<div class="param_name">Item1 Quantity</div>
					<div class="param_value">
						<input type="text" name="item_quantity1" value="1" size="3"
							maxlength="5" />
					</div>
					<div class="param_name">Item1 UnitPrice</div>
					<div class="param_value">
						<input type="text" name="item_unitPrice1" value="1.00" size="10"
							maxlength="19" />
					</div>
					<div class="param_name">Item2 Name</div>
					<div class="param_value">
						<input type="text" name="item_name2" value="item2" size="30"
							maxlength="30" />
					</div>
					<div class="param_name">Item2 Quantity</div>
					<div class="param_value">
						<input type="text" name="item_quantity2" value="2" size="3"
							maxlength="5" />
					</div>
					<div class="param_name">Item2 UnitPrice</div>
					<div class="param_value">
						<input type="text" name="item_unitPrice2" value="2.00" size="10"
							maxlength="19" />
					</div>
					<div class="param_name">Currency Code</div>
					<div class="param_value">
						<input type="text" name="currencyCode" value="USD" size="50"
							maxlength="260" />
					</div>
					<div class="param_name">Payment Terms</div>
					<div class="param_value">
						<select name="paymentTerms">
							<option value="DueOnReceipt">DueOnReceipt</option>
							<option value="DueOnDateSpecified">DueOnDateSpecified</option>
							<option value="Net10">Net10</option>
							<option value="Net15">Net15</option>
							<option value="Net30">Net30</option>
							<option value="Net45">Net45</option>
						</select>
					</div>


					<jsp:include page="permissions.jsp">
						<jsp:param name="source" value="UpdateInvoice"/>
					</jsp:include>
				</div>
				<div class="submit">
					<input type="submit" name="UpdateBtn" value="UpdateInvoice" /><br />
				</div>
				<a href="index.jsp">Home</a>
			</div>
		</form>
	</div>
</body>
</html>