package org.joker.component.event;


import org.joker.JokerObject;
import org.joker.component.listener.abstracts.StatusChangedListener;

import java.util.HashSet;
import java.util.Set;

public class StatusProvider extends JokerObject{

    private Set<StatusChangedListener> listeners = new HashSet<>();

    public void addStatusChangedListener   ( StatusChangedListener statusChangedListener ){
        listeners.add   ( statusChangedListener );
    }

    public void removeStatusChangedListener( StatusChangedListener statusChangedListener ){
        listeners.remove( statusChangedListener );
    }

    public void dispatchEvent( StatusEvent statusEvent ){
        switch ( statusEvent.getType() ){
            case Draggable:
                for( StatusChangedListener listener : listeners ){
                    listener.draggableChanged( statusEvent );
                }
                break;
            case Resizable:
                for( StatusChangedListener listener : listeners ){
                    listener.resizableChanged( statusEvent );
                }
                break;
            case Selectable:
                for( StatusChangedListener listener : listeners ){
                    listener.selectableChanged( statusEvent );
                }
                break;
            case Selected:
                for( StatusChangedListener listener : listeners ){
                    listener.selectedChanged( statusEvent );
                }
                break;
        }
    }

}
