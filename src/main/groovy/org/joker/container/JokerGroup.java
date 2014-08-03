package org.joker.container;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.component.listener.DragListener;
import org.joker.container.abstracts.SelectGroup;

import java.util.HashSet;
import java.util.Set;

public class JokerGroup extends JokerObject implements SelectGroup{

    private DragListener dragListener = new DragListener();
    private Set<JokerComponent> selectedSet = new HashSet<JokerComponent>();

    @Override
    public void select( JokerComponent component ) {
        if( component.isSelectable() && !selectedSet.contains( component ) ){
            dragListener.with( component );
            component.select();
            component.addMouseMotionListener(dragListener);
            selectedSet.add( component );
        }
    }

    @Override
    public void unselect( JokerComponent component ) {
        if( selectedSet.contains( component ) ){
            dragListener.without( component );
            component.unselect();
            component.removeMouseMotionListener(dragListener);
            selectedSet.remove( component );
        }
    }

    @Override
    public void unselectAll() {
        for( JokerComponent selectedComponent : dragListener.components() ){
            unselect( selectedComponent );
        }
    }

    @Override
    public Set< JokerComponent > components() {
        return dragListener.components();
    }

}
