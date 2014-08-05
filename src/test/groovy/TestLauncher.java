import org.joker.component.JokerComponent;
import org.joker.container.JokerCanvas;
import org.joker.container.JokerLayer;

import javax.swing.*;
import java.awt.*;

public class TestLauncher {
    public static void main(String[] args){

        JokerLayer layer = new JokerLayer(){
            public void paintComponent(Graphics g){
                g.drawRect(1,1,this.getWidth()-3,this.getHeight()-3);
            }
        };


        Color[] colors = {Color.black,Color.cyan,Color.blue,Color.darkGray,Color.green,Color.orange};
        for(int i=0;i<5;i++){
            final Color color = colors[i];
            final int number  = i;
            JokerComponent component = new JokerComponent(){
                public void paintComponent(Graphics g){
                    g.setColor(color);
                    g.drawRect(3,3,this.getWidth()-4,this.getHeight()-4);
                    g.drawString(number+"",10,10);
                    if( isSelected() ){
                        g.fillRect( getWidth()/2-3,-6,6,12 );
                        g.fillRect( getWidth()/2-3,getHeight()-6,6,12 );
                        g.fillRect( -6,getHeight()/2-3,12,6 );
                        g.fillRect( getWidth()-6,getHeight()/2-3,12,6 );
                    }
                }
            };
            component.setBounds(10+i*50,10+i*50,50,50);
            component.setSelectable(true);
            component.setDraggable(true);
            layer.add(component);
        }


        JokerCanvas canvas = new JokerCanvas();
        canvas.add(layer);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}