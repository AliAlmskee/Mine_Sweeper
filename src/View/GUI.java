package View;

//        Thread.sleep(500);
import Controller.GameMaster;

import Model.Grid;
import Model.Square;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import mine3.MineSweeper3;

public class GUI {

    public JFrame frame = new JFrame(" Mine Sweeper ");
    GameMaster gameMaster;
    public JButton[][] buttonsGrid;
    int w, h;

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public GUI(int w, int h, GameMaster gameMaster) {
        this.gameMaster = gameMaster;
        this.buttonsGrid = new JButton[w][h];
        this.w = w;
        this.h = h;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.buttonsGrid[i][j] = new JButton();
                this.buttonsGrid[i][j].addMouseListener(new ClickHandler(i, j, this));
            }
        }
    }

    public void setGameMaster(GameMaster gm) {
        this.gameMaster = gm;
    }

//    نحنا عم نخبر الكونترولر انو اليوزر اتفاعل مع الواجهة وكبس زر
    void onMouseClick(int x, int y, boolean isLeft) {
        try {
            this.gameMaster.onSequerClicked(x, y, isLeft);
        } catch (UnsupportedAudioFileException |LineUnavailableException |IOException ex) {
     
        
    }
    }
    public void setIconToButton(Square sq, JButton button, boolean gameended) {
        Icon numbericon = null;
        if (gameended) {

            if (sq.isMine) {
                if(gameMaster.grid.vscomputer && gameMaster.grid.computerturn%2==0)
                    return ;

                numbericon = new ImageIcon("download (3).jpg");
                button.setIcon(numbericon);
                
                //  button.setBackground(Color.red);

                return;
            } else {
                return;
            }
        } else {

            if (sq.isFlag) {
               if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                  //  sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("flag p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("flag p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("flag p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("flag p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("download.jpg");
                }
                button.setIcon(numbericon);

                return;
            }
            if (!sq.isOpen) {

                numbericon = new ImageIcon("Minesweeper_unopened_square.svg.jpg");
                button.setIcon(numbericon);
                return;
            } else if (sq.minesNearBy == 0) {
               if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("uncoverd p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("uncoverd p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("uncoverd p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("uncoverd p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("Minesweeper_0.svg.jpg");
                }
            } else if (sq.minesNearBy == 1) {

                if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("1 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("1 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("1 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("1 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("download (1).jpg");
                }
            } else if (sq.minesNearBy == 2) {
                if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("2 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("2 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("2 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("2 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("download (2).jpg");
                }
            } else if (sq.minesNearBy == 3) {
                if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("3 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("3 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("3 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("3 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("images (1).jpg");
                }
            } else if (sq.minesNearBy == 4) {

               if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("4 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("4 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("4 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("4 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("images (2).jpg");
                }
            } else if (sq.minesNearBy == 5) {
                if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("5 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("5 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("5 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("5 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("Minesweeper_5.svg.jpg");
                }
            } else if (sq.minesNearBy == 6) {
                 if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("6 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("6 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("6 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("6 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("images (3).jpg");
                }
            } else if (sq.minesNearBy == 7) {
             if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("7 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("7 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("7 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("7 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("images (4).jpg");
                }
            } else if (sq.minesNearBy == 8) {
                if (Grid.multiplayer) {
                    if (sq.isOpen2) {
                        return;
                    }
                    sq.isOpen2 = true;
                    if( sq.numperofplayer==0){
                    if (gameMaster.grid.playerturn % 2 == 0) {
                        
                        numbericon = new ImageIcon("8 p1.jpg");
                         sq.numperofplayer = 1 ;
                    } else {
                        numbericon = new ImageIcon("8 p2.jpg");
                         sq.numperofplayer = 2;
                    }
                    }
                    else
                    {
                        if( sq.numperofplayer==1)
                             numbericon = new ImageIcon("8 p1.jpg");
                        else if( sq.numperofplayer==2)
                            numbericon = new ImageIcon("8 p2.jpg");
                    }

                } else {
                    numbericon = new ImageIcon("images (5).jpg");
                }
            }
        }

        button.setIcon(numbericon);
    }

//    الكونترولر رح يخبرني عن طريق هي الميثود انو الموديل اتحدث وانا لازم حدث الواجهة
    public void refreshGUI(Grid grid, boolean gameended) {

        for (int i = 0; i < this.w; i++) {
            for (int j = 0; j < this.h; j++) {
                Square sq = grid.sequers[i][j];
                setIconToButton(sq, this.buttonsGrid[i][j], gameended);

            }
        }

        if (gameended) {
               if(gameMaster.grid.vscomputer && gameMaster.grid.computerturn%2==0)
                    return ;
            GameMaster.gameEnded = true;
            gameMaster.stoptimer();
            finalScore();
            score.setText(String.valueOf(gameMaster.grid.player.score));

            score2.setText(String.valueOf(gameMaster.grid.player2.score));

            
        }
    }

    public void setplayerturn(int x, int y) {
        if (Grid.multiplayer) {
            if (gameMaster.grid.sequers[x][y].isOpen) {
                return;
            }
            if (gameMaster.grid.playerturn % 2 == 1) {
                score.setForeground(Color.RED);
                score2.setForeground(Color.BLACK);
            } else {
                score2.setForeground(Color.RED);
                score.setForeground(Color.BLACK);
            }

        }
    }

    void finalScore() {
        JFrame fr = new JFrame(" SCORE ");
        fr.setSize(300, 340);
        ImageIcon img = new ImageIcon("mainicon.png");
        fr.setIconImage(img.getImage());

        JLabel background;
        if (Grid.multiplayer) {

            JLabel Lb1 = new JLabel(String.valueOf("         player 1 score  :  " + gameMaster.grid.player.score));
            JLabel Lb2 = new JLabel(String.valueOf("         player 2 score  :  " + gameMaster.grid.player2.score));
            JLabel result = new JLabel(" ");

            if (gameMaster.grid.leftcelles != 0) {
                background = new JLabel(new ImageIcon("lose2.png"));
                fr.setContentPane(background);

                if (gameMaster.grid.playerturn % 2 == 0) {
                    result.setText("Player 1 Won");
                } else {
                    result.setText("Player 2 Won");
                }
            } else {
                background = new JLabel(new ImageIcon("win2.png"));
                fr.setContentPane(background);

                if (gameMaster.grid.player.score > gameMaster.grid.player2.score) {
                    result.setText("Player 1 Won");
                } else if (gameMaster.grid.player.score < gameMaster.grid.player2.score) {
                    result.setText("Player 2 Won");
                } else {
                    result.setText("        Draw");
                }

            }
            result.setBounds(105,28,90,36);
            Lb1.setBounds(-15, 82, 160, 20);
            Lb2.setBounds(  135, 82, 160, 20);
            result.setForeground(Color.white);
            Lb1.setForeground(Color.white);
            Lb2.setForeground(Color.white);
            fr.add(result);
            fr.add(Lb1);
            fr.add(Lb2);
        } else {
            if (gameMaster.grid.leftcelles != 0) background = new JLabel(new ImageIcon("lose1.png"));
            else background = new JLabel(new ImageIcon("win1.png"));
            fr.setContentPane(background);

            JLabel Lb1 = new JLabel(String.valueOf("Your score is :  " + gameMaster.grid.player.score));
            Lb1.setForeground(Color.white);
            Lb1.setBounds(90, 27, 160, 36);
            fr.add(Lb1);
        }

        fr.setLayout(null);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setResizable(false);
    }

    class ClickHandler implements MouseListener {

        int x, y;
        GUI gui;

        ClickHandler(int x, int y, GUI gui) {
            this.x = x;
            this.y = y;
            this.gui = gui;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                this.gui.onMouseClick(this.x, this.y, false);
            }
            if (SwingUtilities.isLeftMouseButton(e)) {
                this.gui.onMouseClick(this.x, this.y, true);

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    JLabel score2 = new JLabel();
    JLabel timer = new JLabel();
    JLabel score = new JLabel();
       JLabel numberOfFlages = new JLabel();

    public void showgrid(Grid grid) {
        final int EADG_SIZEX = 16;
        final int EADG_SIZEY = 41;
        numberOfFlages = new JLabel(String.valueOf(gameMaster.grid.getnumOfFlags()));
        // عدل الثوابت لمتغيرات نهائية
        frame.setSize(grid.w * 30 + EADG_SIZEX, (grid.h + 1) * 30 + EADG_SIZEY);
        gameMaster.reset();
        gameMaster.save();
        timer.setBorder(BorderFactory.createTitledBorder("Time "));
        timer.setBounds((grid.w * 30 + 16) - 76, 0, 60, 33);
        frame.add(timer);
        gameMaster.grid.t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setText(String.valueOf(gameMaster.grid.k));
                ++gameMaster.grid.k;
            }
        ;
        });
                 
        numberOfFlages.setBounds(100, 0, 50, 32);
        numberOfFlages.setBorder(BorderFactory.createTitledBorder("Flags"));
        frame.add(numberOfFlages);

        setscore();
        score.setBorder(BorderFactory.createTitledBorder("Score"));
        score.setBounds((grid.w * 30 + 16) - 156, 0, 60, 33);

        frame.add(score);

        for (int i = 0; i < grid.w; i++) {
            for (int j = 0; j < grid.h; j++) {

                buttonsGrid[i][j].setBounds(i * 30, (j + 1) * 30 + 2, 30, 30);
                Square sq = grid.sequers[i][j];
                setIconToButton(sq, buttonsGrid[i][j], false);
                frame.add(buttonsGrid[i][j]);

            }
        }
        if (Grid.multiplayer) {

            score2.setBounds((grid.w * 30 + 16) - 210, 0, 60, 33);
            score2.setBorder(BorderFactory.createTitledBorder("score2"));
          if(!  " Biginner ".equals(MineSweeper3.s))
          {
              
            frame.add(score2);
          //  MineSweeper3.s= " Biginner " ;
          }
        }
         if (!Grid.multiplayer) {
              frame.remove(score2);
         }
        ImageIcon img = new ImageIcon("mainicon.png");
        frame.setIconImage(img.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //frame.setResizable(false);
        frame.setVisible(true);

    }

    public void setFlagNum() {
        numberOfFlages.setText(String.valueOf(gameMaster.grid.getnumOfFlags()));
    }

    public void setscore() {
        if (gameMaster.grid.player.score <= 10) {
            score.setText(String.valueOf(gameMaster.grid.player.score));
        } else {
            score.setText(String.valueOf(10));
        }
        if (Grid.multiplayer) {

            if (gameMaster.grid.player2.score <= 10) {
                score2.setText(String.valueOf(gameMaster.grid.player2.score));
            } else {
                score2.setText(String.valueOf(10));
            }
        }
    }

    public void setscore(int x) {

        score.setText(String.valueOf(x));

    }

    public void setTime() {

        timer.setText(String.valueOf(gameMaster.grid.k));
    }
}
