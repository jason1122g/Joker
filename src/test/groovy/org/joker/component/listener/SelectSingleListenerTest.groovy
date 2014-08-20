package org.joker.component.listener

import org.jason1122g.gionic.awt.simulator.Gionic
import org.joker.component.JokerComponent
import org.joker.exceptions.IllegalContainerException
import spock.lang.Specification

import javax.swing.*
import java.awt.event.MouseListener

class SelectSingleListenerTest extends Specification {

    def "throws exception when clicked if component parent is not observer"(){
        given:
            def component = Mock( JokerComponent ){
                getParent() >> new JPanel()
            }
        when:
            Gionic.control( SelectSingleListener.triggerFrom( component ) as MouseListener ).click().atSomeWhere()
        then:
            thrown( IllegalContainerException )
    }

    def "throws exception when clicked if no parent exist"(){
        given:
            def component = Mock( JokerComponent )
        when:
            Gionic.control( SelectSingleListener.triggerFrom( component ) as MouseListener ).click().atSomeWhere()
        then:
            thrown( IllegalContainerException )
    }

    def "not thrown exception even wrong container before event trigger"(){
        given:
            def component = Mock ( JokerComponent )
        when:
            SelectSingleListener.triggerFrom( component )
        then:
            notThrown( IllegalContainerException )
    }
}
