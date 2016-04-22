package pieces;

import main.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import main.Gameboard;
import org.w3c.dom.css.Rect;

public abstract class Piece {
           
    boolean promoted;
    boolean promotable;
    
    String normChar = ""; 
    String promChar = "";
    
    Player owner;
    
    Piece(Player player){
        promotable = true;
    }
    
    public boolean couldMove(Point from, Point to){
        return false;
    }
    
    public List<Point> getPossibleMoves(int x, int y, Gameboard board){
        List<Point> result = new ArrayList<>();
            for(int i = 0; i<board.size; i++){
                for(int j=0; j<board.size; j++){
                    Piece next = board.getField(i, j);
                    if(couldMove(new Point(x,y), new Point(i,j))){
                        result.add(new Point(i, j));
                    }
                }
            }            
            return result;
    }
        
    public void draw(Graphics g, Rectangle rect) {
            Font f = new Font("Times New Roman", Font.BOLD, 35);
            g.setFont(f);
        if(!promoted) { 
            g.drawString(normChar, rect.x+5, rect.y+30);
        } else {
            g.drawString(promChar, rect.x+5, rect.y+30);
        }
    }
}