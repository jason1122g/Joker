package org.joker.container;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.component.event.DragEvent;
import org.joker.container.abstracts.SelectGroup;

import java.util.HashSet;
import java.util.Set;

public class JokerGroup extends JokerObject implements SelectGroup{

    private DragEvent dragEvent = new DragEvent();
    private Set<JokerComponent> selectedSet = new HashSet<JokerComponent>();

    @Override
    public void select( JokerComponent component ) {
        if( component.isSelectable() && !selectedSet.contains( component ) ){
            dragEvent.with( component );
            component.select();
            component.addMouseMotionListener( dragEvent );
            selectedSet.add( component );
        }
    }

    @Override
    public void unselect( JokerComponent component ) {
        if( selectedSet.contains( component ) ){
            dragEvent.without( component );
            component.unselect();
            component.removeMouseMotionListener( dragEvent );
            selectedSet.remove( component );
        }
    }

    @Override
    public void unselectAll() {
        for( JokerComponent selectedComponent : dragEvent.components() ){
            unselect( selectedComponent );
        }
    }

    @Override
    public Set< JokerComponent > components() {
        return dragEvent.components();
    }

}
