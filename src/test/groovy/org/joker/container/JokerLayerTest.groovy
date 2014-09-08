package org.joker.container

import org.joker.component.JokerComponent
import org.joker.container.abstracts.ComponentHandler
import spock.lang.Specification

class JokerLayerTest extends Specification {

    JokerLayer layer

    def setup(){
        layer = new JokerLayer()
    }

    def "default layout manager is null"() {
        expect:
            layer.getLayout() == null
    }

    def "setComponentHandler() will construct the handler"() {
        given:
            def handler = Mock( ComponentHandler )
        when:
            layer.setComponentHandler( handler )
        then:
            1 * handler.construct( layer )
    }

    def "handler of setComponentHandler() can be get from getComponentHandler()"() {
        given:
            def handler = Mock( ComponentHandler )
        when:
            layer.setComponentHandler( handler )
        then:
            layer.getComponentHandler() == handler
    }

    def "throw Exception when call setComponentHandler() with null"() {
        when:
            layer.setComponentHandler( null )
        then:
            thrown( IllegalArgumentException )
    }

    def "setComponentHandler() will destruct() previous handler"() {
        given:
            def previousHandler = Mock( ComponentHandler )
            def currentHandler  = Mock( ComponentHandler )
        when:
            layer.setComponentHandler( previousHandler )
        and:
            layer.setComponentHandler( currentHandler )
        then:
            1 * previousHandler.destruct( layer )
    }

    def "add( Component ) will mount component if instance of JokerComponnet and handler is not null"() {
        given:
            def handler   = Mock( ComponentHandler )
            def component = new JokerComponent()
        and:
            layer.setComponentHandler( handler )
        when:
            layer.add( component )
        then:
            1 * handler.mount( component )
    }

    def "remove( Component ) will unmount component if instance of JokerComponnet and handler is not null"() {
        given:
            def handler   = Mock( ComponentHandler )
            def component = Mock( JokerComponent )
        and:
            layer.setComponentHandler( handler )
        when:
            layer.remove( component )
        then:
            1 * handler.unmount( component )
    }

    def "components() will return the set of added JokerComponents"() {
        given:
            def component = new JokerComponent()
        and:
            layer.add( component )
        expect:
            layer.components().contains( component )
    }

}
