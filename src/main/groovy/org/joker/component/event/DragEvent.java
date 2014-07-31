package org.joker.component.event;

import org.joker.component.JokerComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DragEvent implements MouseMotionListener{

    private Set<JokerComponent> components = new HashSet<JokerComponent>();
    private Point anchorPoint;

    public DragEvent with(JokerComponent...components){
        Collections.addAll(this.components,components);
        return this;
    }

    public DragEvent without(JokerComponent...components){
        for(JokerComponent component : components){
            this.components.remove(component);
        }
        return this;
    }

    public Set<JokerComponent> components(){
        return new HashSet<JokerComponent>(components);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        anchorPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int anchorX = anchorPoint.x;
        int anchorY = anchorPoint.y;

        for(JokerComponent component : components){
            Point parentOnScreen = component.getParent().getLocationOnScreen();
            Point mouseOnScreen  = e.getLocationOnScreen();

            int nextX = mouseOnScreen.x - parentOnScreen.x - anchorX;
            int nextY = mouseOnScreen.y - parentOnScreen.y - anchorY;

            component.setLocation( new Point(nextX, nextY) );
        }
    }

}
