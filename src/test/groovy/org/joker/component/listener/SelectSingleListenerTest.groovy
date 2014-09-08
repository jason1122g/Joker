package org.joker.component.listener

import org.jason1122g.gionic.awt.simulator.Gionic
import org.jason1122g.gionic.core.Simulator
import org.joker.component.JokerComponent
import org.joker.component.event.SelectEvent
import org.joker.container.abstracts.SelectObserver
import spock.lang.Specification

import java.awt.event.MouseAdapter

class SelectSingleListenerTest extends Specification{

    SelectObserver observer
    Simulator listener

    def setup(){ "use triggerFrom() and toObserver() to init and specify the event source"
        this.observer = Mock( SelectObserver )
        def listener  = SelectSingleListener.triggerFrom( Mock( JokerComponent ) ).toObserver( observer )
        this.listener = Gionic.control( listener as MouseAdapter )
    }

    def "click on listener component will lead to call notify() of its parent"(){
        when:
            listener.click().atSomeWhere()
        then:
            1 * observer.notify( _ as SelectEvent)
    }

    def "mouse drag in 3px will notify its parent"(){
        when:
            listener.drag().from( 1, 0 ).to( 3, 0 ).endHere()
        then:
            1 * observer.notify( _ as SelectEvent)
    }

    def "mouse drag over 3px will not notify its parent"(){
        when:
            listener.drag().from( 1, 0 ).to( 9, 0 ).endHere()
        then:
            0 * observer.notify( _ as SelectEvent)
    }

    def "mouse press, exit then release will not notify its parent"(){
        when:
            listener.press().at( 1, 0 )
            listener.exit().atSomeWhere()
            listener.release().at( 3, 0 )
        then:
            0 * observer.notify( _ as SelectEvent)
    }

}
