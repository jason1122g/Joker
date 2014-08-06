package org.joker.container.group

import org.joker.component.JokerComponent
import org.joker.component.listener.abstracts.StatusChangedListener
import spock.lang.Specification

import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

class StatusObserveGroupTest extends Specification {

    private StatusObserveGroup statusObserveGroup
    private JokerComponent     component1
    private JokerComponent     component2

    def setup(){
        statusObserveGroup = new StatusObserveGroup()
        component1 = Spy( JokerComponent )
        component2 = Spy( JokerComponent )
    }

    def "use add() to add listener to target"(){
        when:
            statusObserveGroup.add( component1 )
        then:
            1 * component1.addStatusChangedListener( _ as StatusChangedListener )
    }

    def "add() will add correspond listeners of its current states"(){
        when:
            component1.setSelectable( true )
        and:
            statusObserveGroup.add( component1 )
        then:
            1 * component1.addMouseListener      ( _ as MouseListener )
            1 * component1.addMouseMotionListener( _ as MouseMotionListener )
    }

    def "add() over two times will just trigger once"(){
        when:
            statusObserveGroup.add( component1 )
            statusObserveGroup.add( component1 )
            statusObserveGroup.add( component1 )
        then:
            1 * component1.addStatusChangedListener( _ as StatusChangedListener )
    }

    def "use remove() to remove listener added from add()"(){
        when:
            statusObserveGroup.add( component1 )
        and:
            statusObserveGroup.remove( component1 )
        then:
            1 * component1.removeStatusChangedListener( _ as StatusChangedListener )
    }

    def "remove() will remove all added listeners"(){
        when:
            component1.setSelectable( true )
        and:
            statusObserveGroup.add   ( component1 )
            statusObserveGroup.remove( component1 )
        then:
            1 * component1.removeMouseListener      ( _ as MouseListener )
            1 * component1.removeMouseMotionListener( _ as MouseMotionListener )
    }

    def "use removeAll() to remove all listeners in this group"(){
        when:
            statusObserveGroup.add( component1 )
            statusObserveGroup.add( component2 )
        and:
            statusObserveGroup.removeAll()
        then:
            1 * component1.removeStatusChangedListener( _ as StatusChangedListener )
            1 * component2.removeStatusChangedListener( _ as StatusChangedListener )
    }

    def "use components() to get all added components"(){
        expect:
            statusObserveGroup.components().size() == 0
        when:
            statusObserveGroup.add( component1 )
            statusObserveGroup.add( component2 )
        then:
            statusObserveGroup.components().size() == 2
        when:
            statusObserveGroup.remove( component2 )
        then:
            statusObserveGroup.components().size() == 1
    }

}
