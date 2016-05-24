package pieces;


import pieces.Piece;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import main.Gameboard;

import main.Player;

public class Lance extends Piece{

    public Lance(Player player) {
        super(player);
        normChar = "香";
        promChar = "杏";
    }
    
    private boolean obstacleDetect(Point from, Point to, Gameboard board){
        Point tmp = new Point(to.x, to.y);
        
        while(!tmp.equals(from)){
  
            if (tmp.y < from.y) tmp.y++;
            
            Piece piece = board.getField(tmp.x, tmp.y);
            if(piece != null  && piece != this) {
               return false;
            }
        }    
        return true;
    }

    @Override
    public boolean couldMove(Point from, Point to, Gameboard board) {
        if(!promoted) {
            return super.couldMove(from, to, board) 
                   && (from.x-to.x == 0) && (from.y-to.y>0)
                        && obstacleDetect(from, to, board);
        } else {
            int dx = to.x-from.x;
            int dy = to.y-from.y;
            return super.couldMove(from, to, board)
                && (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
                    && !(dx == -1 && dy == 1) && !(dx == 1 && dy == 1);
        }
    }
    
    
    
}
