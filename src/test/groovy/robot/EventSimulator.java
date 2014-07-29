package robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class EventSimulator {

    private JComponent component;

    public EventSimulator(JComponent component){
        this.component = component;
    }

    public void click(Point where){
        click(where, 1);
    }

    public void click(Point where,int times){
        dispatch(MouseEvent.MOUSE_CLICKED,where,times);
    }

    public void doubleClick(Point where){
        dispatch(MouseEvent.MOUSE_CLICKED, where, 2);
    }

    public void mousePress(Point where){
        mousePress(where, 1);
    }

    public void mousePress(Point where,int times){
        dispatch(MouseEvent.MOUSE_PRESSED, where, times);
    }

    public void mouseRelease(Point where){
        mouseRelease(where, 1);
    }

    public void mouseRelease(Point where,int times){
        dispatch(MouseEvent.MOUSE_RELEASED,where,times);
    }

    public void mouseMove(Point where){
        dispatch(MouseEvent.MOUSE_MOVED, where, 1 );
    }
    
    public void dragTo(Point where){
        mouseMove (new Point(0,0));
        mousePress(new Point(0,0));
        dispatch(MouseEvent.MOUSE_DRAGGED,where,1);
        mouseRelease(where);
    }

    private void dispatch(int key,Point where,int times){
        component.dispatchEvent(new MouseEvent(component,key,System.currentTimeMillis(),0,where.x,where.y,times,false));
    }
}
