package org.joker.component.event

import robot.EventSimulator
import spock.lang.Specification

import javax.swing.*
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class ClickThroughEventTest extends Specification {

    def "click/press/release event will dispatch to parent"(){
        given:
            def panel1 = new JPanel()
            def panel2 = new JPanel()
            def events = []
        and:
            panel2.addMouseListener(new ClickThroughEvent().through(panel2))
            panel1.add(panel2)
            panel1.addMouseListener(new MouseAdapter() {
                @Override
                void mouseClicked(MouseEvent e) {
                    events << e.getPoint()
                }

                @Override
                void mousePressed(MouseEvent e) {
                    events << e.getPoint()
                }

                @Override
                void mouseReleased(MouseEvent e) {
                    events << e.getPoint()
                }
            })
        and:
            def point = new Point(30,30)
            def simulater = new EventSimulator(panel2)
        when:
            simulater.click(point)
            simulater.mousePress(point)
            simulater.mouseRelease(point)
        then:
            events == [point,point,point]
    }
}
