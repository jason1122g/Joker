package guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.swing.*;

public class testModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Provides
    JFrame provideJFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
        return frame;
    }

    @Provides
    JPanel provideJPanel(){
        JPanel panel = new JPanel();
        panel.setSize(300,300);
        return panel;
    }
}
