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
import main.Gameboard;


public class SilverGeneral extends Piece{

    public SilverGeneral(Player player) {
        super(player);
        normChar = "銀";
        promChar = "全";
    }


    @Override
    public boolean couldMove(Point from, Point to, Gameboard board) {
        int dx = to.x-from.x;
        int dy = to.y-from.y;
        return super.couldMove(from, to, board)
        && (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
                && !(dx == -1 && dy == 0) && !(dx == 0 && dy == 1) && !(dx == 1 && dy == 0);
    }
    
    
    
}