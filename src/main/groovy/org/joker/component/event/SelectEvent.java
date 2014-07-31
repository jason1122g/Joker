package org.joker.component.event;

import org.joker.component.JokerComponent;
import org.joker.container.abstracts.SelectObserver;
import org.joker.exceptions.IllegalContainerException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectEvent extends MouseAdapter {

    private SelectObserver selectObserver;
    private JokerComponent component;

    public SelectEvent from(JokerComponent component){
        this.component = component;
        return this;
    }

    private void initObserver(){
        if(component.getParent() == null){
            throw new IllegalContainerException("must have a container");
        }
        try{
            selectObserver = (SelectObserver) component.getParent();
        }catch (ClassCastException e){
            throw new IllegalContainerException("container must be JokerLayer/SelectObserver");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(selectObserver == null){
            initObserver();
        }
        selectObserver.notify(component);
    }

}
