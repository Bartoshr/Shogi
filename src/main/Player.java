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
    
    public Player() {
    }
    
    void setPieces(Gameboard board){
                for(int i = 0; i < 9; i++){
            board.setField(i, 6, new Pawn(this));
        }
        board.setField(2,7, new Bishop(this));
        board.setField(6,7, new Rook(this));
        
        board.setField(0,8, new Lance(this));
        board.setField(1,8, new Knight(this));
        board.setField(2,8, new SilverGeneral(this));
        board.setField(3,8, new GoldGeneral(this));
        board.setField(4,8, new King(this));
        board.setField(5,8, new GoldGeneral(this));
        board.setField(6,8, new SilverGeneral(this));
        board.setField(7,8, new Knight(this));
        board.setField(8,8, new Lance(this));
    }
    
}
