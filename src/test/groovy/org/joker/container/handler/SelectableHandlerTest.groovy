package org.joker.container.handler

import org.jason1122g.gionic.awt.simulator.Gionic
import org.joker.component.JokerComponent
import org.joker.container.JokerLayer
import spock.lang.Shared
import spock.lang.Specification

import java.awt.*
import java.awt.event.KeyEvent

class SelectableHandlerTest extends Specification {

    private @Shared SelectableHandler selectableHandler = Spy( SelectableHandler )
    private JokerComponent component1
    private JokerComponent component2
    private JokerLayer layer

    def setup() { "init 2 jokerComponents and add them to a layer, construct handler"
        initComponents()
        initHandler()
    }

    private void initComponents() {
        layer = Spy( JokerLayer )
        layer.add( ( component1 = Spy( JokerComponent ) ) ) //TODO USE MOCK
        layer.add( ( component2 = Spy( JokerComponent ) ) )
    }

    private void initHandler() {
        selectableHandler.construct( layer )
    }

    def cleanup() {
        selectableHandler.destruct( layer )
    }

    def "after mount(), component can be selected "() {
        given:
            selectableHandler.mount( component1 )
        when:
            Gionic.control( component1 ).click().atSomeWhere()
        then:
            1 * component1.select()
    }

    def "after unmount(), the selected component will be unselected"() {
        given:
            selectableHandler.mount( component1 )
        and:
            Gionic.control( component1 ).click().atSomeWhere()
        when:
            selectableHandler.unmount( component1 )
        then:
            1 * component1.unselect()
    }

    def "after mount(), click on another component will make the previous one unselected"() {
        given:
            selectableHandler.mount( component1 )
            selectableHandler.mount( component2 )
        when:
            Gionic.control( component1 ).click().atSomeWhere()
        and:
            Gionic.control( component2 ).click().atSomeWhere()
        then:
            1 * component1.unselect()
    }

    def "after mount(), click on them with ctrl can apply multi-select"() {
        given:
            selectableHandler.mount( component1 )
            selectableHandler.mount( component2 )
        when:
            Gionic.control( component1 ).click().atSomeWhere()
        and:
            def controller2 =  Gionic.control( component2 )
        and:
            controller2.keyPress().of( KeyEvent.VK_CONTROL )
            controller2.click().atSomeWhere()
        then:
            1 * component2.select()
            1 * component1.select()
            0 * component1.unselect()
    }

    def "after mount(), click with ctrl on selected component can unselect it"() {
        given:
            selectableHandler.mount( component1 )
        when:
            def controller =  Gionic.control( component1 )
        and:
            controller.click().atSomeWhere()
            controller.keyPress().of( KeyEvent.VK_CONTROL )
            controller.click().atSomeWhere()
        then:
            1 * component1.select()
            1 * component1.unselect()
    }

    def "click on layer will unselect all selected components"() {
        given:
            selectableHandler.mount( component1 )
        when:
            Gionic.control( component1 ).click().atSomeWhere()
        and:
            Gionic.control( layer ).click().atSomeWhere()
        then:
            1 * component1.unselect()
    }

    def "after mount(), use drag to select components in that area"() {
        given:
            component1.setBounds( 10, 10, 50, 50 )
        and:
            selectableHandler.mount( component1 )
        when:
            Gionic.control( layer ).drag().from( 0, 0 ).to( 100, 100 ).endHere()
        then:
            1 * component1.select()
    }

    def "after mount(), drag with ctrl can apply multi-area-select"() {
        given:
            component1.setBounds( 10, 10, 50, 50 )
            component2.setBounds( 100, 100, 50, 50 )
        and:
            selectableHandler.mount( component1 )
            selectableHandler.mount( component2 )
        when:
            def layerSimulator =  Gionic.control( layer )
            layerSimulator.drag().from( 0, 0 ).to( 100, 100 ).endHere()
            layerSimulator.keyPress().of( KeyEvent.VK_CONTROL )
            layerSimulator.drag().from( 90, 90 ).to( 200, 200 ).endHere()
        then:
            0 * component1.unselect()
            1 * component1.select()
            1 * component2.select()
    }

    def "use selectedComponents() to get all selected components"() {
        given:
            selectableHandler.mount( component1 )
        when:
            Gionic.control( component1 ).click().atSomeWhere()
        then:
            selectableHandler.selectedComponents().size() == 1
    }

    def "use isSelectingArea() to check state, use getSelectingArea() to get area if selecting"() {
        given:
            component1.setBounds( 10, 10, 50, 50 )
        and:
            selectableHandler.mount( component1 )
        when:
            Gionic.control( layer ).drag().from( 0, 0 ).to( 100, 100 )
        then:
            selectableHandler.isSelectingArea()
            selectableHandler.getSelectingArea() == new Rectangle( 0, 0, 100, 100 )
    }

    def "use components() to get all added components"() {
        expect:
            selectableHandler.components().size() == 0
        when:
            selectableHandler.mount( component1 )
            selectableHandler.mount( component2 )
        then:
            selectableHandler.components().size() == 2
        when:
            selectableHandler.unmount( component2 )
        then:
            selectableHandler.components().size() == 1
    }

}
