package org.joker.component

import spock.lang.Specification

class JokerComponentTest extends Specification {

    def "get/set draggable 1"(){
        given:
            def component = new JokerComponent()
        when:
            component.setDraggable(true)
        then:
            component.isDraggable()
    }

    def "get/set draggable 2"(){
        given:
            def component = new JokerComponent()
        when:
            component.setDraggable(false)
        then:
            !component.isDraggable()
    }

    def "get/set resizable 1"(){
        given:
            def component = new JokerComponent()
        when:
            component.setResizable(true)
        then:
            component.isResizable()
    }

    def "get/set resizable 2"(){
        given:
            def component = new JokerComponent()
        when:
            component.setResizable(false)
        then:
            !component.isResizable()
    }

}
