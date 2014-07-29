package org.joker.component;

import org.joker.JokerObject;
import org.joker.component.abstracts.Draggable;
import org.joker.component.abstracts.Resizable;
import org.joker.component.event.ClickThroughEvent;

/**
 * TODO TEST AND FINISH THIS
 */
public class JokerComponent extends JokerObject implements Draggable,Resizable {

    private boolean isDraggable;
    private boolean isResizable;

    public JokerComponent(){
        this.addMouseListener(new ClickThroughEvent().through(this));
    }

    @Override
    public boolean isResizable() {
        return isResizable;
    }

    @Override
    public void setResizable(boolean isResizable) {
        this.isResizable = isResizable;
    }

    @Override
    public boolean isDraggable() {
        return isDraggable;
    }

    @Override
    public void setDraggable(boolean isDraggable){
        this.isDraggable = isDraggable;
    }
}
