<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int YT = Integer.parseInt(request.getAttribute("YT").toString());
	int XT = Integer.parseInt(request.getAttribute("XT").toString());
	String html=request.getAttribute("HTMLRes").toString();
	String input[][] = (String[][])request.getAttribute("grille");
%>

<%@page import="com.wumpus.metier.*"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
      function afficher(element) {
    	  document.Formu.elements[element.name].id="boutonImage"+element.name;
    	 
      }
    
      
    </script>
<STYLE type=text/CSS>
body {
	background: #eee;
	color: #333;
	font-family: Arial, sans-serif;
	font-size: 12px;
	line-height: 16px;
	text-shadow: 0 1px 0 #fff;
}

a {
	color: #333;
	text-decoration: underline;
}

Form {
	background: #f7f7f7;
	background: -moz-linear-gradient(90deg, #ccc, #fff); /* Firefox */
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#ccc) ); /* Webkit */
	border: 1px solid #aaa;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	-moz-box-shadow: 0 0 15px #aaa;
	-webkit-box-shadow: 0 0 15px #aaa;
	margin: 60px auto 0;
	padding: 20px;
	width: <%=(XT * 110)%>px;
}










.bouton {
	BORDER-TOP-WIDTH: 2px;
	BORDER-LEFT-WIDTH: 2px;
	FONT-SIZE: 13px;
	BACKGROUND: #99CCFF no-repeat center 50%;
	BORDER-BOTTOM-WIDTH: 2px;
	WIDTH: 100px;
	COLOR: #000000;
	FONT-FAMILY: Verdana, Arial, sans-serif;
	HEIGHT: 75px;
	BORDER-RIGHT-WIDTH: 2px
}

.essai {
	BORDER-TOP-WIDTH: 0px;
	BORDER-LEFT-WIDTH: 0px;
	FONT-SIZE: 1em;
	BACKGROUND: #000000;
	BORDER-BOTTOM-WIDTH: 0px;
	COLOR: #ffffff;
	FONT-FAMILY: Verdana, Arial, sans-serif;
	BORDER-RIGHT-WIDTH: 0px
}
<%
for (int y1 = 0; y1 < YT; y1++) {
	for (int x1 = 0; x1 < XT; x1++) {
	String ch2=" ";
if ( input[y1][x1]!=null)
{ch2=input[y1][x1];}
if("PIT".equalsIgnoreCase(ch2)){
	ch2="http://dl.dropboxusercontent.com/u/18445852/images/pit.png";
} else if ("BREEZE".equalsIgnoreCase(ch2)) {
	ch2="http://dl.dropboxusercontent.com/u/18445852/images/breeze.png";
}else if ("Gold".equalsIgnoreCase(ch2)) {
	ch2="http://dl.dropboxusercontent.com/u/18445852/images/gold.png";
}else if ("Wumpus".equalsIgnoreCase(ch2)) {
	ch2="http://dl.dropboxusercontent.com/u/18445852/images/wumpus.png";
}else if ("STENCH;BREEZE".equalsIgnoreCase(ch2) | "BREEZE;STENCH".equalsIgnoreCase(ch2)) {
	ch2="http://dl.dropboxusercontent.com/u/18445852/images/bowth.png";
} else if ("STENCH".equalsIgnoreCase(ch2)) {
	ch2="http://dl.dropboxusercontent.com/u/18445852/images/stench.png";
}else{
ch2="http://dl.dropboxusercontent.com/u/18445852/images/rien.png";	
}%>

#boutonImage<%=x1+"V"+y1%> {
	BACKGROUND-IMAGE: url('<%=ch2 %>')
}

<%}}%>
h1 {
	border-bottom: 1px solid #ccc;
	font-size: 11px;
	font-weight: bold;
	letter-spacing: 2px;
	margin-bottom: 20px;
	text-transform: uppercase;
}

form p {
	margin-bottom: 20px;
}

form p:last-child {
	/* Sélecteur avancé pour sélectionner le dernier paragraphe du formulaire */
	margin-bottom: 0;
}

label {
	cursor: pointer;
	display: block;
	float: left;
	font-size: 13px;
	font-weight: bold;
	line-height: 28px;
	margin-bottom: 5px;
	width: 1000px;
	
}

form p:hover label {
	color: #0459b7;
}

form p:hover label:after {
	content: " »";
}

input[type=text] {
	background: rgba(255, 255, 255, 0.9);
	background: -moz-linear-gradient(90deg, #fff, #eee); /* Firefox */
	background: -webkit-gradient(linear, left top, left bottom, from(#eee),
		to(#fff), color-stop(0.2, #fff) ); /* Webkit */
	border: 1px solid #aaa;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-box-shadow: 0 0 3px #aaa;
	-webkit-box-shadow: 0 0 3px #aaa;
	padding: 5px;
}

input[type=text]:focus {
	border-color: #093c75;
	-moz-box-shadow: 0 0 3px #0459b7;
	-webkit-box-shadow: 0 0 3px #0459b7;
	outline: none;
	/* Pour enlever le contour jaune lorsque l'on sélectionne un input dans Chrome */
}

select {
	cursor: pointer;
	padding: 3px;
	-moz-box-shadow: 0 0 3px #aaa;
	-webkit-box-shadow: 0 0 3px #aaa;
}

select:active,select:focus {
	border: 1px solid #093c75;
	-moz-box-shadow: 0 0 3px #0459b7;
	-webkit-box-shadow: 0 0 3px #0459b7;
	outline: none;
}

#bt1,a.button {
	background: #ddd;
	background: -moz-linear-gradient(90deg, #0459b7, #08adff);
	/* Firefox */
	background: -webkit-gradient(linear, left top, left bottom, from(#08adff),
		to(#0459b7) ); /* Webkit */
	border: 1px solid #093c75;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-box-shadow: 0 1px 0 #fff;
	-webkit-box-shadow: 0 1px 0 #fff;
	color: #fff;
	cursor: pointer;
	font-family: Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
	margin-left: 120px;
	padding: 5px 10px;
	text-decoration: none;
	text-shadow: 0 1px 1px #333;
	text-transform: uppercase;
}

#bt1 :hover,a.button:hover {
	background: #eee;
	background: -moz-linear-gradient(90deg, #067cd3, #0bcdff);
	background: -webkit-gradient(linear, left top, left bottom, from(#0bcdff),
		to(#067cd3) );
	border-color: #093c75;
	text-decoration: none;
}

#bt1 :active,#bt1 :focus,a.button:active,a.button:focus {
	background: #ccc;
	background: -moz-linear-gradient(90deg, #0bcdff, #067cd3);
	background: -webkit-gradient(linear, left top, left bottom, from(#067cd3),
		to(#0bcdff) );
	border-color: #093c75;
	outline: none;
}
#copyright{
	font-size:11px;
	margin-top:20px;
	text-align:center;
}
</STYLE>
</head>
<body>

<FORM name=Formu action="/aliasAction2" method=post>
<CENTER><A name=jeu></A>
<TABLE cellSpacing=0 cellPadding=4 border=0>
	<TBODY>

		<%
			for (int y = 0; y < YT; y++) {
		%>
		<TR>
			<%
				for (int x = 0; x < XT; x++) {
			%><TD><% String idid="boutonImage"+x+"V"+y; %>
			<CENTER><INPUT class=bouton type=button name=<%=x+"V"+y %>    onClick="javascript: afficher( this );"></CENTER>
			</TD>
			<%//document.Formu.elements["base1"].id="boutonImage1";
				}
			%>
		</TR>
		<%
			}
		%>
	</TBODY>
</TABLE>
</CENTER>
</FORM>
<label>
 <%=html %></label>
 <br><% 
 String chch="<script>";
 if(!ResultatsClass.cheminement_simplifier.isEmpty())
 { int seconde=1000;
 for (int i=0;i<ResultatsClass.cheminement_simplifier.size();i++){
	seconde+=1000;
	 System.out.print("("+ResultatsClass.cheminement_simplifier.get(i).x+","+ResultatsClass.cheminement_simplifier.get(i).y+")");
	 String pVp=ResultatsClass.cheminement_simplifier.get(i).x+"V"+ResultatsClass.cheminement_simplifier.get(i).y;
	chch+="document.onload =setTimeout(function(){"+" document.Formu.elements['"+pVp+"'].id='boutonImage"+pVp+"'},"+seconde+"); " ;
 } }
 chch+="</script>";
 %>
 <%=chch %>
 <p id="copyright">©Réalisé par ZAKARIA OUHROCHAN </p>
</body>

</html>