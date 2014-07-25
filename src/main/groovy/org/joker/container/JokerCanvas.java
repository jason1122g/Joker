package org.joker.container;


import org.joker.component.JokerComponent;
import org.joker.container.layout.CanvasLayoutManager;


/**
 * TODO TEST AND FINISH THIS
 */
public class JokerCanvas extends JokerComponent{

    public JokerCanvas(){
        this.setLayout(new CanvasLayoutManager());
    }

}
