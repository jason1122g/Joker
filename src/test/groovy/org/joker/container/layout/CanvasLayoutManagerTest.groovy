package org.joker.container.layout

import org.joker.container.JokerLayer
import spock.lang.Shared
import spock.lang.Specification

import javax.swing.*
import java.awt.*

class CanvasLayoutManagerTest extends Specification {

    @Shared LayoutManager layoutManager = new CanvasLayoutManager()

    def "getPrefferedSize from container"(){
        given:
            def container = new JPanel()
        expect:
            layoutManager.preferredLayoutSize( container ) == getPrefferedSize( container )
    }

    def "getMinimumSize from container is equals to PrefferedSize"(){
        given:
            def container = new JPanel()
        expect:
            layoutManager.minimumLayoutSize( container ) == layoutManager.preferredLayoutSize( container )
    }

    private static Dimension getPrefferedSize( Container container ){
        Insets    insets    = container.getInsets();
        Dimension dimension = new Dimension();
        dimension.width  = container.getWidth()  + insets.left + insets.right;
        dimension.height = container.getHeight() + insets.top  + insets.bottom;
        return dimension;
    }

    def "layoutContainer would only setBounds of JokerLayer to container size"(){
        given:
            def container = new JPanel()
        and:
            def layer = new JokerLayer()
            def label = new JLabel()
        when:
            container.add( layer )
            container.add( label )
            container.setSize( 300, 300 )
        and:
            layoutManager.layoutContainer( container )
        then:
            layer.getSize() == container.getSize()
            label.getSize() != container.getSize()
    }

    def"addLayoutComponent and removeLayoutComponent will do nothing"(){
        when:
            layoutManager.addLayoutComponent( null, null )
            layoutManager.removeLayoutComponent( null )
        then: "nothing happened"
            true
    }

}
