package org.joker.component.event;

import org.joker.component.JokerComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class DragEvent implements MouseMotionListener{

    private Set<JokerComponent>       components         = new HashSet<JokerComponent>();
    private Map<JokerComponent,Point> beforeDragPointMap = new HashMap<JokerComponent, Point>();
    private Point targetMovePoint;
    private Point beforeDragPoint;
    private boolean isFirstDrag;

    public DragEvent with( JokerComponent...components ){
        Collections.addAll( this.components,components );
        return this;
    }

    public DragEvent without( JokerComponent...components ){
        for( JokerComponent component : components ){
            this.components.remove( component );
        }
        return this;
    }

    public Set<JokerComponent> components(){
        return new HashSet<JokerComponent>( components );
    }

    @Override
    public void mouseMoved( MouseEvent e ) {
        if( targetMovePoint != e.getPoint() ){
            clearDragCondition();
        }
        targetMovePoint = e.getPoint();
        beforeDragPoint = e.getComponent().getLocation();
    }

    private void clearDragCondition(){
        isFirstDrag = true;
        beforeDragPointMap.clear();
    }

    @Override
    public void mouseDragged( MouseEvent e ) {
        tryInitDragCondition();
        setNextLocationOfAll(e);
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
        for( JokerComponent component : components ){
            Point originPos = beforeDragPointMap.get( component );
            int   nextX     = originPos.x + nextRelativePoint.x;
            int   nextY     = originPos.y + nextRelativePoint.y;
            component.setLocation( nextX, nextY );
        }
    }

    private Point getNextRelativePoint( MouseEvent e ){

        Point parentOnScreen = e.getComponent().getParent().getLocationOnScreen();
        Point mouseOnScreen  = e.getLocationOnScreen();

        int nextX = mouseOnScreen.x - parentOnScreen.x - targetMovePoint.x - beforeDragPoint.x;
        int nextY = mouseOnScreen.y - parentOnScreen.y - targetMovePoint.y - beforeDragPoint.y;

        return new Point( nextX, nextY );

    }

}
