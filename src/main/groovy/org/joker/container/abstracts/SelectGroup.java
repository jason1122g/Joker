package org.joker.container.abstracts;

import org.joker.component.JokerComponent;

import java.util.Set;

public interface SelectGroup {

    public void select  ( JokerComponent component );
    public void unselect( JokerComponent component );
    public void unselectAll();

    public Set<JokerComponent> components();

}
