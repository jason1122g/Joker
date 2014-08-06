package org.joker.container

import org.joker.component.JokerComponent
import org.joker.component.listener.abstracts.StatusChangedListener
import spock.lang.Specification

import javax.swing.*

class JokerLayerTest2 extends Specification {

    JokerLayer     layer
    JokerComponent component

    def setup(){
        layer     = new JokerLayer()
        component = Spy( JokerComponent )
    }

    def "add() will cause listener to be added if it is JokerComponent"(){
        when:
            layer.add( component )
        then:
            1 * component.addStatusChangedListener( _ as StatusChangedListener )
    }

    def "add() will only do regular add of Container if it is not JokerComponent"(){
        given:
            def otherComponent = new JLabel()
        when:
            layer.add( otherComponent )
        then:
            0 * component.addStatusChangedListener( _ as StatusChangedListener )
    }

    def "remove() will undo everything before add() and unselect the component if it's selected"(){
        when:
            component.select()
            layer.add   ( component )
            layer.remove( component )
        then:
            1 * component.unselect()
            1 * component.removeStatusChangedListener( _ as StatusChangedListener )
    }

}
