package org.joker.component.event

import com.google.inject.Guice
import com.google.inject.Injector
import guice.modules.TestModule
import org.fest.swing.fixture.FrameFixture
import org.joker.component.JokerComponent
import robot.FestRobot
import spock.lang.Shared
import spock.lang.Specification

import java.awt.*

class DragEventTest extends Specification {

    @Shared Injector injector = Guice.createInjector(new TestModule())
    @Shared FrameFixture window

    def setup(){
        window = injector.getInstance(FrameFixture.class)
        window.show()
    }

    def cleanup(){
        window.cleanUp()
    }

    def "test drag use with"(){
        given:
            def component = new JokerComponent()
            def dragEvent = new DragEvent().with(component)
            component.addMouseMotionListener(dragEvent)
            component.setName("dragEventComponent")
            component.setBounds(0,0,50,50)
        and:
            window.component().setSize(300,300)
            window.component().add(component)
        and:
            def robot = new FestRobot(window)
        when:
            robot.drag().from(new Point(25,25)).to(new Point(125,125))
        then:
            robot.findComponentAt(125,125).getName() == "dragEventComponent"
    }

    def "get active components1"(){
        given:
            def component = new JokerComponent()
            def dragEvent = new DragEvent().with(component)
        expect:
            dragEvent.components().contains(component)
    }

    def "get active components2"(){
        given:
            def component = new JokerComponent()
            def dragEvent = new DragEvent().with(component).without(component)
        expect:
            !dragEvent.components().contains(component)
    }


}
