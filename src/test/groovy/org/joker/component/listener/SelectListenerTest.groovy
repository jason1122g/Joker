package org.joker.component.listener

import org.joker.component.JokerComponent
import org.joker.component.event.SelectEvent
import org.joker.container.JokerLayer
import org.joker.exceptions.IllegalContainerException
import robot.EventSimulator
import spock.lang.Specification

import javax.swing.*

class SelectListenerTest extends Specification {

    def "click on target will lead to notify its parent"(){
        given:
            def eventSource = null
            def component = new JokerComponent()
            def container = new JokerLayer(){
                @Override
                void notify(SelectEvent selectEvent) {
                    eventSource = selectEvent.getSource()
                }
            }
        and:
            container.add( component )
            component.addMouseListener( new SelectListener().triggerFrom( component ) )
        and:
            def simulator = new EventSimulator( component )
        when:
            simulator.click()
        then:
            eventSource == component
    }

    def "throws exception when parent is not correct"(){
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

    def "throws exception when no parent exist"(){
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
