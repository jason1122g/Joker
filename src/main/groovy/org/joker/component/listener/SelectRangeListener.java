package org.joker.component.listener;

import org.joker.component.JokerComponent;
import org.joker.container.abstracts.SelectGroup;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectRangeListener extends MouseAdapter {

    private SelectGroup selectGroup;
    private Container   container;
    private Point startPoint;
    private Point endPoint;

    private SelectRangeListener( SelectGroup selectGroup ) {
        this.selectGroup = selectGroup;
    }

    public static SelectRangeListener useGroup( SelectGroup selectGroup ) {
        return new SelectRangeListener( selectGroup );
    }

    public SelectRangeListener withContainer( Container container ) {
        this.container = container;
        return this;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
        endPoint = e.getPoint();
        selectGroup.unselectAll();
        selectAllInRange();
    }

    private void selectAllInRange(){
        Rectangle selectRectangle = convertToRectangle( startPoint, endPoint );
        for ( Component component : container.getComponents() ){
            if( component instanceof JokerComponent ){
                JokerComponent jokerComponent = (JokerComponent) component;
                if( selectRectangle.contains( convertToRectangle( jokerComponent ) ) ){
                    selectGroup.select( jokerComponent );
                }
            }
        }
    }

    private Rectangle convertToRectangle( Point p1, Point p2 ){
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

    private Rectangle convertToRectangle( JokerComponent component ){
        int x = component.getX();
        int y = component.getY();
        int w = component.getWidth();
        int h = component.getHeight();
        return new Rectangle( x, y, w, h );
    }

}
