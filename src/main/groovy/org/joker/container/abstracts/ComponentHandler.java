package org.joker.container.abstracts;

import org.joker.component.JokerComponent;
import org.joker.container.JokerLayer;

public interface ComponentHandler extends ComponentSet {

    public void construct( JokerLayer layer );
    public void destruct ( JokerLayer layer );

    public void mount  ( JokerComponent component );
    public void unmount( JokerComponent component );

}
