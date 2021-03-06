package main;



import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pieces.*;

public class Shogi extends javax.swing.JFrame implements Connection.OnDataReceived{
    
    public static boolean myTurn; // czy jest twoja kolej czy nie
    
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(label)
                .addGap(109, 109, 109))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(529, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(label))
                .addContainerGap())
        );

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

    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
        ((MyPanel)panel).startButtonClicked();
    }//GEN-LAST:event_startButtonMouseClicked

    public static void changeTurn(boolean turn){
        myTurn = turn;
        if(myTurn == true) setText("Your Turn");
        else  setText("Wait for opponent");
    }

    public static boolean chooseClientServer(){
        String[] possibleValues = { "Client", "Server"};
        String selectedValue = (String)JOptionPane.showInputDialog(null,"Choose", "Input",
        JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[1]);
        return (selectedValue.compareTo("Server") == 0);
    }
    
    public static void setText(String napis){
        label.setText(napis);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {  
        
        Connection connection = Connection.getInstance();
        connection.isServer = chooseClientServer();        
        new Thread(connection).start();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Shogi shogi = new Shogi();
                shogi.setVisible(true);
                
                Connection connection = Connection.getInstance();
                connection.listener = shogi;
                
                //Klient ma pierwszy ruch
                changeTurn(!connection.isServer);
            }
        });
          
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel label;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panel;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onDataReceived(Move data) {
        ((MyPanel)panel).onOponentMove(data);
//        changeTurn(true);
    }
}
class MyPanel extends JPanel
{    
    Point current = null; 
    Piece cpiece = null;
    
    
    Player localPlayer;
    Player netPlayer;
    
    Purgatory localPurgatory;
    Purgatory netPurgatory;
        
    Gameboard board;
    
    int lastPurgitoryItem = -1;
    
    MyPanel()
    {
        board = new Gameboard(20,0);
        
        localPurgatory = new Purgatory(540, 6*50+20);
        localPlayer = new Player(false);
        localPlayer.setPieces(board);
        
        netPurgatory = new Purgatory(540, 35);
        netPlayer = new Player(true);
        netPlayer.setPieces(board);
    }
        
   
    // Game Mechanics in mouseClicked
    void mouseClicked(int mouseX, int mouseY)
    {
        if(Shogi.myTurn == false) return;
        
        localPurgatory.clearSelection();
        board.clearSelecion();
        board.clearPossibleMoves();
        
        if(onPurgitoryClicked(mouseX, mouseY)) return;
        onBoardClicked(mouseX, mouseY);
    }
    
    // true - jeśli obsłuzone kliknięcie, false jeśli nie
    public boolean onBoardClicked(int mouseX, int mouseY){
        Point next = board.checkCoordinates(mouseX, mouseY);   
        if(next == null) return false;
        
        // gdy dodano element z "czyśca"
        if(lastPurgitoryItem != -1) {
            localPurgatory.selectItem(lastPurgitoryItem);
            if(localPurgatory.couldMove(next, board)) {
                addPieceFromPurgitory(next);
                localPurgatory.clearSelection();
                Shogi.changeTurn(false);
                return true;
            }
            localPurgatory.clearSelection();
        }
        lastPurgitoryItem = -1;
                   
       // gdy wykoanano ruch 
       if (board.couldMove(current, next)){ 
                System.out.println("Moved from "+current+" to "+next);
                
                Piece moved = board.getField(current.x, current.y);
                Piece beaten = board.movePiece(current, next);
                                
                Move move = new Move(current, next, moved.promoted);
                Connection.getInstance().sendString(move.toString());
                
                if(beaten != null) {
                    endGame(beaten);
                    System.out.println("Beaten "+beaten);
                    localPurgatory.addPiece(beaten);
                }
                board.clearSelecion();
                Shogi.changeTurn(false);
                return true;
        }

        // gdy zaznaczono jakieś pole 
        board.selectField(next.x, next.y);
        current = new Point(next.x, next.y);
        return true;
    }
    
    public void addPieceFromPurgitory(Point next){
        Piece piece = localPurgatory.getPiece(lastPurgitoryItem, localPlayer);
        board.setField(next.x, next.y, piece);
        localPurgatory.removePiece(piece);
        
        Move move = new Move(next, next, false, lastPurgitoryItem);
        Connection.getInstance().sendString(move.toString());
    }
    
    public boolean onPurgitoryClicked(int mouseX, int mouseY){
        int purgitoryItem = localPurgatory.checkCoordinates(mouseX, mouseY);
        if(purgitoryItem != -1) {
            localPurgatory.selectItem(purgitoryItem);
            List<Point> possibleMoves = localPurgatory.getPossibleMoves(board);
            board.setPossibleMoves(possibleMoves);
            lastPurgitoryItem = purgitoryItem;
            return true;
        }
        return false;
    }
    
    public void onOponentMove(Move move) {
        
        // gdy figura została dodana z "czyśca"
        if(move.pieceType != 9) {
            Point to = board.mirror(move.to);
            Piece piece = localPurgatory.getPiece(move.pieceType, netPlayer);
            board.setField(to.x, to.y, piece);
            netPurgatory.removePiece(piece);
            return;
        }
        
        
        Point from = board.mirror(move.from);
        Point to = board.mirror(move.to);
        
        Piece moved = board.getField(from.x, from.y);
        moved.promoted = move.withPromotion;
        
        Piece beaten = board.movePiece(from, to);
        
        if(beaten != null) {
            if(endGame(beaten)) return;
            System.out.println("Beaten "+beaten);
            netPurgatory.addPiece(beaten);
        }
        
        Shogi.changeTurn(true);
    }
    
    void startButtonClicked(){  
    }
    
    @Override
    public void paintComponent (Graphics g)
    {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());        
        board.draw(g);
        localPurgatory.draw(g);
        netPurgatory.draw(g);
    }

    // sprawdza czy ktoś nie został nieoszlifowanym zwycięscą
    public boolean endGame(Piece beaten){
        if(beaten instanceof King && beaten.owner == localPlayer) {
            JOptionPane.showMessageDialog(this, "You lost");
            Shogi.changeTurn(false);
            return true;
        } else if(beaten instanceof King && beaten.owner == netPlayer) {
            JOptionPane.showMessageDialog(this, "You won");
             Shogi.changeTurn(false);
             return true;
        }
        return false;
    } 
        
    
}