package org.joker.component.event;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickThroughEvent extends MouseAdapter {

    private JComponent component;

    public ClickThroughEvent through(JComponent component){
        this.component = component;
        return this;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        component.getParent().dispatchEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        component.getParent().dispatchEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        component.getParent().dispatchEvent(e);
    }
}
