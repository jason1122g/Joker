package org.joker.container

import org.jason1122g.gionic.awt.simulator.Gionic
import org.jason1122g.gionic.core.Simulator
import org.joker.component.JokerComponent
import org.joker.component.event.SelectEvent
import spock.lang.Specification

import java.awt.*
import java.awt.event.KeyEvent

class JokerLayerTest extends Specification { //TODO MULTI DRAG TEST

    JokerComponent component1
    JokerComponent component2
    JokerLayer layer

    Simulator layerSimulator
    Simulator component1Simulator
    Simulator component2Simulator

    def setup(){
        component1 = Spy( JokerComponent )
        component2 = Spy( JokerComponent )
        layer      = Spy( JokerLayer )

        layerSimulator      = Gionic.control( layer )
        component1Simulator = Gionic.control( component1 )
        component2Simulator = Gionic.control( component2 )

        layer.add( component1 )
        layer.add( component2 )
    }

    def "default layout manager is null"(){
        expect:
            layer.getLayout() == null
    }

    def "click on its selectable component will trigger notify()"(){
        given:
            component1.setSelectable( selectable )
        when:
            component1Simulator.click().atSomeWhere()
        then:
            times * layer.notify( _ as SelectEvent )

        where:
            selectable | times
            true       | 1
            false      | 0
    }

    def "click on another component will make the previous one unselected"(){
        given:
            component1.setSelectable( true )
            component2.setSelectable( true )
        when:
            component1Simulator.click().atSomeWhere()
        then:
            1 * component1.select()
        when:
            component2Simulator.click().atSomeWhere()
        then:
            1 * component1.unselect()
            1 * component2.select()
    }

    def "click on its components with ctrl can apply multi-select"(){
        given:
            component1.setSelectable( true )
            component2.setSelectable( true )
        when:
            component1Simulator.click().atSomeWhere()
            component2Simulator.keyPress().of( KeyEvent.VK_CONTROL )
            component2Simulator.click().atSomeWhere()
        then:
            1 * component1.select()
            1 * component2.select()
    }

    def "click with ctrl on selected component can unselect it"(){
        given:
            component1.setSelectable( true )
        when:
            component1Simulator.click().atSomeWhere()
            component1Simulator.keyPress().of( KeyEvent.VK_CONTROL )
            component1Simulator.click().atSomeWhere()
        then:
            1 * component1.select()
            1 * component1.unselect()
    }

    def "click on layer itself will unselect all selected components"(){
        given:
            component1.setSelectable( true )
            component2.setSelectable( true )
        when:
            component1Simulator.click().atSomeWhere()
            component2Simulator.click().atSomeWhere()
            layerSimulator.click().atSomeWhere()
        then:
            1 * component1.unselect()
            1 * component2.unselect()
    }

    def "use selectedComponentes() to get all selected components"(){
        given:
            component1.setSelectable( true )
        when:
            component1Simulator.click().atSomeWhere()
        then:
            layer.selectedComponents().contains( component1 )
    }

    def "use isSelectingArea() to check the state"(){
        when:
            layerSimulator.drag().from( 10, 10 ).to( 110, 110 )
        then:
            layer.isSelectingArea()
    }

    def "use getSelectingArea to get the area"(){
        when:
            layerSimulator.drag().from( 10, 10 ).to( 110, 110 )
        then:
            layer.getSelectingArea() == new Rectangle( 10, 10, 100, 100 )
    }

}
