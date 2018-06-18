<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				
		<title>Sky Tabs</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="./admin/css/demo.css">
		<link rel="stylesheet" href="./admin/css/font-awesome.css">
		<link rel="stylesheet" href="./admin/css/sky-tabs.css">
		<% if(session.getAttribute("cid")==null){
			response.sendRedirect("login.jsp");
		} %>
		
		<style>
			.vertical-divider{
			clear: both;
			position: relative;
			}
			.vertical-divider:after {
				clear: both;
				content: " ";
				display: block;
				height: 0;
				visibility: hidden;
			}
			.vertical-divider .column:not(:first-child):after, .vertical-divider .columns:not(:first-child):after{
				background: #DDDDDD;
				bottom: 0;
				content: " ";
				margin-left: -10px;
				position: absolute;
				top: 0;
				width: 1px;
			}
			/* Very simple grid for example */
			.container { position: relative; width: 960px; margin: 20px auto; padding: 0; }
			.container .column, .container .columns { float: left; display: inline; margin-left: 10px; margin-right: 10px; }
			.container .one-third.column { width: 450px; }
			.container .two-thirds.column { width: 620px; }
		
		.wthreesubmitaits {
				padding: 0px 0px 30px;
				text-align: center;
		}

		.wthreesubmitaits input {
			padding: 7px 30px;
			font-family: 'Varela Round', sans-serif;
			font-size: 15px;
			border: none;
			outline: none;
		cursor: pointer;
		}

		.wthreesubmitaits input:hover {
			background-color:#03bbb1;
		}
		.wthreesubmitaits {
			padding: 0px 0px 14px;
		}
		.tickets {
			padding: 10px 0px;
			text-align: left;
		}
		.tickets input {
			width: 97%;
		}
		.tickets input {
			width: 100%;
			padding: 7px;
			font-family: 'Varela Round', sans-serif;
			border: none;
			font-size: 15px;
			outline: none;
			color: #000;
			background-color: rgba(255, 255, 255, 0.76);
			margin-top: 12px;
			margin-bottom: 18px;
		}
		</style>
		<script>function check() {
					if ((document.getElementById('npass').value ==
						document.getElementById('rnpass').value) && (document.getElementById('npass').value != document.getElementById('pass').value)) {
						document.getElementById('message').style.color = 'green';
						document.getElementById('message').innerHTML = 'Matched';
						document.getElementById('submit').disabled = false;
					} 
				else if(document.getElementById('npass').value == document.getElementById('pass').value){
					document.getElementById('message').style.color = 'red';
					document.getElementById('message').innerHTML = 'New Password must be different from old Password';
					document.getElementById('submit').disabled = true;
				}
				else{
					document.getElementById('submit').disabled = true;
					document.getElementById('message').style.color = 'red';
					document.getElementById('message').innerHTML = 'These passwords do not match. Try again?';
				}
			}
		</script>
		<script>	
			function check_pass(){
				if(document.getElementById('opass').value == document.getElementById('pass').value){
					document.getElementById('pass_msg').style.color = 'green';
					document.getElementById('pass_msg').innerHTML = 'Matched';
					document.getElementById('submit').disabled = false;
				}
				else{
					document.getElementById('submit').disabled = true;
					document.getElementById('pass_msg').style.color = 'red';
					document.getElementById('pass_msg').innerHTML = 'This passwords is not correct. Try again?';
				}
			}
	</script>
	</head>
	<h1 align="center" style="color:rgb(27, 81, 68);">Child~Vaccination Care</h1>
	<p align="center" style="color:rgb(239, 248, 248);"}>Welcome, <%= session.getAttribute("fname") %></p>
	<body class="bg-cyan" cz-shortcut-listen="true">
		<div class="body">
		
			<!-- tabs -->
			<div class="sky-tabs sky-tabs-pos-top-left sky-tabs-anim-flip sky-tabs-response-to-icons">
				<input type="radio" name="sky-tabs" checked="" id="sky-tab1" class="sky-tab-content-1">
				<label for="sky-tab1"><span><span><i class="fa fa-bolt"></i>Vaccine Details</span></span></label>
				
				<input type="radio" name="sky-tabs" id="sky-tab2" class="sky-tab-content-2">
				<label for="sky-tab2"><span><span><i class="fa fa-picture-o"></i>Vaccine Schedule</span></span></label>
				
				<input type="radio" name="sky-tabs" id="sky-tab3" class="sky-tab-content-3">
				<label for="sky-tab3"><span><span><i class="fa fa-cogs"></i>Setting</span></span></label>
				
				<input type="radio" name="sky-tabs" id="sky-tab4" class="sky-tab-content-4">
				<label for="sky-tab4"><span><span><i class="fa fa-globe"></i>Profile</span></span></label>
				
				<label ><span><span><a href="javascript:logout()"  style="color:black; text-decoration: none; display:block;padding:0.5px"><i class="fa fa-wrench"></i>Logout</a></span></span></label>
				<ul>
					<li class="sky-tab-content-1">					
						<div >
							<table border="1" style="width:100%">
							 <tr>
								<th><u>Vaccination Time</ul></th>
								<th><u>Vaccine Name</ul></th> 
								<th><u>Vaccination Date</ul></th>
							</tr>
							
							<tr><th></th><th></th><th>(YYYY-MM-DD)</th></tr>
							<tr></tr>
							<tr>
								<td>Birth</td>
								<td> BCG, OPVO, HepB1</td> 
								<td><%= session.getAttribute("v1") %></td>
							</tr>
							<tr>
								<td>6 Weeks</td>
								<td>DTwP1/DTaP1, OPV1*/OPV1+IPV1, Hib1,  HepB2, Rotavirus 1, PCV1</td> 
								<td><%= session.getAttribute("v2") %></td>
							</tr>
							<tr>
								<td>10 Weeks</td>
								<td>DTwP2/DTaP2, OPV2*/OPV2+IPV2, Hib 2, Rotavirus 2, PCV 2</td> 
								<td><%= session.getAttribute("v3")  %></td>
							</tr>
							<tr>
								<td>14 Weeks</td>
								<td> DTwP3/DTaP3, OPV3*/OPV3+IPV3, Hib3, Rotavirus 3, HepB3, PCV3</td> 
								<td><%= session.getAttribute("v4")  %></td>
							</tr>
							<tr>
								<td>9 Months</td>
								<td>Measles</td> 
								<td><%= session.getAttribute("v5")  %></td>
							</tr>
							<tr>
								<td>12 Months</td>
								<td>Hepatitis A1</td> 
								<td><%= session.getAttribute("v6") %></td>
							</tr>
							<tr>
								<td>15 Months</td>
								<td>MMR1, Varivella, PCV Booster</td> 
								<td><%= session.getAttribute("v7")  %></td>
							</tr>
							<tr>
								<td>16 to 18 Months</td>
								<td>DTwP B1/DTaP B1, OPV4*/OPV4 + IPVB1, Hib booster</td> 
								<td><%= session.getAttribute("v8")  %></td>
							</tr>
							<tr>
								<td>18 Months</td>
								<td>Hepatitis A2</td> 
								<td><%= session.getAttribute("v9") %></td>
							</tr>
							<tr>
								<td>2 Years</td>
								<td>Typhoid 1</td> 
								<td><%= session.getAttribute("v10")  %></td>
							</tr>
							</table>
						</div>
					</li>
					
					<li class="sky-tab-content-2">
						<div class="typography">
							<h1>Vaccination Schedule</h1>
							<img src="admin/img/i2.jpg" alt="Mountain View" style="width:100%;height:350px;">
						</div>
					</li>
					
					<li class="sky-tab-content-3">
						<div class="tickets">
							<div><u><h3>Change Your Password</h3></u><span></span></div>
							<br/>
							<form action="ChangePassword" method="post">
							<label>Enter Old Password  : </label>
							<input type="hidden" id="pass" value=<%= session.getAttribute("password") %> />
							<input type="hidden" name="cid" id="cid" value=<%= session.getAttribute("cid") %> />
							<span style="border: 1px solid rgb(45, 165, 138)"><input id="opass" type="password" name="opass" required="" onkeyup='check_pass();' /></span>
							<span id='pass_msg'></span>
							<br/><br/>
							<label>Enter New Password  :   </label>
							<span style="border: 1px solid rgb(45, 165, 138)"><input pattern=".{6,15}" id="npass" type="password" name="npass" required="" onkeyup='check();' title="6 character minimum" /></span>
							<br/><br/>
							<label>Repeat New Password  :  </label>
							<span style="border: 1px solid rgb(45, 165, 138)"><input id="rnpass" type="password" name="rnpass" required="" onkeyup='check();' /></span>
							<span id='message'></span>
							<br/><br/>
							<div class="wthreesubmitaits">
										<input id="submit" type="submit" name="submit" value="Submit" disabled="false">
								</div>
							</form>
						</div>
					</li>
					
					<li class="sky-tab-content-4">
						<div class="typography">
							
							
							<div class="container vertical-divider">
						<div class="column one-third">
						<h1>Hello, <%= session.getAttribute("fname") %></h1>
						</div>
					<div class="column two-thirds">
						<p>Here is your Profile details.</p>
				</div>
			</div>

			<div class="container vertical-divider">
			<div class="column one-third">
			<h5>Father Name : <%= session.getAttribute("fname") %></h5>
			<h5>Child Name : <%= session.getAttribute("cname") %></h5>
			<h5>DOB : <%= session.getAttribute("dob") %></h5>
			<h5>Gender : <%= session.getAttribute("gender") %></h5>
		</div>
		<div class="column one-third">
				<u><h3>Contact Details</h3></u>
				<h5>Mobile : <%= session.getAttribute("mobile") %></h5>
				<h5>E-Mail : <%= session.getAttribute("email") %></h5>
			</div>
			</div>
							
							
							
						</div>
					</li>					
				</ul>
			</div>
			<!--/ tabs -->
			
		</div>

</body>
<script type="text/javascript">
			function logout() {
				<% session.invalidate(); %>
				 window.location="index.html";
			}
		</script></html>