/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.GameMaster;
import java.awt.Color;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.Timer;
import mine3.MineSweeper3;

public class Grid implements Serializable {

    public int ismulti;
        public int iswithcomputer;
    public Player player = new Player();
    public Player player2 = new Player();

    public Timer t;
    public int k = 1;
    public int numOfMines;
    public  int numOfFlags;
    public Square[][] sequers;
    public int w;
    public int h;
    public int leftcelles = 166;
    public boolean isonceflaged[][];
   public int state=0;
    public static boolean multiplayer = false;
    public static boolean vscomputer = false;
    public  int computerturn = 0;

    public  int playerturn = 0;

    public  int getnumOfFlags() {
        return numOfFlags;
    }
    // public Object Player;

    public Grid(int w, int h, int numOfMines) {
        this.sequers = new Square[w][h];
        this.numOfMines = numOfMines;
        this.numOfFlags = numOfMines;
        this.w = w;
        this.h = h;
        leftcelles = w * h - numOfMines;
        this.fillGrid();
        if (multiplayer) {
            player.setColor(Color.BLUE);
            player2.setColor(Color.YELLOW);
        }
        isonceflaged = new boolean[w][h];
        if(" Biginner ".equals(MineSweeper3.s))
        {
            state=2;
        }
    }

    public void fopen2() {

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                sequers[i][j].isOpen2 = false;
                sequers[i][j].isFlag2 = false;

            }
        }

    }

    public void setnumOfFlags() {
        numOfFlags = numOfMines;
    }

    public void reset() {
        GameMaster.gameEnded = false;
        setnumOfFlags();
        this.t.stop();
        GameMaster.firstopen = false;
        leftcelles = w * h - numOfMines;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                sequers[i][j].reset();

            }
        }

    }

    public void save() {
        if (multiplayer) {
            ismulti = 2;
        }
         if (vscomputer) {
            iswithcomputer=2;
        }
       
        try {

            FileOutputStream file = new FileOutputStream("savegrid.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
            file.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public static Grid continuefromlastposition() {
        Grid object1 = null;

        try {

            FileInputStream file = new FileInputStream("savegrid.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            object1 = (Grid) in.readObject();

            in.close();
            file.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        return object1;
    }

    void fillGrid() {

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                sequers[i][j] = new Square();

            }
        }
         if(" Biginner ".equals(MineSweeper3.s))
        {
            state=2;
        }
    }
}
