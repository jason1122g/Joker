package org.joker.container.handler;

import org.joker.component.JokerComponent;
import org.joker.component.event.StatusEvent;
import org.joker.component.event.StatusType;
import org.joker.component.listener.StatusListener;
import org.joker.component.listener.abstracts.StatusChangedListener;
import org.joker.container.JokerLayer;
import org.joker.container.abstracts.ComponentHandler;
import org.joker.container.abstracts.ComponentSelectHandler;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class BasicSelectHandler implements ComponentSelectHandler {

    private ComponentHandler       draggableHandler  = new DraggableHandler();
    private ComponentSelectHandler selectableHandler = new SelectableHandler();
    private StatusChangedListener  listener;

    private Set<JokerComponent> components;

    @Override
    public void construct( JokerLayer layer ) {
        draggableHandler.construct ( layer );
        selectableHandler.construct( layer );

        listener   = new StatusListener( draggableHandler, selectableHandler );
        components = new HashSet<>();

        mountAllJokerComponents( layer );
    }

    private void mountAllJokerComponents( JokerLayer layer ) {
        Set<JokerComponent> jokerComponents = layer.components();
        for( JokerComponent jokerComponent : jokerComponents ) {
            this.mount( jokerComponent );
        }
    }

    @Override
    public void destruct( JokerLayer layer ) {
        draggableHandler.destruct( layer );
        selectableHandler.destruct( layer );
        for( JokerComponent component : new HashSet<>( components )) {
            unmount( component );
        }
    }

    @Override
    public void mount( JokerComponent component ) {
        if( !components.contains( component ) ) {
            prepareListeners( component );
            components.add  ( component );
        }
    }

    private void prepareListeners( JokerComponent component ) {
        component.addStatusChangedListener( listener );
        tryTriggerAllStateOnce( component );
    }

    private void tryTriggerAllStateOnce( JokerComponent component ) {
        listener.draggableChanged ( new StatusEvent( component, StatusType.Draggable , component.isDraggable()  ) );
        listener.resizableChanged ( new StatusEvent( component, StatusType.Resizable , component.isResizable()  ) );
        listener.selectableChanged( new StatusEvent( component, StatusType.Selectable, component.isSelectable() ) );
        listener.selectedChanged  ( new StatusEvent( component, StatusType.Selected  , component.isSelected()   ) );
    }

    @Override
    public void unmount( JokerComponent component ) {
        if( components.contains( component ) ) {
            component.removeStatusChangedListener( listener );
            triggerAllFalseOnce( component );
            components.remove  ( component );
        }
    }

    private void triggerAllFalseOnce( JokerComponent component ) {
        listener.draggableChanged ( new StatusEvent( component, StatusType.Draggable , false ) );
        listener.resizableChanged ( new StatusEvent( component, StatusType.Resizable , false ) );
        listener.selectableChanged( new StatusEvent( component, StatusType.Selectable, false ) );
        listener.selectedChanged  ( new StatusEvent( component, StatusType.Selected  , false ) );
    }

    @Override
    public Set<JokerComponent> components() {
        return new HashSet<>( components );
    }

    @Override
    public boolean isSelectingArea() {
        return selectableHandler.isSelectingArea();
    }

    @Override
    public Rectangle getSelectingArea() {
        return selectableHandler.getSelectingArea();
    }

    @Override
    public Set<JokerComponent> selectedComponents() {
        return selectableHandler.selectedComponents();
    }
}
