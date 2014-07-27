package org.joker.container;


import org.joker.JokerObject;
import org.joker.container.layout.CanvasLayoutManager;


/**
 * TODO TEST AND FINISH THIS
 */
public class JokerCanvas extends JokerObject {

    public JokerCanvas(){
        this.setLayout(new CanvasLayoutManager());
    }

}
