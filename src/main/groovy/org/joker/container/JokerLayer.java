package org.joker.container;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.container.abstracts.ComponentHandler;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class JokerLayer extends JokerObject {


    private ComponentHandler componentHandler;

    public JokerLayer(){
        this.setLayout( null );
    }

    @Override
    public Component add( Component component ) {
        if( component instanceof JokerComponent && componentHandler != null ) {
            componentHandler.mount( (JokerComponent) component );
        }
        return super.add( component );
    }

    @Override
    public void remove( Component component ) {
        if( component instanceof JokerComponent && componentHandler != null ) {
            componentHandler.unmount( (JokerComponent) component );
        }
        super.remove( component );
    }

    public void setComponentHandler( ComponentHandler componentHandler ) {
        if( componentHandler == null ) {
            throw new IllegalArgumentException( "componentHandler cannot be null" );
        }
        if( this.componentHandler != null ) {
            this.componentHandler.destruct( this );
        }
        this.componentHandler = componentHandler;
        this.componentHandler.construct( this );
    }

    public ComponentHandler getComponentHandler() {
        return componentHandler;
    }

    public Set< JokerComponent > components() {
        Component[] components = this.getComponents();
        Set<JokerComponent> jokerComponents = new HashSet<>();
        for( Component component : components ) {
            if( component instanceof JokerComponent ){
                jokerComponents.add( (JokerComponent) component );
            }
        }
        return jokerComponents;
    }

}
  
