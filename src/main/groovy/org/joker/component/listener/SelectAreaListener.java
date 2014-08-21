package org.joker.component.listener;

import org.joker.component.JokerComponent;
import org.joker.container.abstracts.SelectGroup;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectAreaListener extends MouseAdapter {

    private SelectGroup selectGroup;
    private Container   container;
    private Point       startPoint;
    private Rectangle   selectArea;
    private boolean isSelecting;

    private SelectAreaListener( SelectGroup selectGroup ) {
        this.selectGroup = selectGroup;
    }

    public static SelectAreaListener useGroup( SelectGroup selectGroup ) {
        return new SelectAreaListener( selectGroup );
    }

    public SelectAreaListener withContainer( Container container ) {
        this.container = container;
        return this;
    }

    public boolean isSelecting(){
        return isSelecting;
    }

    public Rectangle getSelectArea(){
        return selectArea;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        isSelecting  = true;
        startPoint  = e.getPoint();
    }

    @Override
    public void mouseDragged( MouseEvent e ) {
        selectArea = convertToRectangle( startPoint, e.getPoint() );
        container.repaint();
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
        isSelecting  = false;
        if( ! e.isControlDown() ){
            selectGroup.unselectAll();
        }
        if( ! startPoint.equals( e.getPoint() ) ){
            selectAllInRange();
        }
        container.repaint();
    }

    private void selectAllInRange() {
        for ( Component component : container.getComponents() ){
            if( component instanceof JokerComponent ){
                JokerComponent jokerComponent = (JokerComponent) component;
                if( selectArea.contains( convertToRectangle( jokerComponent ) ) ){
                    selectGroup.select( jokerComponent );
                }
            }
        }
    }

    private static Rectangle convertToRectangle( Point p1, Point p2 ) {
        if( p1.y > p2.y ){
            if( p1.x > p2.x ){
                return new Rectangle( p2, new Dimension( p1.x - p2.x, p1.y - p2.y ) );
            }else{
                return new Rectangle( p1.x , p2.y, p2.x - p1.x, p1.y - p2.y );
            }
        }else{
            if( p1.x > p2.x ){
                return new Rectangle( p2.x, p1.y, p1.x - p2.x, p2.y - p1.y );
            }else{
                return new Rectangle( p1, new Dimension( p2.x - p1.x, p2.y - p1.y ) );
            }
        }
    }

    private static Rectangle convertToRectangle( JokerComponent component ) {
        int x = component.getX();
        int y = component.getY();
        int w = component.getWidth();
        int h = component.getHeight();
        return new Rectangle( x, y, w, h );
    }

}
