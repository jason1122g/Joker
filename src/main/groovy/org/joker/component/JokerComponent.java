package org.joker.component;

import org.joker.JokerObject;
import org.joker.component.abstracts.Draggable;
import org.joker.component.abstracts.Resizable;
import org.joker.component.abstracts.Selectable;
import org.joker.component.listener.DragListener;


public class JokerComponent extends JokerObject implements Draggable,Resizable,Selectable {

    private boolean isDraggable;
    private boolean isResizable;
    private boolean isSelectable;
    private boolean isSelected;

    private DragListener dragListener = new DragListener().with( this );

    @Override
    public boolean isResizable() {
        return isResizable;
    }

    @Override
    public void setResizable( boolean isResizable ) {
        this.isResizable = isResizable;
    }

    @Override
    public boolean isDraggable() {
        return isDraggable;
    }

    @Override
    public void setDraggable( boolean isDraggable ){
        if( isDraggable ){
            this.addMouseMotionListener( dragListener );
        }else{
            this.removeMouseMotionListener( dragListener );
        }
        this.isDraggable = isDraggable;
    }

    @Override
    public void select() {
        isSelected = true;
        repaint();
    }

    @Override
    public void unselect() {
        isSelected = false;
        repaint();
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean isSelectable() {
        return isSelectable;
    }

    @Override
    public void setSelectable( boolean isSelectable ) {
        this.isSelectable = isSelectable;
    }

}
