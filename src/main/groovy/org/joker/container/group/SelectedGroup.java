package org.joker.container.group;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.component.listener.DragListener;
import org.joker.container.abstracts.SelectGroup;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SelectedGroup extends JokerObject implements SelectGroup {

    private Set<JokerComponent> selectedSet = new HashSet<>();
    private DragListener dragListener = new DragListener();

    @Override
    public void select( JokerComponent component ) {
        if( !component.isSelected() ){
            component.select();
            if( component.isDraggable() ){
                component.addMouseMotionListener( dragListener.dragWith( component ) );
            }
            selectedSet.add( component );
        }
    }

    @Override
    public void unselect( JokerComponent component ) {
        if( component.isSelected() ){
            component.unselect();
            component.removeMouseMotionListener( dragListener.dragWithout( component ) );
            selectedSet.remove( component );
        }
    }

    @Override
    public void unselectAll() {
        for( JokerComponent selectedComponent : new LinkedList<>( selectedSet ) ){
            unselect( selectedComponent );
        }
    }

    @Override
    public Set< JokerComponent > components() {
        return new HashSet<>( selectedSet );
    }

}
