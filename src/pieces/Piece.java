package pieces;

import java.awt.Color;
import main.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import main.Gameboard;

public abstract class Piece {
           
    public boolean promoted;
    public boolean promotable;
    
    private static final Font normalSignFont = new Font("Times New Roman", Font.BOLD, 35);
    private static final Font smallSignFont = new Font("Times New Roman", Font.BOLD, 30);
    private static final Font textFont = new Font("Times New Roman", Font.PLAIN, 15);
    
    String normChar = ""; 
    String promChar = "";
    
    public Player owner;
    
    private static final Color background = new Color(212, 195, 144);
    
    Piece(Player owner){
        promotable = true;
        this.owner = owner;
    }
    
    public boolean couldMove(Point from, Point to, Gameboard board){
       if(owner.isUp) return false; // jeśli pionek jest nie twój
       if(isFieldTaken(to.x, to.y, board)) return false;
       if(to.y-from.y == 0 && to.x-from.x == 0) return false;
       return true;
    }
    
    public List<Point> getPossibleMoves(int x, int y, Gameboard board){
        List<Point> result = new ArrayList<>();
            for(int i = 0; i<board.size; i++){
                for(int j=0; j<board.size; j++){
                    Piece next = board.getField(i, j);
                    if(couldMove(new Point(x,y), new Point(i,j),board)){
                        result.add(new Point(i, j));
                    }
                }
            }            
            return result;
    }
    
    protected boolean isFieldTaken(int x, int y, Gameboard board){
       Piece piece = board.getField(x, y);
       if(piece == null) return false;
       return  (piece.owner == this.owner);
    }
            
    public void draw(Graphics g, Rectangle rect) {
        g.setFont(normalSignFont);
        
        if(owner.isUp){
            drawPoligonDown(g, rect);   
        } else {
            drawPoligonUp (g, rect);
        }
        
        if(!promoted) { 
            g.drawString(normChar, rect.x+10, rect.y+35);
        } else {
            g.drawString(promChar, rect.x+10, rect.y+35);
        }
    }
    
    
    public void drawSign(Graphics g, Rectangle rect, int num) {
        g.setFont(smallSignFont);       
        g.drawString(normChar, rect.x+10, rect.y+35);
        
        g.setFont(textFont);
        g.drawString(toString()+" "+Integer.toString(num), rect.x, rect.y+60);
    }
    
    private void drawPoligonDown(Graphics g, Rectangle rect) {
        
        Color tmp = g.getColor();
        g.setColor(background);
        
        int xPoly[] = {
            rect.x+1,
            rect.x+rect.width,
            rect.x+(rect.width)-5,
            rect.x+(rect.width/2),
            rect.x+1+5
        };
        
        int yPoly[] = {
            rect.y+1,
            rect.y+1,
            rect.y+rect.height-10,
            rect.y+rect.height-1,
            rect.y+rect.height-10
        };
        
        Polygon result = new Polygon(xPoly, yPoly, xPoly.length);
        g.fillPolygon(result);
        g.setColor(tmp);
    }
    
     private void drawPoligonUp(Graphics g,Rectangle rect) {
        
        Color tmp = g.getColor();
        g.setColor(background); 
 
        
        int xPoly[] = {
            rect.x+1+5,
            rect.x+rect.width/2,
            rect.x+rect.width-5,
            rect.x+(rect.width),
            rect.x+1
        };
        
        int yPoly[] = {
            rect.y+1+10,
            rect.y+1,
            rect.y+1+10,
            rect.y+rect.height,
            rect.y+rect.height,
        };
        
        Polygon result = new Polygon(xPoly, yPoly, xPoly.length);
        g.fillPolygon(result);
        g.setColor(tmp);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
    
     
}