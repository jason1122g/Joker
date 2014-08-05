package guice.provider;

import com.google.inject.Provider;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;

import javax.swing.*;

public class FrameFixtureProvider implements Provider<FrameFixture> {

    @Override
    public FrameFixture get() {

        JFrame resultFrame = GuiActionRunner.execute( new GuiQuery<JFrame>() {
            protected JFrame executeInEDT() {
                return  initFrame ();
            }
        });

        return new FrameFixture( resultFrame );
    }

    private JFrame initFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setLayout( null );
        frame.setSize( 300, 300 );
        return frame;
    }
}
