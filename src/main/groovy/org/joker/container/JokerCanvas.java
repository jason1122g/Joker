package org.joker.container;

import org.joker.JokerObject;
import org.joker.container.layout.CanvasLayout;

public class JokerCanvas extends JokerObject {

    public JokerCanvas(){
        this.setLayout( new CanvasLayout() );
    }

}
