/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

public class Square implements Serializable{

    public boolean isOpen, isFlag, isMine,isOpen2,isFlag2;
    public int minesNearBy = 0;
    public int numperofplayer =0;
    void reset() {
        isFlag = false;
        isMine = false;
        isOpen = false;
        minesNearBy = 0;
        isOpen2=false ;
        isFlag2=false;
          numperofplayer =0;
    }

}
