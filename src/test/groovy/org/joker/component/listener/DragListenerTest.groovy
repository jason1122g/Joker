package org.joker.component.listener

import com.google.inject.Guice
import com.google.inject.Injector
import guice.modules.TestModule
import org.joker.component.JokerComponent
import robot.EventSimulator
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
            def dragEvent = new DragListener().with( component )
        and:
            component.addMouseMotionListener( dragEvent )
            component.setBounds( 0, 0, 50, 50 )
        and:
            frame.add( component )
        when:
            new EventSimulator(component).drag().from( new Point(25,25) ).to( new Point(125,125) )
        then:
            component.getLocation() == new Point( 100, 100 )
    }

}
