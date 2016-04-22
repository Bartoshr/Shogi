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

/**
 *
 * @author bartosh
 */
public class GoldGeneral extends Piece{

    public GoldGeneral(Player player) {
        super(player);
        promotable = false;
        normChar = "金";
    }

    @Override
    public boolean couldMove(Point from, Point to) {
        int dx = to.x-from.x;
        int dy = to.y-from.y;
        return (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
                && !(dx == -1 && dy == 1) && !(dx == 1 && dy == 1);
    }
    
    
    
}