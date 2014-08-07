package org.joker.container.abstracts;

import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;

import java.util.Set;

public interface SelectObserver {
    public void notify( SelectEvent selectEvent );
    public Set<JokerComponent> selectedComponents();
}
