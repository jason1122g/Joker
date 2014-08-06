package org.joker.container.group;

import org.joker.component.JokerComponent;
import org.joker.component.listener.DragListener;
import org.joker.container.abstracts.StatusGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DraggableGroup implements StatusGroup {

    private Map<JokerComponent, DragListener> listenerMap = new HashMap<>();

    @Override
    public void add( JokerComponent component ) {
        if( !listenerMap.containsKey( component ) ){
            DragListener dragListener = new DragListener().with( component );
            component.addMouseMotionListener( dragListener );
            listenerMap.put( component, dragListener );
        }
    }

    @Override
    public void remove( JokerComponent component ) {
        if( listenerMap.containsKey( component ) ){
            DragListener dragListener = listenerMap.get( component );
            component.removeMouseMotionListener( dragListener );
            listenerMap.remove( component );
        }
    }

    @Override
    public void removeAll() {
        for( Map.Entry< JokerComponent, DragListener > entry : listenerMap.entrySet() ){
            entry.getKey().removeMouseMotionListener( entry.getValue() );
        }
        listenerMap.clear();
    }

    @Override
    public Set<JokerComponent> components() {
        return listenerMap.keySet();
    }

}
