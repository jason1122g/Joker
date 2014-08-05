package org.joker.component.listener

import org.joker.component.JokerComponent
import org.joker.component.event.SelectEvent
import org.joker.container.abstracts.SelectObserver
import robot.EventSimulator
import spock.lang.Specification

import javax.swing.*
import java.awt.*

class SelectListenerTest2 extends Specification{

    class TestContainer extends JComponent implements SelectObserver {
        @Override
        void notify(SelectEvent selectEvent) {

        }
    }

    TestContainer  container
    EventSimulator component

    def setup(){ "use triggerFrom() to specify the event source"
        def component = new JokerComponent()
        def listener  = new SelectListener().triggerFrom( component )

        this.container = Spy( TestContainer )
        this.container.add( component )

        component.addMouseListener( listener )
        component.addMouseMotionListener( listener )

        this.component = new EventSimulator( component )
    }

    def "click on listener component will lead to call notify() of its parent"(){
        when:
            component.click()
        then:
            1 * container.notify( _ as SelectEvent)
    }

    def "mouse drag in 3px will notify its parent"(){
        when:
            component.drag().from( new Point(1,0) ).to( new Point(3,0) )
        then:
            1 * container.notify( _ as SelectEvent)
    }

    def "mouse drag over 3px will not notify its parent"(){
        when:
            component.drag().from( new Point(1,0) ).to( new Point(9,0) )
        then:
            0 * container.notify( _ as SelectEvent)
    }

    def "mouse press, exit then release will not notify its parent"(){
        when:
            component.mousePress  ( new Point(1,0) )
            component.mouseExit()
            component.mouseRelease( new Point(3,0) )
        then:
            0 * container.notify( _ as SelectEvent)
    }

}
