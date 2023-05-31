/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Ali-Almski
 */
public class Player implements Serializable {
   public int score =0;
    public Color  color ;

    public void setColor(Color color) {
        this.color = color;
    }

    
    
}
