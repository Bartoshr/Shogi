package main;



import main.Player;
import main.Gameboard;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import pieces.*;

public class Shogi extends javax.swing.JFrame {

    public Shogi() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new MyPanel();
        startButton = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        resetItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelMouseMoved(evt);
            }
        });
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMouseClicked(evt);
            }
        });

        startButton.setText("Rozpocznij grę");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });

        label.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        label.setText("Shogi by Bartosz Rumiński");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(startButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .addComponent(label)
                .addGap(199, 199, 199))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(517, Short.MAX_VALUE)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton)
                .addContainerGap())
        );

        menu.setText("Opcje");

        resetItem.setText("Reset");
        resetItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetItemActionPerformed(evt);
            }
        });
        menu.add(resetItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseClicked
        // TODO add your handling code here:
        if(evt.getButton()==evt.BUTTON1) {
         ((MyPanel)panel).mouseClicked(evt.getX(), evt.getY());
        }
        panel.repaint();

    }//GEN-LAST:event_panelMouseClicked

    private void panelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_panelMouseMoved

    private void resetItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetItemActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_resetItemActionPerformed

    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
        ((MyPanel)panel).startButtonClicked();
    }//GEN-LAST:event_startButtonMouseClicked

    
    public static void setText(String napis){
        label.setText(napis);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Shogi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel label;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panel;
    private javax.swing.JMenuItem resetItem;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
class MyPanel extends JPanel
{    
    Point current = null; 
    Piece cpiece = null;
    
    boolean graRozpoczeta = false;
    
    Player localPlayer;
    Player netPlayer;
        
    Gameboard board;
    
    MyPanel()
    {
        board = new Gameboard(20,0);
        
        localPlayer = new Player(false);
        localPlayer.setPieces(board);
        
        netPlayer = new Player(true);
        netPlayer.setPieces(board);
    }
        
   
    // Game Mechanics in mouseClicked
    void mouseClicked(int mouseX, int mouseY)
    {
        Point next = board.sprawdzWspolrzedne(mouseX, mouseY);   
        if(next == null) return;
           
                      
       if (board.couldMove(current, next)){ 
                System.out.println("Moved from "+current+" to "+next);
                board.movePiece(current, next);
                board.clearSelecion();
                return;
        }

        board.selectField(next.x, next.y);
        current = new Point(next.x, next.y);
    }
    
    void startButtonClicked(){
       
    }
    
    @Override
    public void paintComponent (Graphics g)
    {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());        
        board.draw(g);
    }

    // sprawdza czy ktoś nie został nieoszlifowanym zwycięscą
    public void czyKoniecGry(Gameboard plansza){
    }

    private Piece Point(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}