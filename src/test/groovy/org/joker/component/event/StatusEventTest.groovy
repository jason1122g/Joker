package org.joker.component.event

import org.joker.component.JokerComponent
import spock.lang.Specification

class StatusEventTest extends Specification {

    def "set data only by constructor, get data by methods"(){
        given:
            def source = new JokerComponent()
        when:
            def event  = new StatusEvent( source, status, flag )
        then:
            event.getSource() == source
            event.getType  () == status
            event.getValue () == flag

        where:
            status                | flag
            StatusType.Draggable  | true
            StatusType.Resizable  | false
    }
}
