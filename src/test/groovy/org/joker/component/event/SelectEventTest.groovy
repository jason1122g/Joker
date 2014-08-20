package org.joker.component.event

import org.joker.component.JokerComponent
import spock.lang.Specification

import java.awt.event.MouseEvent

class SelectEventTest extends Specification {

    def "set data only by constructor, get data by methods"(){
        given:
            def component   = Mock( JokerComponent )
            def mouseEvent  = Mock( MouseEvent )
        when:
            def event = new SelectEvent( component, mouseEvent )
        then:
            event.getSource()     == component
            event.getMouseEvent() == mouseEvent
    }
}
