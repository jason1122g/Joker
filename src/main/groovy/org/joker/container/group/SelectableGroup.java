package org.joker.container.group;

import org.joker.component.JokerComponent;
import org.joker.component.listener.SelectListener;
import org.joker.container.abstracts.StatusGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SelectableGroup implements StatusGroup {

    private Map<JokerComponent, SelectListener> listenerMap = new HashMap<>();

    @Override
    public void add( JokerComponent component ) {
        if( !listenerMap.containsKey( component ) ){
            SelectListener selectListener = new SelectListener().triggerFrom( component );
            component.addMouseListener      ( selectListener );
            component.addMouseMotionListener( selectListener );
            listenerMap.put( component, selectListener );
        }
    }

    @Override
    public void remove( JokerComponent component ) {
        if( listenerMap.containsKey( component ) ){
            SelectListener selectListener = listenerMap.get( component );
            component.removeMouseListener      ( selectListener );
            component.removeMouseMotionListener( selectListener );
            listenerMap.remove( component );
        }
    }

    @Override
    public void removeAll() {
        for( Map.Entry< JokerComponent, SelectListener > entry : listenerMap.entrySet() ){
            entry.getKey().removeMouseListener      ( entry.getValue() );
            entry.getKey().removeMouseMotionListener( entry.getValue() );
        }
        listenerMap.clear();
    }

    @Override
    public Set<JokerComponent> components() {
        return listenerMap.keySet();
    }

}
