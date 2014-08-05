package org.joker.component.listener;

import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;
import org.joker.container.abstracts.SelectObserver;
import org.joker.exceptions.IllegalContainerException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectListener extends MouseAdapter{

    private SelectObserver selectObserver;
    private JokerComponent component;

    private Point   lastPressPoint;
    private int     dragMinLength = 3;
    private boolean isDragged;

    public SelectListener triggerFrom( JokerComponent component ){
        this.component = component;
        return this;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        lastPressPoint = e.getPoint();
        isDragged      = false;
    }

    @Override
    public void mouseExited( MouseEvent e ) {
        lastPressPoint = null;
    }

    @Override
    public void mouseDragged( MouseEvent e ) {
        if( lastPressPoint != null && lengthOf( e.getPoint(), lastPressPoint ) > dragMinLength ){
            isDragged = true;
        }
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
        if( lastPressPoint != null && !isDragged ){
            tryNotifyParent(e);
        }
    }

    private void tryNotifyParent( MouseEvent e ){
        if( lengthOf( e.getPoint(), lastPressPoint ) < dragMinLength ){
            if(selectObserver == null){
                initObserver();
            }
            selectObserver.notify( new SelectEvent( component, e ) );
        }
    }

    private double lengthOf( Point point1, Point point2 ){
        double X = point1.x - point2.x;
        double Y = point1.y - point2.y;
        return Math.sqrt( X*X + Y*Y );
    }

    private void initObserver(){
        if( component.getParent() == null ){
            throw new IllegalContainerException( "must have a container" );
        }
        try{
            selectObserver = (SelectObserver) component.getParent();
        }catch ( ClassCastException e ){
            throw new IllegalContainerException( "container must be JokerLayer/SelectObserver" );
        }
    }
}
