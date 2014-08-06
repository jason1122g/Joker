package org.joker.container.abstracts;

import org.joker.component.JokerComponent;

import java.util.Set;

public interface StatusGroup {

    public void add   ( JokerComponent component );
    public void remove( JokerComponent component );
    public void removeAll();

    public Set<JokerComponent> components();

}
