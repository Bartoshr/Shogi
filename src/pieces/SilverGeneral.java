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


public class SilverGeneral extends Piece{

    public SilverGeneral(Player player) {
        super(player);
    }

    @Override
    public void draw(Graphics g, Rectangle rect) {
        Font f = new Font("Times New Roman", Font.BOLD, 35);
        g.setFont(f);
        g.drawRect(rect.x, rect.y, 1, 1);
        g.drawString("銀", rect.x+5, rect.y+30);
    }

    @Override
    public boolean couldMove(Point from, Point to) {
        int dx = to.x-from.x;
        int dy = to.y-from.y;
        return (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
                && !(dx == -1 && dy == 0) && !(dx == 0 && dy == 1) && !(dx == 1 && dy == 0);
    }
    
    
    
}