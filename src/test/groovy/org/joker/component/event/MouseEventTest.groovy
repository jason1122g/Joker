package org.joker.component.event

import org.joker.component.JokerComponent
import spock.lang.Specification

import java.awt.event.MouseEvent

class MouseEventTest extends Specification {

    def "the params injected into event can be get by methods"(){
        given:
            def component   = new JokerComponent()
            def mouseEvent  = new MouseEvent( component, 0, 0, 0, 0, 0, 0, false )
        when:
            def event = new SelectEvent( component, mouseEvent )
        then:
            event.getSource()     == component
            event.getMouseEvent() == mouseEvent
    }
}
