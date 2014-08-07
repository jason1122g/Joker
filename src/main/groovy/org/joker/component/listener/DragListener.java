package org.joker.component.listener;

import org.joker.component.JokerComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class DragListener implements MouseMotionListener{

    private Set<JokerComponent>       components         = new HashSet<>();
    private Map<JokerComponent,Point> beforeDragPointMap = new HashMap<>();
    private Point beforeDragPoint;
    private Point beforeDragLocation;
    private boolean isFirstDrag;

    public DragListener dragWith( JokerComponent... components ){
        Collections.addAll( this.components,components );
        return this;
    }

    public DragListener dragWithout( JokerComponent... components ){
        for( JokerComponent component : components ){
            this.components.remove( component );
        }
        return this;
    }

    public boolean contains( JokerComponent component ){
        return components.contains( component );
    }

    @Override
    public void mouseMoved( MouseEvent e ) {
        if( beforeDragPoint != e.getPoint() ){
            clearDragCondition();
        }
        beforeDragPoint    = e.getPoint();
        beforeDragLocation = e.getComponent().getLocation();
    }

    private void clearDragCondition(){
        isFirstDrag = true;
        beforeDragPointMap.clear();
    }

    @Override
    public void mouseDragged( MouseEvent e ) {
        tryInitDragCondition();
        setNextLocationOfAll(e);
        repaintParent(); //TODO CHECK IF REASONABLE HERE
    }

    private void tryInitDragCondition(){
        if( isFirstDrag ){
            for( JokerComponent component : components ){
                beforeDragPointMap.put( component, component.getLocation() );
            }
            isFirstDrag = false;
        }
    }

    private void setNextLocationOfAll( MouseEvent e ){
        Point nextRelativePoint = getNextRelativePoint(e);
        if ( lengthOf( nextRelativePoint ) > 3 ){
            for( JokerComponent component : components ){
                Point originPos = beforeDragPointMap.get( component );
                int   nextX     = originPos.x + nextRelativePoint.x;
                int   nextY     = originPos.y + nextRelativePoint.y;
                component.setLocation( nextX, nextY );
            }
        }
    }

    private Point getNextRelativePoint( MouseEvent e ){

        Point parentOnScreen = e.getComponent().getParent().getLocationOnScreen();
        Point mouseOnScreen  = e.getLocationOnScreen();

        int nextX = mouseOnScreen.x - parentOnScreen.x - beforeDragPoint.x - beforeDragLocation.x;
        int nextY = mouseOnScreen.y - parentOnScreen.y - beforeDragPoint.y - beforeDragLocation.y;

        return new Point( nextX, nextY );

    }

    private double lengthOf( Point point ){
        double x2 = point.x * point.x;
        double y2 = point.y * point.y;
        return Math.sqrt( x2 + y2 );
    }

    private void repaintParent(){
        components.iterator().next().getParent().repaint();
    }

}
