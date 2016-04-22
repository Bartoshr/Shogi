package pieces;


import pieces.Piece;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import main.Player;

public class Knight extends Piece{

    public Knight(Player player) {
        super(player);
        normChar = "桂";
        promChar = "圭";
    }

    @Override
    public boolean couldMove(Point from, Point to) {
        return (to.y-from.y) == -2 && Math.abs(to.x-from.x) == 1;
    }
    
    
}
