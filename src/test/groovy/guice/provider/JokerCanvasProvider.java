package guice.provider;

import com.google.inject.Provider;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.joker.component.JokerComponent;
import org.joker.container.JokerCanvas;
import org.joker.container.JokerLayer;

import javax.swing.*;
import java.awt.*;

public class JokerCanvasProvider implements Provider<JokerCanvas> {

    @Override
    public JokerCanvas get() {
        return GuiActionRunner.execute(new GuiQuery<JokerCanvas>() {
            protected JokerCanvas executeInEDT() {
                JokerCanvas canvas = initCanvas();
                JokerLayer layer = initLayer(canvas);
                initComponent(layer);
                return canvas;
            }
        });
    }

    private JokerCanvas initCanvas() {
        JokerCanvas canvas = new JokerCanvas();
        canvas.setSize(250, 250);
        canvas.setName("jokerCanvas");
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
        jokerComponent.setName("jokerComponent1");
        jokerComponent.setBounds(10, 10, 50, 50);
        jokerComponent.setDraggable(true);
        component.add(jokerComponent);
    }
}
