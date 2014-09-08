package org.joker.component.listener

import org.jason1122g.gionic.awt.simulator.Gionic
import org.jason1122g.gionic.core.Simulator
import org.joker.component.JokerComponent
import org.joker.container.abstracts.SelectGroup
import spock.lang.Specification

import javax.swing.*
import java.awt.*
import java.awt.event.KeyEvent

class SelectAreaListenerTest extends Specification {

    SelectGroup    selectGroup
    JokerComponent component_joker1
    JokerComponent component_joker2
    JComponent     component_others
    Simulator      container
    SelectAreaListener selectAreaListener

    def setup(){  "add 3 components to layer and use useSelectGroup() and withContainer() to init"

        selectGroup = Mock( SelectGroup )

        def containerPanel = new JPanel()
        initEventSimulator ( containerPanel )
        initChildComponents( containerPanel )
        initListeners      ( containerPanel )
    }

    private void initEventSimulator( JPanel panel ) {
        container   = Gionic.control( panel )
    }

    private void initChildComponents( JPanel panel ) {
        panel.add( ( component_joker1  = new JokerComponent() ) )
        panel.add( ( component_joker2  = new JokerComponent() ) )
        panel.add( ( component_others  = new JLabel() ) )
    }

    private void initListeners( JPanel panel ) {
        selectAreaListener = SelectAreaListener.useSelectGroup( selectGroup ).withContainer( panel )
        panel.addMouseListener( selectAreaListener )
        panel.addMouseMotionListener( selectAreaListener )
    }


    def "use select group to select only jokerComponents in drag range entirely"(){
        given:
            component_joker1.setBounds( 10, 10, 30, 30 )
            component_joker2.setBounds( 40, 40, 30, 30 )
            component_others.setBounds( 70, 70, 30, 30 )
        when:
            container.drag().from( 0, 0 ).to( 120, 120 ).endHere()
        then:
            2 * selectGroup.select( _ as JokerComponent )
    }

    def "drag area must over the components or it will not be selected"(){
        given:
            component_joker1.setBounds( 10, 10, 100, 100 )
        when:
            container.drag().from( 20, 20 ).to( 120, 120 ).endHere()
        then:
            0 * selectGroup.select( component_joker1 )
    }

    def "drag with ctrl pressed will not unselect previous components"(){
        given:
            component_joker1.setBounds( 10, 10, 30, 30 )
            component_joker2.setBounds( 100, 100, 30, 30 )
        when:
            container.drag().from( 0, 0 ).to( 50, 50 ).endHere()
        and:
            container.keyPress().of( KeyEvent.VK_CONTROL )
            container.drag().from( 90, 90 ).to( 150, 150 ).endHere()
        then:
            0 * selectGroup.unselect( _ as JokerComponent )
    }

    def "four ways select are ok"(){
        given:
            component_joker1.setBounds( 30, 30, 30, 30 )
        when:
            container.drag().from( 10, 10   ).to( 100, 100 ).endHere()
            container.drag().from( 100, 10  ).to( 10, 100  ).endHere()
            container.drag().from( 10, 100  ).to( 100, 10  ).endHere()
            container.drag().from( 100, 100 ).to( 10, 10   ).endHere()
        then:
            4 * selectGroup.select( component_joker1 )
    }

    def "use isSelectingArea() to check the state"(){
        when:
            container.drag().from( 10, 10 ).to( 110, 110 )
        then:
            selectAreaListener.isSelecting()
    }

    def "use getSelectArea() get the seleting area"(){
        when:
            container.drag().from( 10, 10 ).to( 110, 110 )
        then:
            selectAreaListener.getSelectArea() == new Rectangle( 10, 10, 100, 100 )
    }

}
