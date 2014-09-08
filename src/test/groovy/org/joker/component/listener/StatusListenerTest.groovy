package org.joker.component.listener

import org.joker.component.JokerComponent
import org.joker.component.event.StatusEvent
import org.joker.component.event.StatusType
import org.joker.container.abstracts.ComponentHandler
import spock.lang.Specification

class StatusListenerTest extends Specification {

    StatusListener listener
    JokerComponent component

    ComponentHandler draggableHandler
    ComponentHandler selectableHandler

    def setup(){

        component = Mock( JokerComponent )

        draggableHandler  = Mock( ComponentHandler )
        selectableHandler = Mock( ComponentHandler )
        listener  = new StatusListener( draggableHandler, selectableHandler )

    }

    def "when trigger draggableChanged() with true, it will add drag listener"(){
        given:
            def event = new StatusEvent( component, StatusType.Draggable, true  )
        when:
            listener.draggableChanged( event )
        then:
            1 * draggableHandler.mount( component )
    }

    def "when trigger draggableChanged() with false, it will remove drag listener"(){
        given:
            def event = new StatusEvent( component, StatusType.Draggable, false )
        when:
            listener.draggableChanged( event )
        then:
            1 * draggableHandler.unmount( component )
    }

    def "when trigger selectableChanged() with true, it will add select listener"(){
        given:
            def event = new StatusEvent( component, StatusType.Selectable, true  )
        when:
            listener.selectableChanged( event )
        then:
            1 * selectableHandler.mount( component )
    }

    def "when trigger selectableChanged() with false, it will remove select listener"(){
        given:
            def event = new StatusEvent( component, StatusType.Selectable, false )
        when:
            listener.selectableChanged( event )
        then:
            1 * selectableHandler.unmount( component )
    }


}
