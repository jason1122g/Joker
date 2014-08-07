package org.joker.container

import org.joker.component.JokerComponent
import org.joker.component.event.SelectEvent
import robot.EventSimulator
import spock.lang.Specification

import java.awt.event.KeyEvent

class JokerLayerTest extends Specification { //TODO MULTI DRAG TEST

    JokerComponent component1
    JokerComponent component2
    JokerLayer layer

    EventSimulator layerSimulator
    EventSimulator component1Simulator
    EventSimulator component2Simulator

    def setup(){
        component1 = Spy( JokerComponent )
        component2 = Spy( JokerComponent )
        layer      = Spy( JokerLayer )

        layerSimulator      = new EventSimulator( layer )
        component1Simulator = new EventSimulator( component1 )
        component2Simulator = new EventSimulator( component2 )

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
            component1Simulator.click()
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
            component1Simulator.click()
        then:
            1 * component1.select()
        when:
            component2Simulator.click()
        then:
            1 * component1.unselect()
            1 * component2.select()
    }

    def "click on its components with ctrl can apply multi-select"(){
        given:
            component1.setSelectable( true )
            component2.setSelectable( true )
        when:
            component1Simulator.click()
            component2Simulator.pressKey( KeyEvent.VK_CONTROL )
            component2Simulator.click()
        then:
            1 * component1.select()
            1 * component2.select()
    }

    def "click with ctrl on selected component can unselect it"(){
        given:
            component1.setSelectable( true )
        when:
            component1Simulator.click()
            component1Simulator.pressKey( KeyEvent.VK_CONTROL )
            component1Simulator.click()
        then:
            1 * component1.select()
            1 * component1.unselect()
    }

    def "click on layer itself will unselect all selected components"(){
        given:
            component1.setSelectable( true )
            component2.setSelectable( true )
        when:
            component1Simulator.click()
            component2Simulator.click()
            layerSimulator.click()
        then:
            1 * component1.unselect()
            1 * component2.unselect()
    }

}
