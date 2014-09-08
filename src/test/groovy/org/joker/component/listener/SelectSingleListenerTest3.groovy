package org.joker.component.listener

import org.jason1122g.gionic.awt.simulator.Gionic
import org.joker.component.JokerComponent
import org.joker.exceptions.IllegalComponentException
import spock.lang.Specification

import java.awt.event.MouseAdapter
import java.awt.event.MouseListener

class SelectSingleListenerTest3 extends Specification {

    def "throws exception when clicked if not specified an observer"(){
        given:
            def component = Mock( JokerComponent )
            def listener  = SelectSingleListener.triggerFrom( component ).toObserver( null ) as MouseListener
        when:
            Gionic.control( listener as MouseAdapter ).click().atSomeWhere()
        then:
            thrown( IllegalComponentException )
    }

    def "not thrown exception even wrong observer before event trigger"(){
        given:
            def component = Mock ( JokerComponent )
        when:
            SelectSingleListener.triggerFrom( component ).toObserver( null )
        then:
            notThrown( IllegalComponentException )
    }

}
