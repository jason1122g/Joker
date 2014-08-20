package org.joker.component.listener

import org.jason1122g.gionic.awt.simulator.Gionic
import org.jason1122g.gionic.core.Simulator
import org.joker.component.JokerComponent
import org.joker.component.event.SelectEvent
import org.joker.container.abstracts.SelectObserver
import spock.lang.Specification

import javax.swing.*
import java.awt.event.MouseAdapter

class SelectSingleListenerTest2 extends Specification{

    class TestContainer extends JComponent implements SelectObserver {
        @Override
        void notify(SelectEvent selectEvent) {

        }

        @Override
        Set<JokerComponent> selectedComponents() {
            return null
        }
    }

    TestContainer  container
    Simulator listener

    def setup(){ "use triggerFrom() to init and specify the event source"

        def component = Mock( JokerComponent ){
            getParent() >> { return container }
        }
        def listener  = SelectSingleListener.triggerFrom( component )

        this.container = Spy( TestContainer )
        this.listener = Gionic.control( listener as MouseAdapter )
    }

    def "click on listener component will lead to call notify() of its parent"(){
        when:
            listener.click().atSomeWhere()
        then:
            1 * container.notify( _ as SelectEvent)
    }

    def "mouse drag in 3px will notify its parent"(){
        when:
            listener.drag().from( 1, 0 ).to( 3, 0 ).endHere()
        then:
            1 * container.notify( _ as SelectEvent)
    }

    def "mouse drag over 3px will not notify its parent"(){
        when:
            listener.drag().from( 1, 0 ).to( 9, 0 ).endHere()
        then:
            0 * container.notify( _ as SelectEvent)
    }

    def "mouse press, exit then release will not notify its parent"(){
        when:
            listener.press().at( 1, 0 )
            listener.exit().atSomeWhere()
            listener.release().at( 3, 0 )
        then:
            0 * container.notify( _ as SelectEvent)
    }

}
