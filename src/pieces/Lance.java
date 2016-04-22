package pieces;


import pieces.Piece;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import main.Player;

public class Lance extends Piece{

    public Lance(Player player) {
        super(player);
        normChar = "香";
        promChar = "杏";
    }

    @Override
    public boolean couldMove(Point from, Point to) {
        return (from.x-to.x == 0) && (from.y-to.y>0);
    }
    
    
    
}
