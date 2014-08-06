package org.joker.component.listener;

import org.joker.component.event.StatusEvent;
import org.joker.component.listener.abstracts.StatusChangedAdpater;
import org.joker.container.abstracts.StatusGroup;
import org.joker.container.group.DraggableGroup;
import org.joker.container.group.SelectableGroup;

public class StatusListener extends StatusChangedAdpater {

    private StatusGroup draggableGroup  = new DraggableGroup();
    private StatusGroup selectableGroup = new SelectableGroup();

    @Override
    public void draggableChanged( StatusEvent event ) {
        if( event.getValue() ){
            draggableGroup.add   ( event.getSource() );
        }else{
            draggableGroup.remove( event.getSource() );
        }
    }

    @Override
    public void selectableChanged( StatusEvent event ) {
        if( event.getValue() ){
            selectableGroup.add   ( event.getSource() );
        }else{
            selectableGroup.remove( event.getSource() );
        }
    }

}
