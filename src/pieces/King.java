package pieces;


import pieces.Piece;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import main.Gameboard;

import main.Player;

public class King extends Piece{

    public King(Player player) {
        super(player);
        promotable = false;
        normChar = "王";
    }

    @Override
    public boolean couldMove(Point from, Point to, Gameboard board) {
       return super.couldMove(from, to, board) 
               && (Math.abs(to.x-from.x) <= 1 && Math.abs(to.y-from.y) <= 1);
    }
    
    

}
