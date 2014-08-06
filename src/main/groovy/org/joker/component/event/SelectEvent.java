package org.joker.component.event;

import org.joker.component.JokerComponent;
import org.joker.component.event.abstracts.EventSource;

import java.awt.event.MouseEvent;

public class SelectEvent implements EventSource {

    private JokerComponent component;
    private MouseEvent     mouseEvent;

    public SelectEvent( JokerComponent component, MouseEvent mouseEvent ) {
        this.component  = component ;
        this.mouseEvent = mouseEvent;
    }

    public JokerComponent getSource(){
        return component;
    }

    public MouseEvent     getMouseEvent(){
        return mouseEvent;
    }

}
