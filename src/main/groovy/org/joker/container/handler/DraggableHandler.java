package org.joker.container.handler;

import org.joker.component.JokerComponent;
import org.joker.component.listener.DragListener;
import org.joker.container.JokerLayer;
import org.joker.container.abstracts.ComponentHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DraggableHandler implements ComponentHandler {

    private Map<JokerComponent, DragListener> listenerMap;

    @Override
    public void construct( JokerLayer layer ) {
        listenerMap = new HashMap<>();
    }

    @Override
    public void destruct( JokerLayer layer ) {
        for( Map.Entry< JokerComponent, DragListener > entry : new HashMap<>( listenerMap ).entrySet() ){
            unmount( entry.getKey() );
        }
    }

    @Override
    public void mount( JokerComponent component ) {
        if( !listenerMap.containsKey( component ) ){
            listenerMap.put( component, addAndGetListener( component ) );
        }
    }

    private DragListener addAndGetListener( JokerComponent component ) {
        DragListener dragListener = new DragListener().dragWith( component );
        component.addMouseMotionListener( dragListener );
        return dragListener;
    }

    @Override
    public void unmount( JokerComponent component ) {
        if( listenerMap.containsKey( component ) ) {
            component.removeMouseMotionListener( listenerMap.get( component ) );
            listenerMap.remove( component );
        }
    }

    @Override
    public Set<JokerComponent> components() {
        return listenerMap.keySet();
    }

}
