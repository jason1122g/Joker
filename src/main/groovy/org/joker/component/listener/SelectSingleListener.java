package org.joker.component.listener;

import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;
import org.joker.container.abstracts.SelectObserver;
import org.joker.exceptions.IllegalComponentException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectSingleListener extends MouseAdapter{

    private JokerComponent component;
    private SelectObserver selectObserver;

    private Point   lastPressPoint;
    private int     dragMinLength = 3;
    private boolean isDragged;

    private SelectSingleListener( JokerComponent component ){
        this.component = component;
    }

    public static SelectSingleListener triggerFrom( JokerComponent component ){
        return new SelectSingleListener ( component ) ;
    }

    public SelectSingleListener toObserver( SelectObserver selectObserver ){
        this.selectObserver = selectObserver;
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
            tryNotifyObserver( e );
        }
    }

    private void tryNotifyObserver( MouseEvent e ){
        if( selectObserver == null ) {
            throw new IllegalComponentException( "SelectObserver is neccessary" );
        }

        if( lengthOf( e.getPoint(), lastPressPoint ) < dragMinLength ){
            selectObserver.notify( new SelectEvent( component, e ) );
        }
    }

    private double lengthOf( Point point1, Point point2 ){
        double X = point1.x - point2.x;
        double Y = point1.y - point2.y;
        return Math.sqrt( X*X + Y*Y );
    }

}
