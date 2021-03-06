<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PayPal SDK - GetBoardingDetails</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h3>GetBoardingDetails</h3>
			<div id="apidetails">GetBoardingDetails queries the state of a
				merchant's account and retrieves a merchant's PayPal API
				credentials.</div>
		</div>
		<form method="POST">
			<div id="request_form">
				<div class="params">
					<div class="param_name">
						Onboarding Token* (Get OnboardingToken via <a href="EnterBoarding">EnterBoarding</a>
					</div>
					<div class="param_value">
						<input type="text" name="onboardingToken" value="" maxlength="260" />
					</div>

				</div>
				<div class="submit">
					<input type="submit" name="GetBoardingDetails"
						value="GetBoardingDetails" /> <br />
				</div>
				<a href="/merchant-sample/index.html">Home</a>
			</div>
		</form>
		<div id="relatedcalls">
			See also
			<ul>
				<li><a href="EnterBoarding">EnterBoarding</a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>