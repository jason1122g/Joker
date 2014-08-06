package org.joker.container.group;

import org.joker.component.JokerComponent;
import org.joker.component.event.StatusEvent;
import org.joker.component.event.StatusType;
import org.joker.component.listener.StatusListener;
import org.joker.component.listener.abstracts.StatusChangedListener;
import org.joker.container.abstracts.StatusGroup;

import java.util.HashSet;
import java.util.Set;

public class StatusObserveGroup implements StatusGroup {

    private StatusChangedListener listener = new StatusListener();
    private Set<JokerComponent> components = new HashSet<>();

    @Override
    public void add( JokerComponent component ) {
        if( !components.contains( component ) ) {
            component.addStatusChangedListener( listener );
            tryTriggerAllOnce( component );
            components.add   ( component );
        }
    }

    private void tryTriggerAllOnce( JokerComponent component ) {
        listener.draggableChanged ( new StatusEvent( component, StatusType.Draggable , component.isDraggable()  ) );
        listener.resizableChanged ( new StatusEvent( component, StatusType.Resizable , component.isResizable()  ) );
        listener.selectableChanged( new StatusEvent( component, StatusType.Selectable, component.isSelectable() ) );
        listener.selectedChanged  ( new StatusEvent( component, StatusType.Selected  , component.isSelected()   ) );
    }

    @Override
    public void remove( JokerComponent component ) {
        if( components.contains( component ) ){
            component.removeStatusChangedListener( listener );
            triggerAllOnce   ( component );
            components.remove( component );
        }
    }

    private void triggerAllOnce( JokerComponent component ) {
        listener.draggableChanged ( new StatusEvent( component, StatusType.Draggable , false ) );
        listener.resizableChanged ( new StatusEvent( component, StatusType.Resizable , false ) );
        listener.selectableChanged( new StatusEvent( component, StatusType.Selectable, false ) );
        listener.selectedChanged  ( new StatusEvent( component, StatusType.Selected  , false ) );
    }

    @Override
    public void removeAll() {
        for( JokerComponent component : components ) {
            component.removeStatusChangedListener( listener );
        }
        components.clear();
    }

    @Override
    public Set<JokerComponent> components() {
        return new HashSet<>( components );
    }

}
