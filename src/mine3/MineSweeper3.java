/*hjhbj
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package mine3;

import Controller.GameMaster;
import Model.Grid;
import View.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import javax.swing.UIManager.LookAndFeelInfo;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Ali-Almski
 */
public class MineSweeper3 {

    static int w = 0, h = 0, numberOfMines = 0;
    public static String s;
    public static boolean ison = true;

    public class NimbusButton {

        private LookAndFeel nimbus;

        public JButton generateNimbusButton() {
            try {
                LookAndFeel current = UIManager.getLookAndFeel(); //capture the current look and feel

                if (nimbus == null) { //only initialize Nimbus once
                    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                    nimbus = UIManager.getLookAndFeel();
                } else {
                    UIManager.setLookAndFeel(nimbus); //set look and feel to nimbus
                }
                JButton button = new JButton(); //create the button
                UIManager.setLookAndFeel(current); //return the look and feel to its original state
                return button;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                return new JButton();
            }
        }
    }

    static void start() {
        JFrame start = new JFrame(" Mine Sweeper ");
        start.setSize(436, 489);

        JLabel background = new JLabel(new ImageIcon("background.png"));
        start.setContentPane(background);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            SwingUtilities.updateComponentTreeUI(start);
        } catch (UnsupportedLookAndFeelException ignored) {
        }

        String[] arr = {" Beginner ", " Intermediate ", " Expert "};
        JComboBox cb = new JComboBox(arr);
        cb.setSelectedIndex(1);
        cb.setBounds(93, 243, 250, 33);
        start.add(cb);

        JButton new_Game = new JButton(new ImageIcon("newGame.png"));
        new_Game.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        new_Game.getModel().addChangeListener(new ChangeListener() {
            final ImageIcon normal = new ImageIcon("newGame.png");
            final ImageIcon hover = new ImageIcon("newGameH.png");

            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    new_Game.setIcon(hover);
                } else {
                    new_Game.setIcon(normal);
                }
            }
        });
        JButton old_Game = new JButton(new ImageIcon("lastGame.png"));
        old_Game.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        old_Game.getModel().addChangeListener(new ChangeListener() {
            final ImageIcon normal = new ImageIcon("lastGame.png");
            final ImageIcon hover = new ImageIcon("lastH.png");

            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    old_Game.setIcon(hover);
                } else {
                    old_Game.setIcon(normal);
                }
            }
        });
        JButton autoplayer = new JButton(new ImageIcon("pwc.png"));
        autoplayer.setBorder(BorderFactory.createLineBorder(Color.black
        ));
        autoplayer.getModel().addChangeListener(new ChangeListener() {
            final ImageIcon normal = new ImageIcon("pwc.png");
            final ImageIcon hover = new ImageIcon("pwcH.png");

            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    autoplayer.setIcon(hover);
                } else {
                    autoplayer.setIcon(normal);
                }
            }
        });
        JButton multiplayer = new JButton(new ImageIcon("pvp.png"));
        multiplayer.setBorder(BorderFactory.createLineBorder(Color.black));
        multiplayer.getModel().addChangeListener(new ChangeListener() {
            final ImageIcon normal = new ImageIcon("pvp.png");
            final ImageIcon hover = new ImageIcon("pvpH.png");

            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    multiplayer.setIcon(hover);
                } else {
                    multiplayer.setIcon(normal);
                }
            }
        });

        //buttons Bounds
        old_Game.setBounds(93, 30, 250, 36);
        new_Game.setBounds(93, 90, 250, 100);
        autoplayer.setBounds(93, 200, 120, 33);
        multiplayer.setBounds(223, 200, 120, 33);

        //Action listners
        new_Game.addActionListener((ActionEvent e) -> {
            set_diff(cb);

            GameMaster.firstopen = false;
            Grid.multiplayer = false;
            Grid.vscomputer = false;
            GameMaster.gameEnded = false;
            Grid grid = new Grid(w, h, numberOfMines);
            GUI gui = new GUI(w, h, null);
            GameMaster gm = new GameMaster(grid, gui);
            gui.setGameMaster(gm);
            gui.showgrid(grid);
            // start.setVisible(false);
        });
        old_Game.addActionListener((ActionEvent e) -> {
            GameMaster.firstopen = true;
            GameMaster.gameEnded = false;
            Grid grid = Grid.continuefromlastposition();

            if (grid.ismulti == 2) {
                Grid.multiplayer = true;
              
                if (grid.state == 2) {
                    s = " Biginner ";
                }
            }
              else
                  Grid.multiplayer = false;

            if (grid.iswithcomputer == 2) {
                Grid.vscomputer = true;

            }
              else
                  Grid.vscomputer = false;
            grid.fopen2();
            GUI gui = new GUI(grid.w, grid.h, null);
            GameMaster gm = new GameMaster(grid, gui);
            gui.setGameMaster(gm);

            gui.showgrid(grid);
            // new Thread(() -> grid.save()).start();

            gui.setTime();
            // start.setVisible(false);
        });
        autoplayer.addActionListener((ActionEvent e) -> {
            set_diff(cb);
            Grid.multiplayer = false;

            GameMaster.gameEnded = false;
            Grid.vscomputer = true;
            GameMaster.firstopen = false;

            Grid grid = new Grid(w, h, numberOfMines);
            GUI gui = new GUI(w, h, null);
            GameMaster gm = new GameMaster(grid, gui);
            gui.setGameMaster(gm);
            gui.showgrid(grid);
            //  start.setVisible(false);
        });
        multiplayer.addActionListener((ActionEvent e) -> {
            set_diff(cb);
            Grid.vscomputer = false;
            Grid.multiplayer = true;
            GameMaster.firstopen = false;
            GameMaster.gameEnded = false;
            Grid grid = new Grid(w, h, numberOfMines);
            GUI gui = new GUI(w, h, null);
            GameMaster gm = new GameMaster(grid, gui);
            gui.setGameMaster(gm);
            gui.showgrid(grid);
            // start.setVisible(false);
        });

        start.add(multiplayer);
        start.add(new_Game);
        start.add(old_Game);
        start.add(autoplayer);

        //rules
        String s2 = "<html>";
        s2 += "  Rules :<br><br>  ";
        s2 += "        0)  the emoji button is the restart button<br><br>";
        s2 += "         1)  score will be visible under 10<br><br>";
        s2 += "         2)  when you put flag on mine , score + 5<br><br>";

        s2 += "         3)  when you put flag on nonmine , score - 1<br><br>";

        s2 += "         4)  when Flood fill , score + 1 for each cell<br><br>";
        s2 += "         5)  when you open a number , score + number<br><br>";
        s2 += "         6)  when you win, for every cell with mines and without flag ever , score + 100<br><br>";
        s2 += "        7)  when you play with computer, one click for you and one click for the computer<br><br>";
        s2 += "         8)  E N J O Y";
        JLabel rules = new JLabel();
        rules.setBounds(5, 40, 300, 380);
        rules.setText(s2);

        JButton Rules = new JButton(new ImageIcon("rules.png"));
        Rules.setBorder(BorderFactory.createLineBorder(Color.black
        ));
        Rules.getModel().addChangeListener(new ChangeListener() {
            final ImageIcon normal = new ImageIcon("rules.png");
            final ImageIcon hover = new ImageIcon("rulesH.png");

            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    Rules.setIcon(hover);
                } else {
                    Rules.setIcon(normal);
                }
            }

        });

        Rules.setBounds(326, 429, 90, 40);

        Rules.addActionListener((ActionEvent e) -> {
            JFrame Rules2 = new JFrame(" Rules ");
            ImageIcon img2 = new ImageIcon("mainicon.png");
            Rules2.setIconImage(img2.getImage());
            Rules2.setSize(436, 489);
            Rules2.setLocationRelativeTo(null);
            Rules2.add(rules);
            Rules2.setLayout(null);
            Rules2.setVisible(true);
        });

        start.add(Rules);

        //
        ImageIcon img = new ImageIcon("mainicon.png");
        start.setIconImage(img.getImage());

        JButton sound = new JButton();
        sound.setBounds(32, 429, 90, 40);
        start.add(sound);

        ImageIcon n = new ImageIcon("sound.jpg");
        sound.setIcon(n);
        sound.addActionListener((ActionEvent e) -> {
            if (ison) {
                ison = false;
                ImageIcon n2 = new ImageIcon("soundoff.jpg");
                sound.setIcon(n2);

            }
            else
            {
                  ison = true;
                ImageIcon n2 = new ImageIcon("sound.jpg");
                sound.setIcon(n2);
            
            }

        });
        start.pack();
        start.setResizable(false);
        start.setLocationRelativeTo(null);
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setLayout(null);
        start.setVisible(true);

    }

    static void set_diff(JComboBox cb) {
        if (cb.getSelectedIndex() == 0) {
            s = " Biginner ";
            w = 11;
            h = 11;
            numberOfMines = 18;
        } else if (cb.getSelectedIndex() == 1) {
            s = " Intermediate ";
            w = 14;
            h = 14;
            numberOfMines = 30;
        } else if (cb.getSelectedIndex() == 2) {
            s = " Expert ";
            w = 22;
            h = 22;
            numberOfMines = 30;
        }
    }

    public static void main(String[] args) {

        start();

    }
}
