package org.joker.component.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * TODO TEST AND FINISH THIS
 */
public class DragEvent implements MouseMotionListener{

    private JComponent[] components;
    private Point anchorPoint;

    public DragEvent with(JComponent...components){
        this.components = components;
        return this;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        anchorPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int anchorX = anchorPoint.x;
        int anchorY = anchorPoint.y;

        for(JComponent component:components){
            Point parentOnScreen = component.getParent().getLocationOnScreen();
            Point mouseOnScreen  = e.getLocationOnScreen();

            int nextX = mouseOnScreen.x - parentOnScreen.x - anchorX;
            int nextY = mouseOnScreen.y - parentOnScreen.y - anchorY;

            component.setLocation(new Point(nextX, nextY));
        }
    }

}
