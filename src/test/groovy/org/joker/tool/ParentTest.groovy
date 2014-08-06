package org.joker.tool

import org.joker.exceptions.IllegalContainerException
import spock.lang.Specification

import javax.swing.*
import java.awt.*

class ParentTest extends Specification {

    def "parent of xx as yy can get its parent and cast to specified type"(){
        given:
            def component = new JLabel()
            def container = new JPanel()
        and:
            container.add( component )
        when:
            def newType = Parent.of( component ).as( classType )
        then:
            newType instanceof JPanel

        where:
            classType << [ JComponent.class, Container.class, Component.class ]
    }

    def "if no parent then throws exception"(){
        given:
            def component = new JLabel()
        when:
            Parent.of( component ).as( JComponent )
        then:
            thrown( IllegalContainerException )
    }

    def "if no parent type mismatch"(){
        given:
            def component = new JLabel()
            def container = new JPanel()
        and:
            container.add( component )
        when:
            Parent.of( component ).as( JFrame )
        then:
            thrown( IllegalContainerException )
    }

}
