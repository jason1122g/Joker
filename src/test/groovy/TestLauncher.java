import org.joker.component.JokerComponent;
import org.joker.container.JokerCanvas;
import org.joker.container.JokerLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestLauncher {

    public static void main(String[] args){

        final Rectangle rectangle = new Rectangle();

        final JokerLayer layer = new JokerLayer(){
            public void paintComponent(Graphics g){
                for ( JokerComponent comp : selectedComponents() ){
                    g.fillRect( comp.getX() + comp.getWidth()/2 -3, comp.getY()-6, 6, 6);
                    g.fillRect( comp.getX() + comp.getWidth()/2 -3, comp.getY() + comp.getHeight(), 6, 6);
                    g.fillRect( comp.getX() -6, comp.getY() + comp.getHeight()/2 -3, 6, 6 );
                    g.fillRect( comp.getX() + comp.getWidth(), comp.getY() + comp.getHeight()/2 -3, 6, 6 );
                }
                g.drawRect(1,1,this.getWidth()-3,this.getHeight()-3);
                g.drawRect( rectangle.x, rectangle.y, rectangle.width, rectangle.height );
            }
        };

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point originPoint;
            @Override
            public void mousePressed( MouseEvent e ) {
                originPoint = e.getPoint();
            }

            @Override
            public void mouseDragged( MouseEvent e ) {
                Rectangle tempRectangle = convertToRectangle( originPoint, e.getPoint() );
                rectangle.x = tempRectangle.x;
                rectangle.y = tempRectangle.y;
                rectangle.width  = tempRectangle.width;
                rectangle.height = tempRectangle.height;
                layer.repaint();
            }

            @Override
            public void mouseReleased( MouseEvent e ) {
                rectangle.x = 0;
                rectangle.y = 0;
                rectangle.width  = 0;
                rectangle.height = 0;
                layer.repaint();
            }
        };
        layer.addMouseListener( mouseAdapter );
        layer.addMouseMotionListener( mouseAdapter );


        Color[] colors = {Color.black,Color.cyan,Color.blue,Color.darkGray,Color.green,Color.orange};
        for(int i=0;i<5;i++){
            final Color color = colors[i];
            final int number  = i;
            JokerComponent component = new JokerComponent(){
                public void paintComponent(Graphics g){
                    g.setColor(color);
                    g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
                    g.drawString(number+"",10,10);
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

    private static Rectangle convertToRectangle( Point p1, Point p2 ){
        if( p1.y > p2.y ){
            if( p1.x > p2.x ){
                return new Rectangle( p2, new Dimension( p1.x - p2.x, p1.y - p2.y ) );
            }else{
                return new Rectangle( p1.x , p2.y, p2.x - p1.x, p1.y - p2.y );
            }
        }else{
            if( p1.x > p2.x ){
                return new Rectangle( p2.x, p1.y, p1.x - p2.x, p2.y - p1.y );
            }else{
                return new Rectangle( p1, new Dimension( p2.x - p1.x, p2.y - p1.y ) );
            }
        }
    }

}