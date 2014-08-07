package org.joker.container.abstracts;

import org.joker.component.JokerComponent;


public interface SelectGroup extends ComponentSet {

    public void select  ( JokerComponent component );
    public void unselect( JokerComponent component );
    public void unselectAll();

}
