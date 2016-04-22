package pieces;

import main.Player;

import pieces.Piece;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Pawn extends Piece{

    public Pawn(Player player) {
        super(player);
        normChar = "歩";
        promChar = "と";
    }

    @Override
    public boolean couldMove(Point from, Point to) {
        return (to.x-from.x == 0) && (from.y-to.y == 1);
    }
    
    
    
}
