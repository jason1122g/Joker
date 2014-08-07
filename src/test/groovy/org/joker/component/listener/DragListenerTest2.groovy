package org.joker.component.listener

import org.joker.component.JokerComponent
import spock.lang.Specification

class DragListenerTest2 extends Specification {

    def "injected draggable component can be checked if exists by contains()"(){
        given:
            def component = new JokerComponent()
            def dragEvent = new DragListener().dragWith( component )
        expect:
            dragEvent.contains( component )
    }

    def "use without() to remove the injected draggable component"(){
        given:
            def component = new JokerComponent()
            def dragEvent = new DragListener().dragWith( component ).dragWithout( component )
        expect:
            !dragEvent.contains( component )
    }

}
