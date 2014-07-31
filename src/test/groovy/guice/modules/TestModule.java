package guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import guice.provider.FrameFixtureProvider;
import guice.provider.JokerCanvasProvider;
import org.fest.swing.fixture.FrameFixture;
import org.joker.container.JokerCanvas;

import javax.swing.*;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind( FrameFixture.class ).toProvider( FrameFixtureProvider.class );
        bind( JokerCanvas.class ) .toProvider( JokerCanvasProvider.class );
    }

    @Provides
    JFrame provideJFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setSize( 300, 300 );
        frame.setLayout( null );
        return frame;
    }

    @Provides
    JPanel provideJPanel(){
        JPanel panel = new JPanel();
        panel.setSize( 300, 300 );
        panel.setLayout( null );
        return panel;
    }
}
