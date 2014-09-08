package org.joker.container.handler

import com.google.inject.Guice
import com.google.inject.Injector
import guice.modules.TestModule
import org.jason1122g.gionic.awt.simulator.Gionic
import org.joker.component.JokerComponent
import org.joker.container.abstracts.ComponentHandler
import spock.lang.Shared
import spock.lang.Specification

import javax.swing.*
import java.awt.*

class DraggableHandlerTest extends Specification {

    @Shared Injector injector = Guice.createInjector( new TestModule() )
    @Shared JFrame frame

    @Shared ComponentHandler dragHandler = new DraggableHandler()
    JokerComponent component1
    JokerComponent component2

    def setup() { "init 2 jokerComponents and add them to a frame, construct handler"
        initFrame()
        initComponents()
        initHandler()
    }

    private void initComponents() {
        frame.add( ( component1 = new JokerComponent() ) ) //TODO USE MOCK
        frame.add( ( component2 = new JokerComponent() ) )
    }

    private void initFrame() {
        frame = injector.getInstance( JFrame.class )
        frame.setVisible( true )
    }

    private void initHandler() {
        dragHandler.construct( null )
    }

    def cleanup() {
        frame.dispose()
        dragHandler.destruct( null )
    }

    def "use mount() on jokerComponent then it can be dragged"() {
        given:
            dragHandler.mount( component1 )
        and:
            component1.setBounds( 0, 0, 50, 50 )
        when:
            def componentSimu = Gionic.control( component1 )
        and:
            componentSimu.move().from( 0,0 ).to( 25, 25 ).endHere()
            componentSimu.drag().from( 25, 25 ).to( 125, 125 ).endHere()
        then:
            component1.getLocation() == new Point( 100, 100 )
    }

    def "use unmount() on jokerComponent then it can not be dragged"() {
        given:
            dragHandler.mount  ( component1 )
            dragHandler.unmount( component1 )
        and:
            component1.setBounds( 0, 0, 50, 50 )
        when:
            def componentSimu = Gionic.control( component1 )
        and:
            componentSimu.move().from( 0,0 ).to( 25, 25 ).endHere()
            componentSimu.drag().from( 25, 25 ).to( 125, 125 ).endHere()
        then:
            component1.getLocation() != new Point( 100, 100 )
    }

    def "use components() to get all mounted components"() {
        expect:
            dragHandler.components().size() == 0
        when:
            dragHandler.mount( component1 )
            dragHandler.mount( component2 )
        then:
            dragHandler.components().size() == 2
        when:
            dragHandler.unmount( component2 )
        then:
            dragHandler.components().size() == 1
    }

}
