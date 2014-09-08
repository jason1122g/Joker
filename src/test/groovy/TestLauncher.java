import org.joker.component.JokerComponent;
import org.joker.container.JokerCanvas;
import org.joker.container.JokerLayer;
import org.joker.container.abstracts.ComponentHandler;
import org.joker.container.abstracts.ComponentSelectHandler;
import org.joker.container.handler.BasicSelectHandler;

import javax.swing.*;
import java.awt.*;

public class TestLauncher {

    public static void main(String[] args){
        module1();
    }

    private static void module1() {
        final JokerLayer layer = new JokerLayer(){
            public void paintComponent(Graphics g){

                ComponentHandler handler = getComponentHandler();

                if( handler instanceof ComponentSelectHandler ) {
                    ComponentSelectHandler selectHandler = ( ComponentSelectHandler ) handler;
                    for ( JokerComponent comp : selectHandler.selectedComponents() ){
                        g.fillRect( comp.getX() + comp.getWidth()/2 -3, comp.getY()-6, 6, 6);
                        g.fillRect( comp.getX() + comp.getWidth()/2 -3, comp.getY() + comp.getHeight(), 6, 6);
                        g.fillRect( comp.getX() -6, comp.getY() + comp.getHeight()/2 -3, 6, 6 );
                        g.fillRect( comp.getX() + comp.getWidth(), comp.getY() + comp.getHeight()/2 -3, 6, 6 );
                    }

                    if( selectHandler.isSelectingArea() ){
                        Rectangle selectingArea = selectHandler.getSelectingArea();
                        g.drawRect( selectingArea.x, selectingArea.y, selectingArea.width, selectingArea.height );
                    }
                }

                g.drawRect( 1, 1, this.getWidth() - 3, this.getHeight() - 3 );
            }
        };
        layer.setComponentHandler( new BasicSelectHandler() );


        Color[] colors = { Color.black, Color.cyan, Color.blue, Color.darkGray, Color.green, Color.orange };
        for( int i = 0 ; i < 5 ; i ++ ){
            final Color color = colors[i];
            final int number  = i;
            JokerComponent component = new JokerComponent(){
                public void paintComponent(Graphics g){
                    g.setColor( color );
                    g.drawRect( 0, 0, this.getWidth() - 1, this.getHeight() - 1 );
                    g.drawString( number + "", 10, 10 );
                }
            };
            component.setBounds( 10 + i * 50, 10 + i * 50, 50, 50 );
            component.setSelectable( true );
            component.setDraggable ( true );
            layer.add( component );
        }

        JokerCanvas canvas = new JokerCanvas();
        canvas.add( layer );

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setSize( 500, 500 );
        frame.setLayout( new BorderLayout() );
        frame.add( canvas, BorderLayout.CENTER );
        frame.setVisible( true );
    }

}