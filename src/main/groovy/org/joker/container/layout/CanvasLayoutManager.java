package org.joker.container.layout;

import org.joker.container.JokerLayer;

import java.awt.*;

/**
 * TODO TEST AND FINISH THIS
 */
public class CanvasLayoutManager implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Insets    insets    = parent.getInsets();
        Dimension dimension = new Dimension();
        dimension.width  = parent.getWidth() + insets.left + insets.right;
        dimension.height = parent.getHeight()+ insets.top  + insets.bottom;
        return dimension;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        for(Component component: parent.getComponents()){
            if(component instanceof JokerLayer){
                component.setBounds(0,0,parent.getWidth(),parent.getHeight());
            }
        }
    }
}
