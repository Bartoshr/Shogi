/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import static main.Gameboard.size;
import pieces.*;

/**
 *
 * @author bartosh
 */
public class Purgatory {
    
    int x,y;
    
    int rook;
    int bishop;
    int silverGeneral;
    int goldGeneral;
    int lance;
    int pawn;
    int knight;
    
    Rectangle[] rects;
    int selected = -1;
    
    Piece[] pieces = {
        new Bishop(null),
        new GoldGeneral(null),
        new Knight(null),
        new Pawn(null),
        new Rook(null),
        new Lance(null),
        new SilverGeneral(null)
    };

    public Purgatory(int x, int y) {
        this.x = x;
        this.y = y;
        
        rects = new Rectangle[7];
        rects[0] = new Rectangle(x, y, 50, 50);
        rects[1] = new Rectangle(x+70, y, 50, 50);
        rects[2] = new Rectangle(x, y+60, 50, 50);
        rects[3] = new Rectangle(x+70, y+60, 50, 50);
        rects[4] = new Rectangle(x, y+120, 50, 50);
        rects[5] = new Rectangle(x+70, y+120, 50, 50);
        rects[6] = new Rectangle(x+140, y+120, 50, 50);        
    }
    
    void addPiece(Piece piece){
        if(piece == null) return;
        
        if(piece instanceof Rook) {
            rook++;
        } else if(piece instanceof Bishop) {
            bishop++;
        } else if(piece instanceof GoldGeneral) {
            goldGeneral++;
        } else if(piece instanceof SilverGeneral) {
            silverGeneral++;
        } else if(piece instanceof Lance) {
            lance++;
        } else if(piece instanceof Pawn) {
            pawn++;
        } else if(piece instanceof Knight) {
            knight++;
        }
    }
    
    
    public void selectItem(int i){
        selected = i;
    }
    
    public void clearSelection(){
        selected = -1;
    }
    
   // zwraca indeks gdy kliknięcie było w obrębie oraz -1 gdy nie
   public int checkCoordinates(int x, int y){
        for(int i=0; i<7; i++) {
                  Rectangle rect = rects[i];
                  if(rect.contains(x, y)){
                      return i;
                  }
            }
        return -1;
    }
    
    public void draw(Graphics g){

        if(selected != -1){
            Rectangle rect = rects[selected];
            g.drawRect(rect.x+5, rect.y+5, rect.width-10, rect.height-10);
        }
        
        pieces[0].drawSign(g, rects[0], bishop);
        pieces[1].drawSign(g, rects[1], goldGeneral);
        pieces[2].drawSign(g, rects[2], knight);
        pieces[3].drawSign(g, rects[3], pawn);
        pieces[4].drawSign(g, rects[4], rook);
        pieces[5].drawSign(g, rects[5], lance);
        pieces[6].drawSign(g, rects[6], silverGeneral); 
    }
    
    public List<Point> getPossibleMoves(Gameboard board){
        List<Point> result = new ArrayList<>();
            for(int i = 0; i<board.size; i++){
                
                if(pieces[selected] instanceof Pawn 
                        && isPawnInColumn(i, board)) continue;
                
                for(int j=0; j<board.size; j++){                  
                    Piece next = board.getField(i, j);
                    if(next == null){
                        result.add(new Point(i, j));
                    }
                }
            }            
            return result;
    }
    
    
    //sprawdza czy znajduje się promowany pionek
    public boolean isPawnInColumn(int column, Gameboard board){
        for(int i = 0; i<board.size; i++) {
            Piece piece = board.getField(column, i);
            if (piece instanceof Pawn && !piece.promoted) return true;
        }
        return false;
    }
    
    
    
}
