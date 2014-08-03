package org.joker.container

import org.joker.component.JokerComponent
import org.joker.component.event.SelectEvent
import robot.EventSimulator
import spock.lang.Specification

import java.awt.event.KeyEvent

class JokerLayerTest extends Specification {

    def "default layout manager is null"(){
        expect:
            new JokerLayer().getLayout() == null
    }

    def "click on its component will trigger the notify method"(){
        given:
            def component = new  JokerComponent()
            def layer = Spy( JokerLayer )
        and:
            layer.add( component )
        and:
            def component1Simulator = new EventSimulator( component )
        when:
            component1Simulator.click()
        then:
            1 * layer.notify( _ as SelectEvent )
    }

    def "click on new component will make the previous one unselect"(){
        given:
            def component1 = Spy( JokerComponent )
            def component2 = Spy( JokerComponent )
            def layer = new JokerLayer()
        and:
            component1.setSelectable(true)
            component2.setSelectable(true)
            layer.add( component1 )
            layer.add( component2 )
        and:
            def component1Simulator = new EventSimulator( component1 )
            def component2Simulator = new EventSimulator( component2 )
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
            def component1 = Spy( JokerComponent )
            def component2 = Spy( JokerComponent )
            def layer = new JokerLayer()
        and:
            component1.setSelectable(true)
            component2.setSelectable(true)
            layer.add( component1 )
            layer.add( component2 )
        and:
            def component1Simulator = new EventSimulator( component1 )
            def component2Simulator = new EventSimulator( component2 )
        when:
            component1Simulator.click()
            component2Simulator.pressKey( KeyEvent.VK_CONTROL )
            component2Simulator.click()
        then:
            1 * component1.select()
            1 * component2.select()
    }

    def "click on it will unselect all selected components"(){
        given:
            def component1 = Spy( JokerComponent )
            def component2 = Spy( JokerComponent )
            def layer = Spy( JokerLayer )
        and:
            component1.setSelectable( true )
            component2.setSelectable( true )
        and:
            layer.add( component1 )
            layer.add( component2 )
        and:
            def layerSimulator = new EventSimulator( layer )
            def component1Simulator = new EventSimulator( component1 )
            def component2Simulator = new EventSimulator( component2 )
        when:
            component1Simulator.click()
            component2Simulator.click()
            layerSimulator.click()
        then:
            1 * component1.unselect()
            1 * component2.unselect()
    }

}
