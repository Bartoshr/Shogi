package pieces;

import main.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Rook extends Piece{

    public Rook(Player player) {
        super(player);
    }


    @Override
    public void draw(Graphics g, Rectangle rect) {
        Font f = new Font("Times New Roman", Font.BOLD, 35);
        g.setFont(f);
        g.drawRect(rect.x, rect.y, 1, 1);
        g.drawString("飛", rect.x+5, rect.y+30);
    }

    @Override
    public boolean couldMove(Point from, Point to) {
        return (to.x-from.x == 0) || (to.y-from.y == 0);
    }
    
    
    
}