package org.joker.container

import org.joker.component.JokerComponent
import robot.EventSimulator
import spock.lang.Specification

class JokerLayerTest extends Specification {

    def "default layout manager is null"(){
        expect:
            new JokerLayer().getLayout() == null
    }

    def "click on its component will trigger the notify method"(){
        given:
            def component1 = new  JokerComponent()
            def component2 = new  JokerComponent()
            def layer = Spy( JokerLayer )
        and:
            layer.add( component1 )
            layer.add( component2 )
        and:
            def component1Simulator = new EventSimulator( component1 )
            def component2Simulator = new EventSimulator( component2 )
        when:
            component1Simulator.click()
            component2Simulator.click()
        then:
            2 * layer.notify( _ as JokerComponent )
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
