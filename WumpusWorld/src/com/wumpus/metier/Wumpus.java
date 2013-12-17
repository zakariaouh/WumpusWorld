/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wumpus.metier;

import java.util.ArrayList;
import java.util.Scanner;

public class Wumpus {
// attention les tableau [Y][X] et non pas [X][y]  ==> x----->COlonne  et Y ||||| ligne
public static String html="";
    static Position[] Coordonnees(int x, int y, int MaxX, int MaxY) {

        Position[] P = new Position[4];
        try {
            Position haut = new Position();
            Position bas = new Position();
            Position gauche = new Position();
            Position droite = new Position();
            if (x + 1 < MaxX) {
                droite.x = x + 1;
                droite.y = y;
            }
            if (x - 1 >= 0) {
                gauche.x = x - 1;
                gauche.y = y;
            }
            if (y + 1 < MaxY) {
                bas.x = x;
                bas.y = y + 1;
            }
            if (y - 1 >= 0) {
                haut.x = x;
                haut.y = y - 1;
            }

            P[0] = haut;
            P[1] = bas;
            P[2] = gauche;
            P[3] = droite;
        } catch (Exception e) {
            System.err.print("Erreur indÃ©finie: \n" + e);
        }
        return P;
        /* utilisation
         Position[] P = Coordonnees(0, 0, 4, 4);
        
       
         Position elem = P[0];        
         System.out.print("Haut: ");
         System.out.println("X=" + elem.x + " Y=" + elem.y);
         elem = P[1];
         System.out.print("Bas: ");
         System.out.println("X=" + elem.x + " Y=" + elem.y);
         elem = P[2];
         System.out.print("Gauche: ");
         System.out.println("X=" + elem.x + " Y=" + elem.y);
         elem = P[3];
         System.out.print("Droite: ");
         System.out.println("X=" + elem.x + " Y=" + elem.y);
         */

    }

    static String[][] InputGeneration(String[][] input) {

        for (int yy = 0; yy < input.length; yy++) {

            for (int xx = 0; xx < input[0].length; xx++) {

                Position p = new Position(xx, yy);
                input[yy][xx] = Fill_Grid(p, input);
            }
        }
        return input;
    }

    public static String[][] InputDynamique(int X_input, int Y_input) {
        if (X_input < 4) {
            X_input = 4;
        }
        if (Y_input < 4) {
            Y_input = 4;
        }
        String input[][] = new String[Y_input][X_input];
        int tab[][] = new int[Y_input][X_input];
        for (int i = 0; i < Y_input; i++) {
            for (int j = 0; j < X_input; j++) {
                tab[i][j] = 0 + (int) (Math.random() * ((18 - 0) + 1));
            }

        }
        for (int i = 0; i < Y_input; i++) {
            for (int j = 0; j < X_input; j++) {
                if (tab[i][j] % 6 == 0) {
                    input[i][j] = "Wumpus";
                }
                if (tab[i][j] % 5 == 0) {
                    input[i][j] = "PIT";
                }
            }

        }

        input[ (int) (Math.random() * ((Y_input - 1) + 1))][ (int) (Math.random() * ((X_input - 1) + 1))] = "Gold";
        if (!"Gold".equalsIgnoreCase(input[Y_input - 1][0])) {
            input[Y_input - 1][0] = "Start";
        }
        /*tester
         String[][]input2= InputDynamique(4, 4); 
         for (int yy = 0; yy < input2.length; yy++) {

         for (int xx = 0; xx < input2[0].length; xx++) {

                
         System.out.print( input2[yy][xx] + " | ");
         }
         System.out.println("");
         }  /**/

        return input;
    }

    static Tableau[][] Output(int x, int y) {
        // creation du tableau "tab[][]" qui va contenire Par la suit le chemin   si 1&1 en peut passÃ© non sinon 


        Tableau tab[][] = new Tableau[y][x];
        try {
            for (int h = 0; h < tab.length; h++) {
                for (int l = 0; l < tab[0].length; l++) {
                    Tableau element = new Tableau();
                    tab[h][l] = element;
                }
            }
        } catch (Exception e) {
            System.err.print("Erreur indéfinie: \n" + e);
        }
        /* //affichage
         for (int h = 0; h < tab.length; h++) {
         for (int l = 0; l < tab.length; l++) {
         Tableau element2 = tab[h][l];

         System.out.print(element2.isWumpus + "|" + element2.isPIT);
         System.out.print("\t");
         }
         System.out.println("");

         }*/
        return tab;
    }

    static String[][] Input(String tableauinput[][]) {//Matrice inpute

        String input[][] = {
            {"", "Wumpus", "Gold", "PIT"},
            {"Wumpus", "", "Wumpus", ""},
            {"", "", "", ""},
            {"", "", "", ""}
        };/**/
        if (tableauinput != null) {
            input = tableauinput;
        }
        input = InputGeneration(input);

        return input;
    }

    static boolean contains(Position elem, ArrayList<Position> array) {
        boolean existe = false;
        try {
            for (int n = 0; n < array.size(); n++) {
                Position p = array.get(n);
                if (p.x == elem.x & p.y == elem.y) {
                    existe = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.err.print("Erreur indÃ©finie: \n" + e);
        }
        return existe;
    }

    static int lastIndexOf(ArrayList<Position> array, Position p) {
        int k = -1;
        try {
            for (int i = array.size() - 1; i >= 0; i--) {
                Position pos = array.get(i);
                if (pos.x == p.x & pos.y == p.y) {
                    k = i;
                    break;
                }
            }
        } catch (Exception e) {
            System.err.print("Erreur indÃ©finie: \n" + e);
        }
        return k;
    }

    static int nb_de_passage(Position p, ArrayList<Position> array) {
        try {
            if (!contains(p, array)) {
                p.Nb_de_passage = 1;

                return 1;
            } else if (array.get(lastIndexOf(array, p)).Nb_de_passage >= 4) {
                p.Nb_de_passage = array.get(lastIndexOf(array, p)).Nb_de_passage;
                return -1;
            } else {
                for (int i = array.size() - 1; i >= 0; i--) {
                    Position pos = array.get(i);
                    if (pos.x == p.x & pos.y == p.y) {
                        pos.Nb_de_passage++;

                    }
                }
                p.Nb_de_passage = array.get(lastIndexOf(array, p)).Nb_de_passage + 1;
            }
//array.add(p);
        } catch (Exception e) {
            System.err.print("Erreur indÃ©finie: \n" + e);
        }
        return 0;
    }

    public static ArrayList<Position> optimal_path(ArrayList<Position> array, int maxX, int maxY) {
        // Elimination dial cir o rja3
        ArrayList<Position> tmp = new ArrayList<Position>();

        int k = 0;
        while (k <= array.size() - 1) {
            Position p = array.get(k);
            tmp.add(p);
            k = lastIndexOf(array, p);
            k = k + 1;
        }
        // optimiser le chemin fin wadnak haaahiya
        ArrayList<Position> p = new ArrayList<Position>();
        p.add(tmp.get(0));
        int conteur = 0;
        while (conteur <= tmp.size() - 1) {
            Position element = tmp.get(conteur);
            Position tab[] = Coordonnees(element.x, element.y, maxX, maxY);
            int max = 0;
            for (int i = 0; i < 4; i++) {
                Position elm = tab[i];
                if (contains(elm, tmp)) {
                    int indice = lastIndexOf(tmp, elm);
                    if (indice > max) {
                        max = indice;
                    }
                }

            }

            if (contains(tmp.get(max), p) | (tmp.get(max).x == tmp.get(tmp.size() - 1).x & tmp.get(max).y == tmp.get(tmp.size() - 1).y)) {
                break;
            } else {
                p.add(tmp.get(max));
            }
            conteur = max;


        }



        return p;
    }
    /*
     public static ArrayList<Position> find_all_Wumpus( Tableau[][]){
   
     return null;

     }/**/

    public static String kill_the_Wumpus(Position p, String[][] tab) {
        //fonction pour tue le wumpus et remlire ca place avec le filling de son entourage
        String chaine = "";
        if ("Gold".equalsIgnoreCase(tab[p.y][p.x])) {
            return "Gold";
        } else {
            Position[] jiran = Coordonnees(p.x, p.y, tab[0].length, tab.length);
            for (int i = 0; i < jiran.length; i++) {
                if (jiran[i].x != -1) {
                    String feeling = tab[jiran[i].y][jiran[i].x];
                    if ("PIT".equalsIgnoreCase(feeling) && !chaine.contains("BREEZE")) {
                        chaine += "BREEZE;";
                    } else if ("Wumpus".equalsIgnoreCase(feeling) && !chaine.contains("STENCH")) {
                        chaine += "STENCH;";
                    }
                }
            }
            if ("".equalsIgnoreCase(chaine)) {

                return "";
            } else {

                return chaine.substring(0, chaine.lastIndexOf(";"));
            }
        }
    }

    public static String Fill_Grid(Position p, String[][] tab) {

        //fonction qui trouve le felling d'un case de position x et y
        String chaine = tab[p.y][p.x];
        if ("PIT".equalsIgnoreCase(chaine) | "Wumpus".equalsIgnoreCase(chaine) | "Gold".equalsIgnoreCase(chaine)) {
            return chaine;
        } else {
            chaine = "";
            Position[] jiran = Coordonnees(p.x, p.y, tab[0].length, tab.length);
            for (int i = 0; i < jiran.length; i++) {
                if (jiran[i].x != -1 && jiran[i].y != -1) {
                    String feeling = tab[jiran[i].y][jiran[i].x];

                    if ("PIT".equalsIgnoreCase(feeling) && !chaine.contains("BREEZE")) {
                        chaine += "BREEZE;";
                    } else if ("Wumpus".equalsIgnoreCase(feeling) && !chaine.contains("STENCH")) {
                        chaine += "STENCH;";
                    }
                }
            }
            if ("".equalsIgnoreCase(chaine)) {
                return "";
            } else {
                return chaine.substring(0, chaine.lastIndexOf(";"));
            }
        }

    }

    public static int score(int size, boolean Gold, boolean hasNotRow) {
        /*
         * â€“ +1000 points for picking up the gold â€” this is the goal of the agent
         â€“ âˆ’1000 points for dying = entering a square containing a pit or a live
         Wumpus monster
         â€“ âˆ’1 point for each action taken, and
         â€“ âˆ’10 points for using the arrow trying to kill the Wumpus â€” so that the
         agent should avoid performing unnecessary actions.
         /*/

        size = size * -1;
        if (!hasNotRow) {
            size = size - 10;
        }
        if (Gold) {
            size = size + 1000;//wenner  
        } else {
            size = size - 1000;
        }
        return size;
    }

    public static void Where_is_Gold(String tableauinput[][]) {
    	ResultatsClass.cheminement_simplifier.clear();    	html="";
        try {
            String input[][] = Input(tableauinput);
            Tableau output[][] = Output(input[0].length, input.length);
            ArrayList<Position> cheminement = new ArrayList<Position>();
            ArrayList<Position> Wumpus_killed = new ArrayList<Position>();
            Tableau outputTMP[][] = new Tableau[input.length][input[0].length];
            boolean outputOriginale = false;
            //  Tableau outputTMP[][]=new Tableau[][];
//declaration indice des elements de deppare [Start]
            int x = 0;
            int y = input.length - 1;

            output[y][x].isPIT = 1;
            output[y][x].isWumpus = 1;


            while (true) {
                //  initialisation du point de depart

                boolean first_kill = false;

                //---(1)---------lecture et initialisation des deux chemins  suivant [deux ou plus]
//des celules qui le suit (coordonees) et kab7ato 1 fihom in amkan
                Position[] P = Coordonnees(x, y, input[0].length, input.length);
                //kanchofo le feeling fial la case courante  man ba3d on recuperet les indices 

                String feeling = input[y][x];
                if ("STENCH;BREEZE".equalsIgnoreCase(feeling) | "BREEZE;STENCH".equalsIgnoreCase(feeling)) {
                    //ne rien faire
                } else if ("STENCH".equalsIgnoreCase(feeling)) {//donc rah breeze malaynch isPIT rada tkon 1
                    //case 1,2,3,4 wach kayna deja wlla la -1 ila makaynach
                    if (P[0].y != -1 | P[0].x != -1) {
                        output[P[0].y][P[0].x].isPIT = 1;
                    }
                    if (P[1].y != -1 | P[1].x != -1) {
                        output[P[1].y][P[1].x].isPIT = 1;
                    }
                    if (P[2].y != -1 | P[2].x != -1) {
                        output[P[2].y][P[2].x].isPIT = 1;
                    }
                    if (P[3].y != -1 | P[3].x != -1) {
                        output[P[3].y][P[3].x].isPIT = 1;
                    }
                } else if ("BREEZE".equalsIgnoreCase(feeling)) {
                    if (P[0].y != -1 | P[0].x != -1) {
                        output[P[0].y][P[0].x].isWumpus = 1;
                    }
                    if (P[1].y != -1 | P[1].x != -1) {
                        output[P[1].y][P[1].x].isWumpus = 1;
                    }
                    if (P[2].y != -1 | P[2].x != -1) {
                        output[P[2].y][P[2].x].isWumpus = 1;
                    }
                    if (P[3].y != -1 | P[3].x != -1) {
                        output[P[3].y][P[3].x].isWumpus = 1;
                    }
                } else if ("PIT".equalsIgnoreCase(feeling)) {
                    System.out.print("tahti falbir x=" + x + " Y=" + y);
                    html+="tahti falbir x=" + x + " Y=" + y;
                    System.out.println("Le Score est: " + score(cheminement.size(), false, Wumpus_killed.isEmpty()));
                    html+="<br>Le Score est: " + score(cheminement.size(), false, Wumpus_killed.isEmpty());
                  
                    break;
                } else if ("Wumpus".equalsIgnoreCase(feeling)) {
                    System.out.print("wumpus klak a mikhi x=" + x + " Y=" + y);
                    html+="wumpus klak a mikhi x=" + x + " Y=" + y;
                    System.out.println("Le Score est: " + score(cheminement.size(), false, Wumpus_killed.isEmpty()));
                   
                    break;
                } else if ("Gold".equalsIgnoreCase(feeling)) {
                    Position CurantPos = new Position(x, y);
                    cheminement.add(CurantPos);
                    System.out.println("Partie Gagnée le  trésor se trouve dans x=" + x + " Y=" + y);
                    html+="<br>Partie Gagnée le  trésor se trouve dans x=" + x + " Y=" + y;
                    System.out.println(" Le chemin suivie est:");
                    html+="<br> Le chemin suivie est:";
                    String chm = "";
                    for (int i = 0; i < cheminement.size(); i++) {
                        Position pos = cheminement.get(i);
                        chm += "(" + pos.x + "," + pos.y + ")=>";

                    }
                    System.out.println(chm);html+=chm;
                   System.out.println("Le Score est: " + score(cheminement.size(), true, Wumpus_killed.isEmpty()));
                    html+="<br>Le Score est: " + score(cheminement.size(), true, Wumpus_killed.isEmpty());
                    ArrayList<Position> optimal_path = new ArrayList<Position>();
                    optimal_path = optimal_path(cheminement, input[0].length, input.length);
                    System.out.println(" Le chemin optimal est:");
                    html+="<br> Le chemin optimal est:";
                    String chm2 = "";
                    optimal_path.add(CurantPos);
                    for (int i = 0; i < optimal_path.size(); i++) {
                        Position pos = optimal_path.get(i);
                        chm2 += "(" + pos.x + "," + pos.y + ")=>";
                        
                      

                    }
                    System.out.println(chm2);
                    html+=chm2;
//------------------utilisation de la fleche----------
                    if (!Wumpus_killed.isEmpty()) {
                        Position flechepos = new Position(Wumpus_killed.get(Wumpus_killed.size() - 1).x, Wumpus_killed.get(Wumpus_killed.size() - 1).y);
                        System.out.println("Nb:  L'agent a utilisé une fléche pour tuer le Wumpus au point suivant (" + flechepos.x + "," + flechepos.y + ")");
                        html+="<br>Nb:  L'agent a utilisé une fléche pour tuer le Wumpus au point suivant (" + flechepos.x + "," + flechepos.y + ")";
                      /*  for(int ii=0;ii<Wumpus_killed.size();ii++){
                        	 html+="(" + flechepos.x + "," + flechepos.y + ")";
                        	
                        }/**/
                        /* for (int i = 0; i < Wumpus_killed.size(); i++) {
                         Position position = Wumpus_killed.get(i);
                         System.out.print("(" + position.x + "," + position.y + ")");  
                            
                         }/**/
                       
                    }

                    System.out.println("Le Score est: " + score(optimal_path.size(), true, Wumpus_killed.isEmpty()));
                    html+="<br>Le Score est: " + score(optimal_path.size(), true, Wumpus_killed.isEmpty());
                    for(int i=0;i<optimal_path.size();i++){
                    ResultatsClass.cheminement_simplifier.add(new Position(optimal_path.get(i).x,optimal_path.get(i).y));}
                    
                    break;
                } else {//ila makan tawahd la breeze la stech donc rah makayb la wumpus la PIT
                    if (P[0].y != -1 | P[0].x != -1) {
                        output[P[0].y][P[0].x].isPIT = 1;
                        output[P[0].y][P[0].x].isWumpus = 1;
                    }
                    if (P[1].y != -1 | P[1].x != -1) {
                        output[P[1].y][P[1].x].isPIT = 1;
                        output[P[1].y][P[1].x].isWumpus = 1;
                    }
                    if (P[2].y != -1 | P[2].x != -1) {
                        output[P[2].y][P[2].x].isPIT = 1;
                        output[P[2].y][P[2].x].isWumpus = 1;
                    }
                    if (P[3].y != -1 | P[3].x != -1) {
                        output[P[3].y][P[3].x].isPIT = 1;
                        output[P[3].y][P[3].x].isWumpus = 1;
                    }


                }

                Position pointcourant = new Position(x, y);
                Position get = new Position();
                if (!cheminement.isEmpty()) {
                    get = cheminement.get(cheminement.size() - 1);
                }

                boolean flag = true;
                //trouver parmis les cases voisin Ã  laquel on peut se diriger 
                if (P[0].y != -1 | P[0].x != -1) {
                    Position test = new Position(P[0].x, P[0].y);
                    if ((get.x == P[0].x && get.y == P[0].y) | contains(test, cheminement)) {
                    } else if (output[P[0].y][P[0].x].isWumpus == 1 & output[P[0].y][P[0].x].isPIT == 1) {
                        //ila kan is wumpus et is PIT fihom 1 ya3ni makayn la wahch la bir rabdizo
                        //changer daba l'indice l indice jdid o2initialiser chelin f chemib dil khona la9dim lli jabna lahna
                        //  cheminement.add(pointcourant);

                        x = P[0].x;
                        y = P[0].y;
                        flag = false;
                    }
                }
                if (flag) {
                    if (P[1].y != -1 | P[1].x != -1) {
                        Position test = new Position(P[1].x, P[1].y);
                        if ((get.x == P[1].x && get.y == P[1].y) | contains(test, cheminement)) {
                        } else if (output[P[1].y][P[1].x].isWumpus == 1 & output[P[1].y][P[1].x].isPIT == 1) {
                            // cheminement.add(pointcourant);
                            x = P[1].x;
                            y = P[1].y;
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    if (P[2].y != -1 | P[2].x != -1) {
                        Position test = new Position(P[2].x, P[2].y);
                        if ((get.x == P[2].x && get.y == P[2].y) | contains(test, cheminement)) {
                        } else if (output[P[2].y][P[2].x].isWumpus == 1 & output[P[2].y][P[2].x].isPIT == 1) {

                            // cheminement.add(pointcourant);
                            x = P[2].x;
                            y = P[2].y;
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    if (P[3].y != -1 | P[3].x != -1) {
                        Position test = new Position(P[3].x, P[3].y);
                        if ((get.x == P[3].x && get.y == P[3].y) | contains(test, cheminement)) {
                        } else if (output[P[3].y][P[3].x].isWumpus == 1 & output[P[3].y][P[3].x].isPIT == 1) {
                            //  cheminement.add(pointcourant);
                            x = P[3].x;
                            y = P[3].y;
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    if (get.Nb_de_passage < 4 && get.x != -1 && get.y != -1) {
                        x = get.x;
                        y = get.y;
                    } else {
                        boolean resultat = true;
                        for (int i = 0; i < output.length; i++) {
                            if (resultat) {
                                for (int j = 0; j < output[0].length; j++) {
                                    Tableau t = output[i][j];
                                    if (t.isPIT == 1 && t.isWumpus == 1 && t.checked == false) {
                                        x = j;
                                        y = i;
                                        resultat = false;
                                        break;
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                        if (resultat) {
//si ce test est vrais donc l'agent n'a pas trouvÃ© le chemin pour aller vers le gold en va essayÃ© d'utilisÃ© la fleche 
                            boolean aucun_solution = true;

                            if (!outputOriginale) {
                                outputTMP = output;
                                outputOriginale = true;
                            }
                            input = Input(tableauinput);
                            output = Output(input[0].length, input.length);
                            cheminement.clear();
                            x = 0;
                            y = input.length - 1;

                            output[y][x].isPIT = 1;
                            output[y][x].isWumpus = 1;
                            first_kill = true;

                            for (int yy = 0; yy < outputTMP.length; yy++) {
                                boolean sortire_de_la_boucle = false;
                                for (int xx = 0; xx < outputTMP[0].length; xx++) {
                                    Tableau element = new Tableau();
                                    element = outputTMP[yy][xx];
                                    Position pos = new Position(xx, yy);

                                    if (!contains(pos, Wumpus_killed) && element.isPIT == 1 && element.isWumpus == 0) {
                                        Wumpus_killed.add(pos);
                                        boolean iswompus = "Wumpus".equalsIgnoreCase(input[pos.y][pos.x]);
                                        input[pos.y][pos.x] = kill_the_Wumpus(pos, input);
                                        if (iswompus) {
                                            input = InputGeneration(input);
                                        }/**/
                                        output[pos.y][pos.x].isPIT = 1;
                                        output[pos.y][pos.x].isWumpus = 1;
                                        sortire_de_la_boucle = true;
                                        aucun_solution = false;
                                        break;
                                    }
                                }
                                if (sortire_de_la_boucle) {
                                    break;
                                }
                            }
                            if (aucun_solution) {
                                System.out.println("Aucun solution n'a été trouver");//©
                                html+="<br>Aucun solution n'a été trouver";
                                break;

                            }

                        }


                    }

                }
                if (!first_kill) {
                    if (!contains(pointcourant, cheminement)) {
                        pointcourant.Nb_de_passage = 1;

                    } else {
                        boolean uneseulfois = true;
                        for (int i = cheminement.size() - 1; i >= 0; i--) {
                            Position pos = cheminement.get(i);
                            if (pos.x == pointcourant.x & pos.y == pointcourant.y) {
                                pos.Nb_de_passage++;
                                cheminement.set(i, pos);
                                if (uneseulfois) {
                                    uneseulfois = false;
                                    pointcourant.Nb_de_passage = pos.Nb_de_passage;

                                }

                            }
                        }

                    }

                    cheminement.add(pointcourant);
                    output[pointcourant.y][pointcourant.x].checked = true;
                }
                /*
                 String chm = "";
                 for (int i = 0; i < cheminement.size(); i++) {
                 Position pos = cheminement.get(i);
                 chm += "(" + pos.x + "," + pos.y + ")=>";

                 }
                 System.out.println(chm + "(" + x + "," + y + ")");
                 /**/

            }


            /*  for (int i = 0; i < output.length; i++) {
                      
             for (int j = 0; j < output[0].length; j++) {
             Tableau t = output[i][j];
             System.out.print("("+t.isPIT+","+t.isWumpus+","+t.checked+") ");
                                
             }
             System.out.println("");
             }/**/

        } catch (Exception e) {
            System.err.print("Erreur indéfinie: \n" + e);
           
        }

    }

    public static void main(String[] args) {
        try {
            String[][] input2 = null;
            Scanner sc;
            sc = new Scanner(System.in);
            System.out.println("--------------------Bien Venu dans le Monde Wumpus-----------------------");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("-----------------------------  Menu   -----------------------------------");
            System.out.println("-------------------------------------------------------------------------");
            while (true) {


                System.out.println("");
                System.out.println("1- Genere une nouvelle Grille ");
                System.out.println("2- utiliser une grille existante ");
                System.out.println(">>");
                String resp = sc.nextLine();
               
                if ("1".equalsIgnoreCase(resp)) {
                    System.out.println("Entrer le nombre de Colonne");
                    System.out.println(">>");
                 int   x = (int) sc.nextInt();
                    System.out.println("Entrer le nombre de Ligne");
                    System.out.println(">>");
                  int  y = (int) sc.nextInt();
                    input2 = InputDynamique(x, y);
                }
                System.out.println("          Grille d'entrer");
				System.out
						.println("##########################################");
				if (input2 != null)
					for (int yy = 0; yy < input2.length; yy++) {

						for (int xx = 0; xx < input2[0].length; xx++) {

							System.out.print(input2[yy][xx] + " | ");
						}
						System.out.println("");
					}/**/
				Where_is_Gold(input2);
				System.out
						.println("##########################################");
				System.out.println("             Partie Terminer");
				System.out
						.println("##########################################");
                System.out.println("Voulez vous jouer une autre partie yes/no");
                System.out.println(">>");
               
                
                resp= sc.nextLine();
                if ("no".equalsIgnoreCase(resp)) {
                    break;
                }


            }


        } catch (Exception e) {
            System.err.print("Erreur indÃ©finie: \n" + e);
        }
    }
}
