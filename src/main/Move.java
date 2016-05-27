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

    public Move(Point from, Point to, boolean withPromotion) {
        this.from = from;
        this.to = to;
        this.withPromotion = withPromotion;
    }
    
    
    
}
