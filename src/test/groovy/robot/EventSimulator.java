package robot;

import robot.handler.EventDragPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class EventSimulator {

    private JComponent component;
    private int modifier = 0;

    public EventSimulator( JComponent eventTarget ){
        this.component = eventTarget;
    }

    public EventSimulator click(){
        mousePress();
        click( new Point(1,1), 1 );
        mouseRelease();
        return this;
    }

    public void click( Point where ){
        click( where, 1 );
    }

    public void click( Point where, int times ){
        click( component, where, times );
    }

    public void click( JComponent eventSource, Point where ){
        click( eventSource, where, 1 );
    }

    public void click( JComponent eventSource, Point where, int times ){
        dispatch( eventSource, MouseEvent.MOUSE_CLICKED, where, times );
    }

    public EventSimulator mousePress(){
        mousePress( new Point( 1, 1 ) );
        return this;
    }

    public void mousePress( Point where ){
        mousePress( where, 1 );
    }

    public void mousePress( Point where, int times ){
        mousePress( component, where, times );
    }

    public void mousePress( JComponent eventSource, Point where ){
        mousePress( eventSource, where, 1 );
    }
      
    public void mousePress( JComponent eventSource, Point where, int times ){
        dispatch( eventSource, MouseEvent.MOUSE_PRESSED, where, times );
    }

    public EventSimulator mouseRelease(){
        mouseRelease( new Point( 1, 1 ) );
        return this;
    }

    public void mouseRelease( Point where ){
        mouseRelease( where, 1 );
    }

    public void mouseRelease( Point where, int times ){
        mouseRelease( component, where, times);
    }

    public void mouseRelease( JComponent eventSource, Point where ){
        mouseRelease( component, where, 1 );
    }

    public void mouseRelease( JComponent eventSource, Point where, int times ){
        dispatch( eventSource, MouseEvent.MOUSE_RELEASED, where, times );
    }

    public void mouseMove( Point where ){
        mouseMove( component, where );
    }

    public void mouseMove( JComponent eventSource, Point where ){
        dispatch( eventSource, MouseEvent.MOUSE_MOVED, where, 1 );
    }

    public EventDragPath drag(){
        return new EventDragPath( this );
    }

    public void mouseDrag( Point where ){
        mouseDrag( component, where );
    }

    public void mouseDrag( JComponent eventSource, Point where ){
        dispatch( eventSource, MouseEvent.MOUSE_DRAGGED, where, 1 );
    }

    public void mouseEnter(){
        mouseEnter( component );
    }

    public void mouseEnter( JComponent eventSource ){
        dispatch( eventSource, MouseEvent.MOUSE_ENTERED, new Point(1,1), 1 );
    }

    public void mouseExit(){
        mouseExit( component );
    }

    public void mouseExit( JComponent eventSource ){
        dispatch( eventSource, MouseEvent.MOUSE_EXITED, new Point(1,1), 1 );
    }

    public void pressKey(int keyCode){
        switch ( keyCode ){
            case KeyEvent.VK_CONTROL:
                modifier |= MouseEvent.CTRL_DOWN_MASK;
                break;
            case KeyEvent.VK_SHIFT:
                modifier |= MouseEvent.SHIFT_DOWN_MASK;
                break;
        }
    }

    public void releaseKey(int keyCode){
        switch ( keyCode ){
            case KeyEvent.VK_CONTROL:
                modifier ^= MouseEvent.CTRL_DOWN_MASK;
                break;
            case KeyEvent.VK_SHIFT:
                modifier ^= MouseEvent.SHIFT_DOWN_MASK;
                break;
        }
    }

    public void dispatch( JComponent component, int key, Point where, int times ){
        component.dispatchEvent( new MouseEvent( component, key, System.currentTimeMillis(), modifier, where.x, where.y, times, false) );
    }
}
