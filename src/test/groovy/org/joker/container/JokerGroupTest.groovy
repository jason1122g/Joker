package org.joker.container

import org.joker.component.JokerComponent
import spock.lang.Specification

import java.awt.event.MouseMotionListener

class JokerGroupTest extends Specification {

    def "add can be triggered if selectable, remove can be triggered anytimes"(){
        given:
            def group      = new JokerGroup()
            def component1 = new JokerComponent()
            def component2 = new JokerComponent()
        and:
            component1.setSelectable( true )
            component2.setSelectable( true )
        when:
            group.select( component1 )
        then:
             group.components().contains( component1 )
            !group.components().contains( component2 )
        when:
            group.select( component2 )
        then:
            group.components().contains( component1 )
            group.components().contains( component2 )
        when:
            group.unselect( component1 )
            group.unselect( component2 )
        then:
            group.components().size() == 0
    }

    def "unselect all"(){
        given:
            def group      = new JokerGroup()
            def component1 = new JokerComponent()
            def component2 = new JokerComponent()
        and:
            component1.setSelectable( true )
            component2.setSelectable( true )
        when:
            group.select( component1 )
            group.select( component2 )
        and:
            group.unselectAll()
        then:
            group.components().size() == 0
    }

    def "add without selectable then do nothing"(){
        given:
            def group      = new JokerGroup()
            def component  = new JokerComponent()
        and:
            component.setSelectable( false )
        when:
            group.select( component )
        then:
            group.components().size() == 0
    }

    def "add will lead to select action and add motionListener"(){
        given:
            def group     = new JokerGroup()
            def component = Spy( JokerComponent )
        and:
            component.setSelectable( true )
        when:
            group.select( component )
        then:
            1 * component.select()
            1 * component.addMouseMotionListener( _ as MouseMotionListener )
    }

    def "remove will lead to unselect action and remove motionListener, can be call after select"(){
        given:
            def group     = new JokerGroup()
            def component = Spy( JokerComponent )
        and:
            component.setSelectable( true )
        when:
            group.select  ( component )
            group.unselect( component )
        then:
            1 * component.unselect()
            1 * component.removeMouseMotionListener( _ as MouseMotionListener )
    }

    def "remove directly will do nothing"(){
        given:
            def group     = new JokerGroup()
            def component = Mock( JokerComponent )
        when:
            group.unselect( component )
        then:
            0 * component.unselect()
            0 * component.removeMouseMotionListener( _ as MouseMotionListener )
    }
}
