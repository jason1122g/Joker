package org.joker.component;

import org.joker.JokerObject;
import org.joker.component.abstracts.Draggable;
import org.joker.component.abstracts.Resizable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

/**
 * TODO TEST AND FINISH THIS
 */
public class JokerComponent extends JokerObject implements Draggable,Resizable {
    private MouseMotionListener mouseMotionListener;

    @Override
    public void setDraggable(boolean isDraggable){
        if(isDraggable){
            if(mouseMotionListener == null){
                initMouseMotionListener();
            }
            this.addMouseMotionListener(mouseMotionListener);
        }else{
            this.removeMouseMotionListener(mouseMotionListener);
        }
    }

    private void initMouseMotionListener() {
        mouseMotionListener = new MouseMotionAdapter(){

            private Point anchorPoint;

            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen  = e.getLocationOnScreen();

                int nextX = mouseOnScreen.x - parentOnScreen.x - anchorX;
                int nextY = mouseOnScreen.y - parentOnScreen.y - anchorY;

                setLocation(new Point(nextX,nextY));
            }

        };
    }
}
