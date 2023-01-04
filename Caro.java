package Caro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Caro extends JPanel implements ActionListener {

    JPanel panelNorth = new JPanel();
    JPanel panelWest = new JPanel();
    JPanel panelEast = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panelCenter = new JPanel();

    public JFrame frame = new JFrame();
    public int n = 19, m = 19, num = 0, diem = 0;
    public JButton btn[][] = new JButton[n][m];
    int pos[][] = new int[m][n];

    public void add() {
        frame.setLayout(new BorderLayout());

        panelNorth.setBackground(Color.blue);
        panelEast.setBackground(Color.blue);
        panelSouth.setBackground(Color.blue);
        panelWest.setBackground(Color.blue);

        JLabel lbHeader = new JLabel("GAME CARO");

        panelNorth.add(lbHeader);
        lbHeader.setForeground(Color.red);
        ;

        JLabel lbPlayer1 = new JLabel("Người chơi 1");
        lbPlayer1.setForeground(Color.red);
        JLabel lbImagePlayer1 = new JLabel();

        panelWest.add(lbImagePlayer1);
        panelWest.add(lbPlayer1);

        JLabel lbPlayer2 = new JLabel("Nguời chơi 2");
        lbPlayer2.setForeground(Color.red);
        JLabel lbImagePlayer2 = new JLabel();

        panelEast.add(lbPlayer2);
        panelEast.add(lbImagePlayer2);

        JButton btnNewgame = new JButton("New Game");

        btnNewgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Trò Chơi Mới", "Thoát", JOptionPane.INFORMATION_MESSAGE);
                for (int i1 = 0; i1 < n; i1++) {
                    for (int j1 = 0; j1 < m; j1++) {
                        btn[i1][j1].setText("");
                        btn[i1][j1].setBackground(Color.white);
                    }
                }
            }
        });

        panelSouth.add(btnNewgame);

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelEast, BorderLayout.EAST);
        frame.add(panelWest, BorderLayout.WEST);
        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.add(panelCenter);
        panelCenter.setLayout(new GridLayout(n, m));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                pos[i][j] = num;
                num++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                btn[i][j] = new JButton();
                btn[i][j].addActionListener(this);
                panelCenter.add(btn[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                btn[i][j].setBackground(Color.white);
            }
        }
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        panelCenter.setSize(800, 10000);
        panelEast.setSize(100, 10000);
        panelWest.setSize(100, 10000);
        frame.setSize(1500, 800);
        frame.setResizable(true);

    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (e.getSource() == btn[i][j] && btn[i][j].getText() != "X" && btn[i][j].getText() != "O") {
                    if (diem % 2 == 0) {

                        btn[i][j].setText("X");
                        btn[i][j].setForeground(Color.RED);
                        diem++;
                        if (win(i, j, btn[i][j].getText())) {
                            btn[i][j].setBackground(Color.red);
                            JOptionPane.showMessageDialog(null, "X win!", "Game Over!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            for (int i1 = 0; i1 < n; i1++) {
                                for (int j1 = 0; j1 < m; j1++) {
                                    btn[i1][j1].setText("");
                                    btn[i1][j1].setBackground(Color.white);
                                }
                            }
                        }
                    } else {

                        btn[i][j].setText("O");
                        btn[i][j].setForeground(Color.BLACK);
                        diem++;
                        if (win(i, j, btn[i][j].getText())) {
                            btn[i][j].setBackground(Color.green);
                            JOptionPane.showMessageDialog(null, "O win!", "Game Over!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, "Trò Chơi Mới", "Thoát",
                                    JOptionPane.INFORMATION_MESSAGE);
                            for (int i1 = 0; i1 < n; i1++) {
                                for (int j1 = 0; j1 < m; j1++) {
                                    btn[i1][j1].setText("");
                                    btn[i1][j1].setBackground(Color.white);
                                }
                            }
                        }
                    }

                }

            }
        }
    }

    public boolean win(int x, int y, String name) {
        int k, j;
        int d = 0;

        for (k = -4; k <= 4; k++) {
            if (x + k >= 0 && x + k < n) {
                if (btn[x + k][y].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }

        for (k = -5; k <= 5; k++) {
            if (y + k >= 0 && y + k < n) {
                if (btn[x][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }

        for (k = -4, j = 4; k <= 4 && j >= -4; k++, j--) {
            if (y + k >= 0 && y + k < n && x + j >= 0 && x + j < m) {
                if (btn[x + j][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        for (k = -4; k <= 4; k++) {
            if (y + k >= 0 && y + k < n && x + k >= 0 && x + k < m) {
                if (btn[x + k][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        Caro c = new Caro();
        c.add();
    }
}