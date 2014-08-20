package org.joker.component;

import org.joker.component.abstracts.Draggable;
import org.joker.component.abstracts.Resizable;
import org.joker.component.abstracts.Selectable;
import org.joker.component.event.StatusEvent;
import org.joker.component.event.StatusProvider;
import org.joker.component.event.StatusType;


public class JokerComponent extends StatusProvider implements Draggable,Resizable,Selectable {

    private boolean isDraggable;
    private boolean isResizable;
    private boolean isSelectable;
    private boolean isSelected;

    @Override
    public boolean isResizable() {
        return isResizable;
    }

    @Override
    public void setResizable( boolean isResizable ) { //TODO PULL UP ?
        this.isResizable = isResizable;
        this.dispatchEvent( new StatusEvent( this, StatusType.Resizable, isResizable ) );
    }

    @Override
    public boolean isDraggable() {
        return isDraggable;
    }

    @Override
    public void setDraggable( boolean isDraggable ){
        this.isDraggable = isDraggable;
        this.dispatchEvent( new StatusEvent( this, StatusType.Draggable, isDraggable ) );
    }

    @Override
    public void select() {
        setSelected( true );
    }

    @Override
    public void unselect() {
        setSelected( false );
    }

    private void setSelected( boolean isSelected ){
        this.isSelected = isSelected;
        this.dispatchEvent( new StatusEvent( this, StatusType.Selected, isSelected ) );
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
        this.dispatchEvent( new StatusEvent( this, StatusType.Selectable, isSelectable ) );
    }

}
