package org.joker.container.group;

import org.joker.component.JokerComponent;
import org.joker.component.listener.SelectSingleListener;
import org.joker.container.abstracts.StatusGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SelectableGroup implements StatusGroup {

    private Map<JokerComponent, SelectSingleListener> listenerMap = new HashMap<>();

    @Override
    public void add( JokerComponent component ) {
        if( !listenerMap.containsKey( component ) ){
            SelectSingleListener selectSingleListener = SelectSingleListener.triggerFrom( component );
            component.addMouseListener      ( selectSingleListener );
            component.addMouseMotionListener( selectSingleListener );
            listenerMap.put( component, selectSingleListener );
        }
    }

    @Override
    public void remove( JokerComponent component ) {
        if( listenerMap.containsKey( component ) ){
            SelectSingleListener selectSingleListener = listenerMap.get( component );
            component.removeMouseListener      ( selectSingleListener );
            component.removeMouseMotionListener( selectSingleListener );
            listenerMap.remove( component );
        }
    }

    @Override
    public void removeAll() {
        for( Map.Entry< JokerComponent, SelectSingleListener> entry : listenerMap.entrySet() ){
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
