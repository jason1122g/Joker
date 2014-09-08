package org.joker.component.listener;

import org.joker.component.event.StatusEvent;
import org.joker.component.listener.abstracts.StatusChangedAdpater;
import org.joker.container.abstracts.ComponentHandler;

public class StatusListener extends StatusChangedAdpater {

    private ComponentHandler draggableGroup;
    private ComponentHandler selectableGroup;

    public StatusListener( ComponentHandler draggableGroup, ComponentHandler selectableGroup  ) {
        this.draggableGroup  = draggableGroup;
        this.selectableGroup = selectableGroup;
    }

    @Override
    public void draggableChanged( StatusEvent event ) {
        if( event.getValue() ){
            draggableGroup.mount( event.getSource() );
        }else{
            draggableGroup.unmount( event.getSource() );
        }
    }

    @Override
    public void selectableChanged( StatusEvent event ) {
        if( event.getValue() ){
            selectableGroup.mount( event.getSource() );
        }else{
            selectableGroup.unmount( event.getSource() );
        }
    }

}
