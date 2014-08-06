package org.joker.component

import org.joker.component.event.StatusEvent
import org.joker.component.listener.abstracts.StatusChangedListener
import spock.lang.Specification

class JokerComponentTest extends Specification {

    JokerComponent component
    StatusChangedListener listener

    def setup(){
        component = new JokerComponent()
        listener  = Mock( StatusChangedListener )
        component.addStatusChangedListener( listener )
    }

    def "the value of setDraggable() can be get from isDraggable()"(){
        when:
            component.setDraggable( flag )
        then:
            component.isDraggable() == flag
        where:
            flag << [ true, false ]
    }

    def "setDraggable() will trigger added status listeners"(){
        when:
            component.setDraggable( true )
        then:
            1 * listener.draggableChanged( _ as StatusEvent )
    }

    def "the value of setResizable() can be get from isResizable()"(){
        when:
            component.setResizable( flag )
        then:
            component.isResizable() == flag
        where:
            flag << [ true, false ]
    }

    def "setResizable() will trigger added status listeners"(){
        when:
            component.setResizable( true )
        then:
            1 * listener.resizableChanged( _ as StatusEvent )
    }

    def "the value of setSelectable() can be get from isSelectable()"(){
        when:
            component.setSelectable( flag )
        then:
            component.isSelectable() == flag
        where:
            flag << [ true, false ]
    }

    def "setSelectable() will trigger added status listeners"(){
        when:
            component.setSelectable( true )
        then:
            1 * listener.selectableChanged( _ as StatusEvent )
    }

    def "check the state of selecting or not by isSelected()"(){
        when:
            component.select()
        then:
            component.isSelected()
        when:
            component.unselect()
        then:
            !component.isSelected()
    }

    def "select() and unselect() will trigger added status listeners"(){
        when:
            component.select()
        then:
            1 * listener.selectedChanged( _ as StatusEvent )
    }

}
