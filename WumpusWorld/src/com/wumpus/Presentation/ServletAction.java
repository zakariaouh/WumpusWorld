package com.wumpus.Presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wumpus.metier.Position;
import com.wumpus.metier.ResultatsClass;
import com.wumpus.metier.Wumpus;

public class ServletAction extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int xT = Integer.parseInt(req.getParameter("XT"));
		int yT = Integer.parseInt(req.getParameter("YT"));
		xT=xT<4?4:xT;
		yT=yT<4?4:yT;
		
		String[][] input = Wumpus.InputDynamique(xT, yT);
		Wumpus.Where_is_Gold(input);
		
		
	/*	String html="----------------------------------";
		if("PIT".equalsIgnoreCase(ResultatsClass.feeling)){
			html="mchiti fiha pit";

			}else if ("Gold".equalsIgnoreCase(ResultatsClass.feeling)) {
			html="rbahna";
			html="<br>";
			for(int h=0;h<ResultatsClass.cheminement_simplifier.size();h++){
				 Position pos = ResultatsClass.cheminement_simplifier.get(h);
			     html += "(" + pos.getX()+ "," + pos.getY() + ")=>";
			   }
			  if(ResultatsClass.flech.x!=-1){
				     html+="<br>L'agent à utilisé une flech a la position suivant: <br>"
				     			+ "(" + ResultatsClass.flech.getX()+ "," 
				     				+ ResultatsClass.flech .getY()+ ")";
				     }
			}else if ("Wumpus".equalsIgnoreCase(ResultatsClass.feeling)) {
			html="wumpus klak a mikhi";	
			} else{
			html="aucun solution";
			}
		html+="<br> Le chemin suivier est :<br>";
		for(int h=0;h<ResultatsClass.cheminement.size();h++){
			 Position pos = ResultatsClass.cheminement.get(h);
		     html += "(" + pos.getX()+ "," + pos.getY() + ")=>";	
		}
		System.out.println(html+"<br>---------------");
		/**/
		
		
		
		/*
		// String xT=req.getParameter("XT");
		// String yT=req.getParameter("YT");

		System.out.println(xT + " " + yT);
		String[][] input = Wumpus.InputDynamique(xT, yT);
		System.out.println("          Grille d'entrer");
		System.out.println("##########################################");
		if (input != null)
			for (int yy = 0; yy < input.length; yy++) {

				for (int xx = 0; xx < input[0].length; xx++) {

					System.out.print(input[yy][xx] + " | ");
				}
				System.out.println("");
			}
		

		
		
		
		Wumpus.Where_is_Gold(input);
		System.out.println("##########################################");
		System.out.println("             Partie Terminer");
		System.out.println("##########################################");
		/**/
		req.setAttribute("HTMLRes", Wumpus.html);
		req.setAttribute("XT", xT);
		req.setAttribute("YT", yT);
		req.setAttribute("grille", input);
		req.getRequestDispatcher("Environnement.jsp").

		forward(req, resp);

	}
}
