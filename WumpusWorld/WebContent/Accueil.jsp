<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<title>Wumpus World</title>
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
	width: 440px;
}

.bouton {
	BORDER-TOP-WIDTH: 2px;
	BORDER-LEFT-WIDTH: 2px;
	FONT-SIZE: 3em;
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

#boutonImage0 {
	BACKGROUND-IMAGE: url('3.jpg')
}

#boutonImage1 {
	BACKGROUND-IMAGE: url('a.jpg')
}

#boutonImage2 {
	BACKGROUND-IMAGE: url('6.jpg')
}

#boutonImage3 {
	BACKGROUND-IMAGE: url('1.jpg')
}

#boutonImage4 {
	BACKGROUND-IMAGE: url('2.jpg')
}

#boutonImage5 {
	BACKGROUND-IMAGE: url('4.jpg')
}

#boutonCarte {
	BACKGROUND-IMAGE: url('fond.gif')
}

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
	width: 200px;
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

</HEAD>
<BODY>
<br><bR><center>
<font size=7px>Wumpus World Game</font></center><br><br>
<form id="start" action="toGrille">
<h1>Generation Grille</h1>
<p><label>Nombre de lignes</label> <input name="YT" type="text"
	value=""></p>
<p><label>Nombre de colonnes</label> <input name="XT" type="text">
</p>
<p><input id="bt1" name="Generer" type="submit" value="Generer">
</p>
</form>
<p id="copyright">©Réalisé par ZAKARIA OUHROCHAN </p>
</BODY>
</HTML>
