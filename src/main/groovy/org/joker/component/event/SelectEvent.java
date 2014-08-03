package org.joker.component.event;

import org.joker.component.JokerComponent;

import java.awt.event.MouseEvent;

public class SelectEvent {

    private JokerComponent component;
    private MouseEvent     mouseEvent;

    public SelectEvent( JokerComponent component, MouseEvent mouseEvent ) {
        this.component  = component;
        this.mouseEvent = mouseEvent;
    }

    public JokerComponent getSource(){
        return component;
    }

    public MouseEvent     getMouseEvent(){
        return mouseEvent;
    }

}
