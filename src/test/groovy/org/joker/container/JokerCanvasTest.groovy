package org.joker.container

import com.google.inject.Guice
import com.google.inject.Injector
import guice.modules.TestModule
import spock.lang.Shared
import spock.lang.Specification

import javax.swing.*

class JokerCanvasTest extends Specification {

    @Shared Injector injector = Guice.createInjector(new TestModule())

    def "canvas can custom size by layoutManager or null"(){
        given:
            def container = injector.getInstance(JPanel.class)
            def canvas = new JokerCanvas()
        when:
            container.add(canvas)
        and:
            canvas.setBounds(0,0,container.getWidth(),container.getHeight())
        then:
            canvas.getWidth()  == container.getWidth()
            canvas.getHeight() == container.getHeight()
    }

    def "canvas can automatically set the size of added layer when draw"(){
        given:
            def container = injector.getInstance(JFrame.class)
            def canvas = new JokerCanvas()
            def layer  = new JokerLayer()
        when:
            container.add(canvas)
        and:
            canvas.setBounds(0,0,50,50)
            canvas.add(layer)
        and:
            container.setVisible(true)
        then:
            layer.getWidth()  == canvas.getWidth()
            layer.getHeight() == canvas.getHeight()
    }

}
