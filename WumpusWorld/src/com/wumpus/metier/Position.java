/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wumpus.metier;

import java.util.ArrayList;

public class Position {

   public int x = -1;
   public int y = -1;
int Nb_de_passage = 0;
    public Position() {
    }

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNb_de_passage() {
		return Nb_de_passage;
	}

	public void setNb_de_passage(int nb_de_passage) {
		Nb_de_passage = nb_de_passage;
	}

	public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String arg[]) {
        Position p = new Position();
        p.x = 0;
        p.y = 2;

        ArrayList<Position> array = new ArrayList<Position>();
        array.add(p);
        
        if (array.contains(new Position(0, 2))) {
            System.out.print("existe");
        }
       
       
    }
}
