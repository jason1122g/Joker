package org.joker.component

import spock.lang.Shared
import spock.lang.Specification

class JokerComponentTest extends Specification {

    @Shared def component = new JokerComponent()

    def "the value of setDraggable() can be get from isDraggable()"(){
        when:
            component.setDraggable( flag )
        then:
            component.isDraggable() == flag
        where:
            flag << [ true, false ]
    }

    def "the value of setResizable() can be get from isResizable()"(){
        when:
            component.setResizable( flag )
        then:
            component.isResizable() == flag
        where:
            flag << [ true, false ]
    }

    def "the value of setSelectable() can be get from isSelectable()"(){
        when:
            component.setSelectable( flag )
        then:
            component.isSelectable() == flag
        where:
            flag << [ true, false ]
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

}
