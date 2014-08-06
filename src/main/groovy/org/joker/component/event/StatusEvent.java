package org.joker.component.event;

import org.joker.component.JokerComponent;
import org.joker.component.event.abstracts.EventSource;

public class StatusEvent implements EventSource {

    private JokerComponent component;
    private StatusType     statusType;
    private boolean value;

    public StatusEvent( JokerComponent component, StatusType statusType,  boolean value ) {
        this.component  = component;
        this.statusType = statusType;
        this.value      = value;
    }

    public JokerComponent getSource(){
        return component;
    }

    public StatusType getType (){
        return statusType;
    }

    public boolean    getValue(){
        return value;
    }
}
