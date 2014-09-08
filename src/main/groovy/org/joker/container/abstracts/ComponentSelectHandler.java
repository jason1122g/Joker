package org.joker.container.abstracts;

import org.joker.component.JokerComponent;

import java.awt.*;
import java.util.Set;

public interface ComponentSelectHandler extends ComponentHandler {
    public boolean   isSelectingArea();
    public Rectangle getSelectingArea();
    public Set<JokerComponent> selectedComponents();
}
