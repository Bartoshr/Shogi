package pieces;


import pieces.Piece;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import main.Gameboard;

import main.Player;

public class Knight extends Piece{

    public Knight(Player player) {
        super(player);
        normChar = "桂";
        promChar = "圭";
    }
    

    @Override
    public boolean couldMove(Point from, Point to, Gameboard board) {
        if(!promoted) {
            return super.couldMove(from, to, board) 
                && (to.y-from.y) == -2 && Math.abs(to.x-from.x) == 1;
        } else {
            int dx = to.x-from.x;
            int dy = to.y-from.y;
            return super.couldMove(from, to, board)
                && (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
                    && !(dx == -1 && dy == 1) && !(dx == 1 && dy == 1);
        }
    }
    
    
}
