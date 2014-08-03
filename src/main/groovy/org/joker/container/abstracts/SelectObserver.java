package org.joker.container.abstracts;

import org.joker.component.event.SelectEvent;

public interface SelectObserver {
    public void notify( SelectEvent selectEvent );
}
