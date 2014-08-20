package org.joker.component.listener

import com.google.inject.Guice
import com.google.inject.Injector
import guice.modules.TestModule
import org.jason1122g.gionic.awt.simulator.Gionic
import org.joker.component.JokerComponent
import spock.lang.Shared
import spock.lang.Specification

import javax.swing.*
import java.awt.*

class DragListenerTest extends Specification {

    @Shared Injector injector = Guice.createInjector( new TestModule() )
    @Shared JFrame frame

    def setup(){
        frame = injector.getInstance( JFrame.class )
        frame.setVisible( true )
    }

    def cleanup(){
        frame.dispose()
    }

    def "inject draggable component into drag listener group using with(), drag distance must > 3px"(){
        given:
            def component = new JokerComponent()
            def dragEvent = new DragListener().dragWith( component )
        and:
            component.addMouseMotionListener( dragEvent )
            component.setBounds( 0, 0, 50, 50 )
        and:
            frame.add( component )
        when:
            def componentSimu = Gionic.control( component )
        and:
            componentSimu.move().from( 0,0 ).to( 25, 25 ).endHere()
            componentSimu.drag().from( 25, 25 ).to( 125, 125 ).endHere()
        then:
            component.getLocation() == new Point( 100, 100 )
    }

}
