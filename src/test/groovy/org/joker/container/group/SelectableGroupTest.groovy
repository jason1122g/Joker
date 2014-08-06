package org.joker.container.group

import org.joker.component.JokerComponent
import spock.lang.Specification

import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener


class SelectableGroupTest extends Specification {

    private SelectableGroup selectableGroup
    private JokerComponent  component1
    private JokerComponent  component2

    def setup(){
        selectableGroup = new SelectableGroup()
        component1 = Mock( JokerComponent )
        component2 = Mock( JokerComponent )
    }

    def "use add() to add listener to target"(){
        when:
            selectableGroup.add( component1 )
        then:
            1 * component1.addMouseListener      ( _ as MouseListener )
            1 * component1.addMouseMotionListener( _ as MouseMotionListener )
    }

    def "add() over two times will just trigger once"(){
        when:
            selectableGroup.add( component1 )
            selectableGroup.add( component1 )
            selectableGroup.add( component1 )
        then:
            1 * component1.addMouseListener      ( _ as MouseListener )
            1 * component1.addMouseMotionListener( _ as MouseMotionListener )
    }

    def "use remove() to remove listener added from add()"(){
        when:
            selectableGroup.add( component1 )
        and:
            selectableGroup.remove( component1 )
        then:
            1 * component1.removeMouseListener      ( _ as MouseListener )
            1 * component1.removeMouseMotionListener( _ as MouseMotionListener )
    }

    def "use removeAll() to remove all listeners in this group"(){
        when:
            selectableGroup.add( component1 )
            selectableGroup.add( component2 )
        and:
            selectableGroup.removeAll()
        then:
            1 * component1.removeMouseListener      ( _ as MouseListener )
            1 * component1.removeMouseMotionListener( _ as MouseMotionListener )
            1 * component2.removeMouseListener      ( _ as MouseListener )
            1 * component2.removeMouseMotionListener( _ as MouseMotionListener )
    }

    def "use components() to get all added components"(){
        expect:
            selectableGroup.components().size() == 0
        when:
            selectableGroup.add( component1 )
            selectableGroup.add( component2 )
        then:
            selectableGroup.components().size() == 2
        when:
            selectableGroup.remove( component2 )
        then:
            selectableGroup.components().size() == 1
    }

}
