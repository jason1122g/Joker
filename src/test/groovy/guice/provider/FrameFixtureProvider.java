package guice.provider;

import com.google.inject.Provider;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.joker.component.JokerComponent;
import org.joker.container.JokerCanvas;
import org.joker.container.JokerLayer;

import javax.swing.*;
import java.awt.*;

public class FrameFixtureProvider implements Provider<FrameFixture> {

    @Override
    public FrameFixture get() {

        JFrame resultFrame = GuiActionRunner.execute(new GuiQuery<JFrame>() {
            protected JFrame executeInEDT() {
                JFrame frame       = initFrame ();
                JokerCanvas canvas = initCanvas(frame);
                JokerLayer layer   = initLayer (canvas);
                initComponent(layer);
                return frame;
            }
        });

        return new FrameFixture(resultFrame);
    }

    private JFrame initFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    private JokerCanvas initCanvas(JFrame frame) {
        JokerCanvas canvas = new JokerCanvas();
        canvas.setSize(250, 250);
        canvas.setName("jokerCanvas");
        frame.add(canvas, BorderLayout.CENTER);
        return canvas;
    }

    private JokerLayer initLayer(JComponent component) {
        JokerLayer layer = new JokerLayer(){
            public void paintComponent(Graphics g){
                g.drawRect(1,1,this.getWidth()-3,this.getHeight()-3);
            }
        };
        layer.setName("jokerLayer");
        component.add(layer);
        return layer;
    }

    private void initComponent(JComponent component){
        JokerComponent jokerComponent = new JokerComponent(){
            public void paintComponent(Graphics g){
                g.drawRect(3,3,this.getWidth()-4,this.getHeight()-4);
            }
        };
        jokerComponent.setName("jokerComponent");
        jokerComponent.setBounds(10, 10, 50, 50);
        jokerComponent.setDraggable(true);
        component.add(jokerComponent);
    }
}
