package robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class EventSimulator {

    private JComponent component;

    public EventSimulator(JComponent eventTarget){
        this.component = eventTarget;
    }

    public void click(){
        click(new Point(1,1),1);
    }

    public void click(Point where){
        click(where, 1);
    }

    public void click(Point where,int times){
        click(component, where, times);
    }

    public void click(JComponent eventSource, Point where){
        click(eventSource, where, 1);
    }

    public void click(JComponent eventSource, Point where, int times){
        dispatch(eventSource,MouseEvent.MOUSE_CLICKED,where,times);
    }

    public void mousePress(Point where){
        mousePress(where, 1);
    }

    public void mousePress(Point where,int times){
        mousePress(component,where,times);
    }

    public void mousePress(JComponent eventSource, Point where){
        mousePress(eventSource, where, 1);
    }
      
    public void mousePress(JComponent eventSource, Point where, int times){
        dispatch(eventSource,MouseEvent.MOUSE_PRESSED, where, times);
    }

    public void mouseRelease(Point where){
        mouseRelease(where, 1);
    }

    public void mouseRelease(Point where,int times){
        mouseRelease(component,where,times);
    }

    public void mouseRelease(JComponent eventSource, Point where){
        mouseRelease(component,where,1);
    }

    public void mouseRelease(JComponent eventSource, Point where, int times){
        dispatch(eventSource,MouseEvent.MOUSE_RELEASED,where,times);
    }

    public void mouseMove(Point where){
        mouseMove(component,where);
    }

    public void mouseMove(JComponent eventSource, Point where){
        dispatch(eventSource,MouseEvent.MOUSE_MOVED, where, 1 );
    }
    
    public void dragTo(Point where){
        dragTo(component,where);
    }

    public void dragTo(JComponent eventSource, Point where){
        mouseMove (eventSource,new Point(0,0));
        mousePress(eventSource,new Point(0,0),1);
        dispatch(eventSource,MouseEvent.MOUSE_DRAGGED,where,1);
        mouseRelease(eventSource,where,1);
    }

    private void dispatch(JComponent component, int key, Point where, int times){
        component.dispatchEvent(new MouseEvent(component,key,System.currentTimeMillis(),0,where.x,where.y,times,false));
    }
}
