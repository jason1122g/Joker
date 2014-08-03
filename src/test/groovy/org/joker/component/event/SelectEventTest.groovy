package org.joker.component.event

import org.joker.component.JokerComponent
import spock.lang.Specification

import java.awt.event.MouseEvent

class SelectEventTest extends Specification {

    def "test get/set"(){
        given:
            def component   = new JokerComponent()
            def mouseEvent  = new MouseEvent(component,0,0,0,0,0,0,false)
        when:
            def selectEvent = new SelectEvent(component,mouseEvent)
        then:
            selectEvent.getSource()     == component
            selectEvent.getMouseEvent() == mouseEvent
    }
}
