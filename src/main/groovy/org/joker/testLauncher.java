package org.joker;


import org.joker.component.JokerComponent;
import org.joker.container.JokerCanvas;
import org.joker.container.JokerLayer;

import javax.swing.*;
import java.awt.*;

/**
 * TODO TEST AND FINISH THIS
 */
public class testLauncher {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        JokerCanvas canvas = new JokerCanvas();
        canvas.setSize(250, 250);
        frame.add(canvas, BorderLayout.CENTER);

        JokerLayer layer = new JokerLayer(){
            public void paintComponent(Graphics g){
                g.drawRect(1,1,this.getWidth()-3,this.getHeight()-3);
            }
        };
        JokerComponent component = new JokerComponent(){
            public void paintComponent(Graphics g){
                g.drawRect(3,3,this.getWidth()-4,this.getHeight()-4);
            }
        };
        component.setBounds(10,10,50,50);
        component.setDraggable(true);
        layer.add(component);
        canvas.add(layer);


        frame.setVisible(true);
    }

}
