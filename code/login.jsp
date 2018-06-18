<!DOCTYPE HTML>
<html lang="en">
<head>
<title>Account Login</title>
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Server Login Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Meta tag Keywords -->
<!-- css files -->
<link rel="stylesheet" href="login/css/style.css" type="text/css" media="all" /> <!-- Style-CSS --> 
<link rel="stylesheet" href="login/css/font-awesome.css"> <!-- Font-Awesome-Icons-CSS -->
<link href="//fonts.googleapis.com/css?family=Muli:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i&amp;subset=latin-ext,vietnamese" rel="stylesheet">

</head>
<body>

<!-- main -->
<div class="w3ls-header">
	<h1 ><a href="index.html" style="color:rgb(255, 79, 129);">Child~Vaccination Care</a></h1>
	<div class="header-main">
		<h2>login with <a>Your</a> <span>E-mail</span> </h2>
			<div class="header-bottom">
				<div class="header-right w3agile">
					<div class="header-left-bottom agileinfo">
						<form action="LoginFilter" method="post">
							<div class="icon1">
								<% if(request.getCookies()==null){
										out.println("<script>alert('Turn on Cookies...');</script>");
									} %>
								<input  type="email" name="email" value="
								<% if(request.getCookies()!=null){
										String name;
										Cookie c[]= request.getCookies();
										for(int i=0;i<c.length;i++){
											name=c[i].getName();
											if(name.equals("email_cookie")){
												out.println(c[i].getValue());
											}
										}
									} %>" placeholder="Email@example.com" required=""/>
							</div>
							<div class="icon1">
									<input type="password" name="password" value="<% if(request.getCookies()!=null){
										String name;
										Cookie c[]= request.getCookies();
										for(int i=0;i<c.length;i++){
											name=c[i].getName();
											if(name.equals("password_cookie")){
												out.println(c[i].getValue());
											}
										}
									} %>" placeholder="Password" required="" />
							</div>
							<div class="login-check">
								 <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i> Keep me logged in</label>
							</div>
							<span style="color:red;"><% if(session.getAttribute("wrong_uname_pass")!=null){%>
								<br/>Wrong user name or password
							<%
							session.removeAttribute("wrong_uname_pass");
							}
							%>
							</span>
							<div class="bottom">
								<input type="submit" value="Log in" />
							</div>
					</form>	
					</div>
				</div>
			</div>
	</div>
</div>

<!-- copyright start here -->
<div class="copyright">
	<p>© 2017 Vaccination login here, All rights reserved | Developed by  <a>  Yamoksh Verma <br/><br/></a><br/><br/><br/><br/></p>
</div>


</body>
</html>