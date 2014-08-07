package org.joker.component.listener

import org.joker.component.JokerComponent
import org.joker.container.abstracts.SelectGroup
import robot.EventSimulator
import spock.lang.Specification

import javax.swing.*
import java.awt.*

class SelectRangeListenerTest extends Specification {

    SelectRangeListener rangeListener
    Container      container
    SelectGroup    selectGroup
    JokerComponent component1
    JokerComponent component2
    JComponent     component3
    EventSimulator containerSimulator

    def setup(){  "add 3 components to container and use useGroup() and withContainer() to init"
        selectGroup        = Mock( SelectGroup )
        container          = new JPanel()
        containerSimulator = new EventSimulator( container )

        component1  = new JokerComponent()
        component2  = new JokerComponent()
        component3  = new JLabel()

        container.add( component1 )
        container.add( component2 )
        container.add( component3 )

        rangeListener = SelectRangeListener.useGroup( selectGroup ).withContainer( container )
        container.addMouseListener( rangeListener )
    }


    def "use select group to select only jokerComponents in drag range entirely"(){
        given:
            component1.setBounds( 10, 10, 30, 30 )
            component2.setBounds( 40, 40, 30, 30 )
            component3.setBounds( 70, 70, 30, 30 )
        when:
            containerSimulator.drag().from( new Point(0,0) ).to( new Point(120,120) )
        then:
            2 * selectGroup.select( _ as JokerComponent )
    }

    def "drag range must over the components or it will not be selected"(){
        given:
            component1.setBounds( 10, 10, 100, 100 )
        when:
            containerSimulator.drag().from( new Point(20,20) ).to( new Point(120,120) )
        then:
            0 * selectGroup.select( component1 )
    }

    def "four ways select are ok"(){
        given:
            component1.setBounds( 30, 30, 30, 30 )
        when:
            containerSimulator.drag().from( new Point(10,10)   ).to( new Point(100,100) )
            containerSimulator.drag().from( new Point(100,10)  ).to( new Point(10,100)  )
            containerSimulator.drag().from( new Point(10,100)  ).to( new Point(100,10)  )
            containerSimulator.drag().from( new Point(100,100) ).to( new Point(10,10)   )
        then:
            4 * selectGroup.select( component1 )
    }



}
