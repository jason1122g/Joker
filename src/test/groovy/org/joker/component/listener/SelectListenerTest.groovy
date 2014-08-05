package org.joker.component.listener

import org.joker.component.JokerComponent
import org.joker.exceptions.IllegalContainerException
import robot.EventSimulator
import spock.lang.Specification

import javax.swing.*

class SelectListenerTest extends Specification {

    def "throws exception when clicked if component parent is not observer"(){
        given:
            def component = new JokerComponent()
            def container = new JPanel()
        and:
            container.add( component )
            component.addMouseListener( new SelectListener().triggerFrom( component ) )
        when:
            new EventSimulator( component ).click()
        then:
            thrown( IllegalContainerException )
    }

    def "throws exception when clicked if no parent exist"(){
        given:
            def component = new JokerComponent()
            component.addMouseListener( new SelectListener().triggerFrom( component ) )
        when:
            new EventSimulator( component ).click()
        then:
            thrown( IllegalContainerException )
    }

    def "not thrown exception even wrong container before event trigger"(){
        given:
            def component = new JokerComponent()
        when:
            component.addMouseListener( new SelectListener().triggerFrom( component ) )
        then:
            notThrown( IllegalContainerException )
    }
}
