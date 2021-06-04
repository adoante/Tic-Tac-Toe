import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

public class GUIBoard implements ActionListener {
  //Makes the JFrame and JPanel global
  private JPanel panel;
  private JFrame frame;

  //Creates a 2D array of 9 JLables to act like Tic Tac Toe squares
  public JLabel[][] squares = new JLabel[3][3];

  //Constructor used to construct the GUI in Main
  public GUIBoard() {
    //sets frame
    frame = new JFrame();
    //Create the JPanel
    panel = new JPanel();

    //Populates the 2D array with 9 Jlabels
    int squareNum = 1;
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares[0].length; j++) {
        squares[i][j] = new JLabel(Integer.toString(squareNum), SwingConstants.CENTER);
        //Sets Lables titles font and size
        Font newLabelFont = new Font(squares[i][j].getFont().getName(),squares[i][j].getFont().getStyle(),64);
        squares[i][j].setFont(newLabelFont);
        squareNum++;
      }
    }

    //Sets the JLabel boarders to appear like a TicTacToe Board
    squares[0][0].setBorder(BorderFactory.createMatteBorder(0,0,1,1, Color.black));
    squares[0][1].setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.black));
    squares[0][2].setBorder(BorderFactory.createMatteBorder(0,1,1,0, Color.black));
    squares[1][0].setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.black));
    squares[1][1].setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
    squares[1][2].setBorder(BorderFactory.createMatteBorder(0,1,0,0, Color.black));
    squares[2][0].setBorder(BorderFactory.createMatteBorder(1,0,0,1, Color.black));
    squares[2][1].setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.black));
    squares[2][2].setBorder(BorderFactory.createMatteBorder(1,1,0,0, Color.black));

    //Adds 2D array of JLables to the JPanel
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares[0].length; j++) {
        panel.add(squares[i][j]);
      }
    }

    //Sets the JPanel Boarder
    panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    //Sets the JPanel Layout
    panel.setLayout(new GridLayout(3,3));

    //Puts panel on JFrame
    frame.add(panel, BorderLayout.CENTER);
    //Sets the 'X' to actually close
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Sets the title
    frame.setTitle("BUSSY");
    //Makes the frame visable
    frame.setVisible(true);
    //Makes the frame size to be 500x500 pixels
    frame.setSize(500,500);
    //Makes the frame (un)resizeable
    frame.setResizable(false);
    //Creates a new Icon Image object
    ImageIcon image = new ImageIcon("Fish Tank.jpg");
    //Sets the icon to the image object
    frame.setIconImage(image.getImage());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //Add stuff here
  }

  // public static void main(String[] args) {
  //   new GUIBoard();
  // }

  }
