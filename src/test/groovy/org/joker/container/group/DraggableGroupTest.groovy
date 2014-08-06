package org.joker.container.group

import org.joker.component.JokerComponent
import spock.lang.Specification

import java.awt.event.MouseMotionListener

class DraggableGroupTest extends Specification {

    private DraggableGroup dragGroup
    private JokerComponent      component1
    private JokerComponent      component2

    def setup(){
        dragGroup = new DraggableGroup()
        component1 = Mock( JokerComponent )
        component2 = Mock( JokerComponent )
    }

    def "use add() to add listener to target"(){
        when:
            dragGroup.add( component1 )
        then:
            1 * component1.addMouseMotionListener( _ as MouseMotionListener )
    }

    def "add() over two times will just trigger once"(){
        when:
            dragGroup.add( component1 )
            dragGroup.add( component1 )
            dragGroup.add( component1 )
        then:
            1 * component1.addMouseMotionListener( _ as MouseMotionListener )
    }

    def "use remove() to remove listener added from add()"(){
        when:
            dragGroup.add( component1 )
        and:
            dragGroup.remove( component1 )
        then:
            1 * component1.removeMouseMotionListener( _ as MouseMotionListener )
    }

    def "use removeAll() to remove all listeners in this group"(){
        when:
            dragGroup.add( component1 )
            dragGroup.add( component2 )
        and:
            dragGroup.removeAll()
        then:
            1 * component1.removeMouseMotionListener( _ as MouseMotionListener )
            1 * component2.removeMouseMotionListener( _ as MouseMotionListener )
    }

    def "use components() to get all added components"(){
        expect:
            dragGroup.components().size() == 0
        when:
            dragGroup.add( component1 )
            dragGroup.add( component2 )
        then:
            dragGroup.components().size() == 2
        when:
            dragGroup.remove( component2 )
        then:
            dragGroup.components().size() == 1
    }

}
