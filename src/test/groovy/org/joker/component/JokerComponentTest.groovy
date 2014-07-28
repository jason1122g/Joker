package org.joker.component

import com.google.inject.Guice
import com.google.inject.Injector
import fest.CustomRobot
import guice.module.testModule
import org.fest.swing.core.MouseButton
import org.fest.swing.fixture.FrameFixture
import spock.lang.Shared
import spock.lang.Specification

import java.awt.*

class JokerComponentTest extends Specification {

    @Shared Injector injector = Guice.createInjector(new testModule())
    @Shared FrameFixture window

    def setup(){
        window = injector.getInstance(FrameFixture.class)
        window.show()
    }

    def cleanup(){
        window.cleanUp()
    }

    def "test drag"(){
        given:
            def robot = new CustomRobot(window)
        when:
            robot.pressMouse(new Point(15,15),MouseButton.LEFT_BUTTON)
            robot.moveMouse(new Point(100,100))
            robot.releaseMouse(MouseButton.LEFT_BUTTON)
        then:
            robot.findComponentAt(100,100).getName() == "jokerComponent"
    }

}
