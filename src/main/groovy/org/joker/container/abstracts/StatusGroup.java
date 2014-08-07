package org.joker.container.abstracts;

import org.joker.component.JokerComponent;

public interface StatusGroup extends ComponentSet {

    public void add   ( JokerComponent component );
    public void remove( JokerComponent component );
    public void removeAll();

}
