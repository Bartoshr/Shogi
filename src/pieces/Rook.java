package pieces;

import main.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import main.Gameboard;

public class Rook extends Piece{

    public Rook(Player player) {
        super(player);
        normChar = "飛";
        promChar = "龍";
    }


     private boolean obstacleDetect(Point from, Point to, Gameboard board){
        Point tmp = new Point(to.x, to.y);
        
        while(!tmp.equals(from)){
  
            if (tmp.x < from.x) tmp.x++;
            else if(tmp.x > from.x)tmp.x--;

            if (tmp.y < from.y) tmp.y++;
            else if(tmp.y > from.y) tmp.y--;
            
            Piece piece = board.getField(tmp.x, tmp.y);
            if(piece != null && piece.owner == this.owner && piece != this) {
                if(board.getField(tmp.x, tmp.y).owner == this.owner) return false;
            }
        }    
        return true;
    }
    
    @Override
    public boolean couldMove(Point from, Point to, Gameboard board) {
        return super.couldMove(from, to, board) 
                && ((to.x-from.x == 0) || (to.y-from.y == 0)) 
                    && obstacleDetect(from, to, board);
    }
    
    
    
}