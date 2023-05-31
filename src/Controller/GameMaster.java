/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Grid;
import Model.Square;

import View.GUI;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import mine3.MineSweeper3;

public class GameMaster {

    public static boolean gameEnded;

    public Grid grid;
    GUI gui;

    public GameMaster(Grid grid, GUI gui) {
        this.grid = grid;
        this.gui = gui;
    }

    public void timer() {

        this.grid.t.start();

    }

    public void stoptimer() {

        this.grid.t.stop();

    }

    public static boolean firstopen = false;

    public void distrbuteMines(int x, int y) {
//        TODO: you will change the isMine,

        //  timer();
        for (int i = 0; i < grid.numOfMines; i++) {
            int w1 = (int) ((random() * (((grid.w - 1) - 0) + 1)) + 0);
            int w2 = (int) ((random() * ((grid.h - 1) - 0) + 1) + 0);
            if (w1 == x && w2 == y) {
                i--;
                continue;
            }

            if (grid.sequers[w1][w2].isMine == false) {
                grid.sequers[w1][w2].isMine = true;

                for (int k = w1 - 1; k < w1 + 2; k++) {
                    for (int l = w2 - 1; l < w2 + 2; l++) {
                        if (k < 0 || k > grid.w - 1 || l < 0 || l > grid.h - 1 || (k == w1 && l == w2)) {
                            continue;
                        }
                        if (!grid.sequers[k][l].isMine) {
                            grid.sequers[k][l].minesNearBy++;
                        }

                    }
                }
            } else {
                i--;
            }

        }
    }

    public void onSequerClicked(int x, int y, boolean isLeft) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (gameEnded) {
            return;
        }
        if (firstopen == false) {
//     
            this.distrbuteMines(x, y);
            firstopen = true;
        }
        gui.setplayerturn(x, y);
        grid.playerturn++;

        grid.computerturn++;
        if (Grid.vscomputer && grid.computerturn % 2 == 0 && isLeft) {

            gameEnded = true;
            computer();
            gameEnded = false;

        } else {
            calculateTheScore(x, y, isLeft);

            try {
                applyAction(x, y, isLeft);
            } catch (Exception ex) {
           
        }
        }

        if (grid.leftcelles == 0) {
            stoptimer();
            Icon reseticon = new ImageIcon("win.jpg");
            reset.setIcon(reseticon);
            Square sq = grid.sequers[x][y];
            gui.setIconToButton(sq, gui.buttonsGrid[x][y], false);
            gui.refreshGUI(this.grid, true);
               String soundName = "win.wav";
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
             
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                   if(MineSweeper3.ison)
                clip.start();
        } else if (grid.sequers[x][y].isMine && isLeft) {
            if (grid.sequers[x][y].isFlag) {
                return;
            }
            if (Grid.vscomputer && grid.computerturn % 2 == 0) {
                // computer();
                gui.refreshGUI(grid, false);
                return;

            }
            stoptimer();
            Icon reseticon = new ImageIcon("lose.jpg");
            reset.setIcon(reseticon);
            grid.player.score -= grid.sequers[x][y].minesNearBy;
            gui.setscore();

            gui.refreshGUI(this.grid, true);
            grid.playerturn++;
            grid.computerturn++;
        } else {
           // new Thread(() -> grid.save()).start();
            gui.refreshGUI(this.grid, false);
        }
        //  }
    }

    void openSq(int x, int y) {

        grid.sequers[x][y].isOpen = true;

        grid.leftcelles--;
        if (Grid.multiplayer) {
            if (grid.playerturn % 2 == 1) {
                grid.player.score++;
            } else {
                grid.player2.score++;
            }

        } else {
            grid.player.score++;
        }

        gui.setscore();

        if (grid.sequers[x][y].isFlag) {
            grid.sequers[x][y].isFlag = false;
            if (Grid.multiplayer) {
                if (grid.playerturn % 2 == 1) {
                    grid.player.score++;
                } else {
                    grid.player2.score++;
                }

            } else {
                grid.player.score++;
            }

            gui.setscore();

            grid.numOfFlags++;
            gui.setFlagNum();

        }
    }

    void FloodFill(int x, int y) {

        if (x > grid.w - 1 || y > grid.h - 1 || x < 0 || y < 0 || grid.sequers[x][y].isOpen) {
            return;
        }
        if (grid.sequers[x][y].minesNearBy != 0) {
            openSq(x, y);
            return;
        }
        openSq(x, y);

        for (int k = x - 1; k < x + 2; k++) {
            for (int l = y - 1; l < y + 2; l++) {
                if (k == x && l == y) {
                    continue;
                }

                FloodFill(k, l);

            }
        }

    }

    void applyAction(int x, int y, boolean isLeft) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        change the model based on the clicked sequer;

        if (grid.sequers[x][y].isOpen) {
            return;
        }

        if (!isLeft) {

            //if (grid.sequers[x][y].isFlag2) 
            //  grid.sequers[x][y].isFlag2=false;
            if (grid.sequers[x][y].isFlag == false) {
                grid.sequers[x][y].isFlag = true;
                grid.numOfFlags--;

            } else {
                grid.sequers[x][y].isFlag = false;
                grid.numOfFlags++;
            }
            grid.isonceflaged[x][y] = true;
            gui.setFlagNum();
        } else {

            if (grid.sequers[x][y].isFlag) {
                return;
            }

            if (grid.sequers[x][y].isMine) {

                String soundName = "lose_minesweeper.wav";
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                   if(MineSweeper3.ison)
                clip.start();

                return;
            }
            
            if (grid.sequers[x][y].minesNearBy != 0) {
                  String soundName = "click.wav";
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                   if(MineSweeper3.ison)
                clip.start();
                grid.sequers[x][y].isOpen = true;
                grid.leftcelles--;
            } else {
                FloodFill(x, y);
            }

        }
    }

    void calculateTheScore(int x, int y, boolean isLeft) {

        if (grid.sequers[x][y].isOpen) {
            return;
        }

        if (!isLeft) {

            if (grid.sequers[x][y].isMine && !grid.sequers[x][y].isFlag) {
                if (Grid.multiplayer) {
                    if (grid.playerturn % 2 == 1) {
                        grid.player.score += 5;
                    } else {
                        grid.player2.score += 5;
                    }

                } else {
                    grid.player.score += 5;
                }

            } else if (!grid.sequers[x][y].isMine && !grid.sequers[x][y].isFlag) {
                if (Grid.multiplayer) {
                    if (grid.playerturn % 2 == 1) {
                        grid.player.score -= 1;
                    } else {
                        grid.player2.score -= 1;
                    }

                } else {
                    grid.player.score -= 1;
                }

            }

        } else {

            timer();
            if (grid.sequers[x][y].isFlag) {
                return;
            }
            if (grid.leftcelles == 1 && !Grid.multiplayer) {
                for (int i = 0; i < grid.w; i++) {
                    for (int j = 0; j < grid.h; j++) {
                        if (grid.sequers[i][j].isMine && grid.sequers[i][j].isFlag == false && grid.isonceflaged[x][y] == false) {
                            grid.player.score += 100;
                        }

                    }
                }

            }

            if (grid.sequers[x][y].minesNearBy != 0) {
                if (Grid.multiplayer) {
                    if (grid.playerturn % 2 == 1) {
                        grid.player.score += grid.sequers[x][y].minesNearBy;
                    } else {
                        grid.player2.score += grid.sequers[x][y].minesNearBy;
                    }

                } else {
                    grid.player.score += grid.sequers[x][y].minesNearBy;
                }
            }

        }

        gui.setscore();

    }
    public JButton reset = new JButton();

    public void reset() {
        Icon numbericon = new ImageIcon("images.jpg");
        reset.setIcon(numbericon);

        reset.setBounds((grid.w * 30 + 16) / 2 - 24, 0, 30, 30);
        gui.frame.add(reset);
        reset.addActionListener((ActionEvent e) -> {
            Icon reseticon = new ImageIcon("images.jpg");
            reset.setIcon(reseticon);
            grid.k = 1;
            grid.reset();
            gui.setFlagNum();
            grid.player.score = 0;
            if (Grid.multiplayer) {
                grid.player2.score = 0;
            }
            grid.playerturn = 0;

            grid.computerturn = 0;

            gui.setscore();
            gui.setTime();
            GameMaster.gameEnded = false;
            gui.refreshGUI(grid, false);
        });

    }

    public void save() {
        JButton save = new JButton("save");

        save.setBounds(0, 0, 80, 30);
        // if (!Grid.multiplayer) {
        gui.frame.add(save);
        //  }
        save.addActionListener((ActionEvent e) -> {
            
            
            
            new Thread(() -> grid.save()).start();
            
            
                
            
        });

    }

    void computer() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
        }
        int w1 = (int) ((random() * (((grid.w - 1) - 0) + 1)) + 0);
        int w2 = (int) ((random() * ((grid.h - 1) - 0) + 1) + 0);

        boolean is = false;
        for (int i = w1; i < grid.w - 1; i++) {
            for (int j = w2; j < grid.h - 1; j++) {
                if (!grid.sequers[i][j].isOpen && !grid.sequers[i][j].isFlag && grid.sequers[i][j].minesNearBy != 0) {
                    is = true;
                    if (grid.sequers[i][j].isMine && grid.sequers[i][j].isFlag == false) {
                        grid.sequers[i][j].isFlag = true;
                        grid.numOfFlags--;
                        gui.setFlagNum();
                    } else {
                        grid.sequers[i][j].isOpen = true;

                        grid.leftcelles--;
                    }
                    return;
                }
            }
        }
        if (!is) {
            for (int i = w1; i >= 0; i--) {
                for (int j = w2; j >= 0; j--) {
                    if (!grid.sequers[i][j].isOpen && !grid.sequers[i][j].isFlag && grid.sequers[i][j].minesNearBy != 0) {
                        if (grid.sequers[i][j].isMine && grid.sequers[i][j].isFlag == false) {
                            grid.sequers[i][j].isFlag = true;
                            grid.numOfFlags--;
                            gui.setFlagNum();
                        } else {
                            grid.sequers[i][j].isOpen = true;

                            grid.leftcelles--;
                        }
                        return;

                    }
                }
            }
        }
    }
}
