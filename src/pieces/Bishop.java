/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import main.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import main.Gameboard;

/**
 *
 * @author bartosh
 */
public class Bishop extends Piece{

    public Bishop(Player player) {
        super(player);
        normChar = "角";
        promChar = "馬";
    }

    private boolean obstacleDetect(Point from, Point to, Gameboard board){
        Point tmp = new Point(to.x, to.y);
        
        while(!tmp.equals(from)){
  
            if (tmp.x < from.x) tmp.x++;
            else tmp.x--;

            if (tmp.y < from.y) tmp.y++;
            else tmp.y--;
            
            Piece piece = board.getField(tmp.x, tmp.y);
            if(piece != null  && piece != this) {
                return false;
            }
        }    
        return true;
    }
    
    @Override
    public boolean couldMove(Point from, Point to, Gameboard board) {
         return super.couldMove(from, to, board)
                && (Math.abs(to.x-from.x) == Math.abs(to.y-from.y))
                    && obstacleDetect(from, to, board);
    }

}
