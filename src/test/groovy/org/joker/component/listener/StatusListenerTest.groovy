package org.joker.component.listener

import org.joker.component.JokerComponent
import org.joker.component.event.StatusEvent
import org.joker.component.event.StatusType
import spock.lang.Specification

import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

class StatusListenerTest extends Specification {

    StatusListener listener
    JokerComponent component

    def setup(){
        listener  = new StatusListener()
        component = Mock( JokerComponent )
    }

    def "when trigger draggableChanged(), it will add/remove drag listener"(){
        given:
            def event1 = new StatusEvent( component, StatusType.Draggable, true  )
            def event2 = new StatusEvent( component, StatusType.Draggable, false )
        when:
            listener.draggableChanged( event1 )
        then:
            1 * component.addMouseMotionListener( _ as MouseMotionListener )
        when:
            listener.draggableChanged( event2 )
        then:
            1 * component.removeMouseMotionListener( _ as MouseMotionListener )
    }

    def "when trigger selectableChanged(), it will add/remove select listener"(){
        given:
            def event1 = new StatusEvent( component, StatusType.Selectable, true  )
            def event2 = new StatusEvent( component, StatusType.Selectable, false )
        when:
            listener.selectableChanged( event1 )
        then:
            1 * component.addMouseListener      ( _ as MouseListener )
            1 * component.addMouseMotionListener( _ as MouseMotionListener )
        when:
            listener.selectableChanged( event2 )
        then:
            1 * component.removeMouseListener      ( _ as MouseListener )
            1 * component.removeMouseMotionListener( _ as MouseMotionListener )
    }



}
