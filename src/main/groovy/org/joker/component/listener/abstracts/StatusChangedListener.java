package org.joker.component.listener.abstracts;

import org.joker.component.event.StatusEvent;

public interface StatusChangedListener {
    public void draggableChanged ( StatusEvent event );
    public void resizableChanged ( StatusEvent event );
    public void selectableChanged( StatusEvent event );
    public void selectedChanged  ( StatusEvent event );
}
