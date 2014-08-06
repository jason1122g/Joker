package org.joker.tool;

import org.joker.exceptions.IllegalContainerException;

import java.awt.*;

public class Parent {

    private Component component;

    private Parent( Component component ){
        this.component = component;
    }

    public static Parent of( Component component ){
        return new Parent( component );
    }

    public <T> T as ( Class<T> classType ){
        if( component.getParent() == null ){
            throw new IllegalContainerException( "must have a container" );
        }
        try{
            return classType.cast( component.getParent() )   ;
        }catch ( ClassCastException e ){
            throw new IllegalContainerException( "container must be " + classType.getClass().getSimpleName() );
        }
    }

}
