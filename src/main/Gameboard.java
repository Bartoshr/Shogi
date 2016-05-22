package main;


import pieces.Piece;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;



public class Gameboard {
    
    static int width = 50;
    static int heigth = 50;
    public final static int size = 9;
    
    Color green = new Color(0,255,0);
    Color red = new Color(255,0,0);
    Color black = new Color(0,0,0);
    
    int x, y; // położenie lewego górnego rogu
    Piece[][] pieces= new Piece[9][9];
    Rectangle[][] fields=new Rectangle[9][9]; // tablica pól planszy
    Point selected;
        
    Gameboard(int x, int y){
        this.x = x;
        this.y = y;
        createFields();
        clear();
    }
    
    void createFields() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {  
                int rectX = x+((i+1)*width);
                int rectY = y+((j+1)*heigth);
                fields[i][j] = new Rectangle(rectX, rectY, width, heigth);
            }
        }
    }
        
    void clear(){
        for(int i=0;i<pieces.length;i++)
            for(int j=0;j<pieces.length;j++)
                pieces[i][j]=null;
    }
            

   
    
    // zwraca współrzędne pola jeśli kliknięcie miało miejsce w jego obrębie
    // zwraca null w przecinwym wypadku
    // uwaga wspołrzędne rozpatrywane względem planszy
    public Point checkCoordinates(int x, int y){
        for(int i=0; i<size; i++) {
              for(int j=0; j<size; j++) {  
                  Rectangle rect = fields[i][j];
                  if(rect.contains(x, y)){
                      return new Point(i,j);
                  }
              }
            }
        return null;
    }
    
    public boolean couldMove(Point current, Point next){
        if(current == null || next == null) return false; // Just check
        
        Piece movedPiece = getField(current.x, current.y);
        if(movedPiece == null) return false; //if there is no piece return false
        if(movedPiece.couldMove(current, next, this)) return true; 
        return false;
    }
    
    
    // moves Piece from current position to next
    // and return beaten if exist
    public Piece movePiece(Point current, Point next){
        Piece beaten = pieces[next.x][next.y];
        pieces[next.x][next.y] = pieces[current.x][current.y];
        pieces[current.x][current.y] = null;
        return beaten;
    }
       
    // ustawia konkretną wartość na polu o indeksach x,y
    public void setField(int x, int y, Piece wartosc) {
        pieces[x][y] = wartosc;
    }
    
    // zwraca konkretną wartość na polu o indeksach x,y
    public Piece getField(int x, int y) {
        return pieces[x][y];
    }
    
    public void selectField(int x, int y){
        if(selected != null) {
            selected.x = x;
            selected.y = y;
        } else {
            selected = new Point(x,y);
        }
    }
    
    public Piece getSelected(){
        return pieces[selected.x][selected.y];
    }
    
    public void clearSelecion(){
        selected = null;
    }
    
    private void markField(Graphics g, Rectangle rect, Color color){
        g.setColor(color);
        g.fillRect(rect.x+1, rect.y+1, width-1, heigth-1);
        g.setColor(black);
    }
    
    public static Point mirror(Point point){
        return new Point(Math.abs(point.x-8), Math.abs(point.y-8));
    }
       
    void draw(Graphics g){
        
        // głowny szablon
        for(int i=0; i<size; i++) {
              for(int j=0; j<size; j++) {  
                  Rectangle rect = fields[i][j];
                  g.drawRect(rect.x, rect.y, rect.height, rect.width);           
                  if(selected != null && selected.x == i && selected.y == j) {
                         Rectangle rect2 = fields[Math.abs(i-8)][Math.abs(j-8)];
                         markField(g, rect, green);
                         markField(g, rect2, green);
                  }
              }
          }
        
        if(selected != null) {
            Piece seletedPiece = getSelected();
            if(seletedPiece != null) {
                int selx = selected.x;
                int sely = selected.y;
                for(Point marked : seletedPiece.getPossibleMoves(selx, sely, this)){
                    markField(g, fields[marked.x][marked.y], red);
                }
            }
        }
        
        // właściwe pola
        for(int i=0; i<size; i++) {
              for(int j=0; j<size; j++) {
                  if(pieces[i][j] != null) {                  
                    Rectangle rect = fields[i][j];
                    pieces[i][j].draw(g, rect);                    
                  }
              }
          }
        
    }
    
    
    
}
