package org.joker.component.listener;

import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;
import org.joker.container.abstracts.SelectObserver;
import org.joker.exceptions.IllegalContainerException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectListener extends MouseAdapter {

    private SelectObserver selectObserver;
    private JokerComponent component;

    public SelectListener triggerFrom( JokerComponent component ){
        this.component = component;
        return this;
    }

    private void initObserver(){
        if( component.getParent() == null ){
            throw new IllegalContainerException( "must have a container" );
        }
        try{
            selectObserver = (SelectObserver) component.getParent();
        }catch ( ClassCastException e ){
            throw new IllegalContainerException( "container must be JokerLayer/SelectObserver" );
        }
    }

    @Override
    public void mouseClicked( MouseEvent e ) {
        if(selectObserver == null){
            initObserver();
        }
        selectObserver.notify( new SelectEvent( component, e ) );
    }

}
