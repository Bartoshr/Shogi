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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bartosh
 */
public class Bishop extends Piece{

    public Bishop(Player player) {
        super(player);
        normChar = "角";
        promChar = "馬";
    }

    @Override
    public boolean couldMove(Point from, Point to) {
        return (Math.abs(to.x-from.x) == Math.abs(to.y-from.y));
    }

}
