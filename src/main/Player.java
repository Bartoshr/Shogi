package main;


import main.Gameboard;
import java.util.Vector;
import pieces.Bishop;
import pieces.GoldGeneral;
import pieces.King;
import pieces.Knight;
import pieces.Lance;
import pieces.Pawn;
import pieces.Rook;
import pieces.SilverGeneral;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bartosh
 */
public class Player {
    
    public boolean isUp; // indicate direction of arrow (ownership)    
    
    public Player(boolean isUp) {
        this.isUp = isUp;
    }
    
    void setPieces(Gameboard board){
        
        int firstRow = 6;
        int midRow = 7;
        int lastRow = 8;
        
        if(isUp) {
            firstRow = 2;
            midRow = 1;
            lastRow = 0;
        }
        
        
        for(int i = 0; i < 9; i++){
            board.setField(i, firstRow, new Pawn(this));
        }
        board.setField(1,midRow, new Bishop(this));
        board.setField(7,midRow, new Rook(this));
        
        board.setField(0,lastRow, new Lance(this));
        board.setField(1,lastRow, new Knight(this));
        board.setField(2,lastRow, new SilverGeneral(this));
        board.setField(3,lastRow, new GoldGeneral(this));
        board.setField(4,lastRow, new King(this));
        board.setField(5,lastRow, new GoldGeneral(this));
        board.setField(6,lastRow, new SilverGeneral(this));
        board.setField(7,lastRow, new Knight(this));
        board.setField(8,lastRow, new Lance(this));
    }
    
}
