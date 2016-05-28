/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author bartosh
 */
public class Move implements Serializable {
    
    Point from;
    Point to;
    boolean withPromotion;
    int pieceType;

    public Move(Point from, Point to, boolean withPromotion) {
        this.from = from;
        this.to = to;
        this.withPromotion = withPromotion;
        this.pieceType = 9; // dziewiątka wskazuje, że ruchem jest zwykłe przemieszczenie
    }
    
    public Move(Point from, Point to, boolean withPromotion, int pieceType) {
        this.from = from;
        this.to = to;
        this.withPromotion = withPromotion;
        this.pieceType=pieceType;
    }

    
    public Move(String string) {
       from = new Point((int)string.charAt(0)-48, (int)string.charAt(1)-48);
       to = new Point((int)string.charAt(2)-48, (int)string.charAt(3)-48);
       withPromotion = ((int)(string.charAt(4)-48) == 1);
       pieceType = (int)(string.charAt(5)-48);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        builder.append(from.x);
        builder.append(from.y);
        builder.append(to.x);
        builder.append(to.y);
        
        if(withPromotion) builder.append(1);
                else builder.append(0);
        builder.append(pieceType);
        
        return builder.toString();
    }
    
    
}
