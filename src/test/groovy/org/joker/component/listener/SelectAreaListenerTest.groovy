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
    JokerComponent component1
    JokerComponent component2
    JComponent     component3
    Simulator      container
    SelectAreaListener selectAreaListener

    def setup(){  "add 3 components to container and use useGroup() and withContainer() to init"
        def containerPanel = new JPanel()
        selectGroup = Mock( SelectGroup )
        container   = Gionic.control( containerPanel )

        component1  = new JokerComponent()
        component2  = new JokerComponent()
        component3  = new JLabel()

        containerPanel.add( component1 )
        containerPanel.add( component2 )
        containerPanel.add( component3 )

        selectAreaListener = SelectAreaListener.useGroup( selectGroup ).withContainer( containerPanel )
        containerPanel.addMouseListener( selectAreaListener )
        containerPanel.addMouseMotionListener( selectAreaListener )
    }


    def "use select group to select only jokerComponents in drag range entirely"(){
        given:
            component1.setBounds( 10, 10, 30, 30 )
            component2.setBounds( 40, 40, 30, 30 )
            component3.setBounds( 70, 70, 30, 30 )
        when:
            container.drag().from( 0, 0 ).to( 120, 120 ).endHere()
        then:
            2 * selectGroup.select( _ as JokerComponent )
    }

    def "drag area must over the components or it will not be selected"(){
        given:
            component1.setBounds( 10, 10, 100, 100 )
        when:
            container.drag().from( 20, 20 ).to( 120, 120 ).endHere()
        then:
            0 * selectGroup.select( component1 )
    }

    def "drag with ctrl pressed will not unselect previous components"(){
        given:
            component1.setBounds( 10, 10, 30, 30 )
            component2.setBounds( 100, 100, 30, 30 )
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
            component1.setBounds( 30, 30, 30, 30 )
        when:
            container.drag().from( 10, 10   ).to( 100, 100 ).endHere()
            container.drag().from( 100, 10  ).to( 10, 100  ).endHere()
            container.drag().from( 10, 100  ).to( 100, 10  ).endHere()
            container.drag().from( 100, 100 ).to( 10, 10   ).endHere()
        then:
            4 * selectGroup.select( component1 )
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
