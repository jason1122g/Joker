package org.joker.container.handler

import org.joker.component.JokerComponent
import spock.lang.Specification

class BasicSelectHandlerTest extends Specification {

    private BasicSelectHandler statusObserveGroup
    private JokerComponent     component1
    private JokerComponent     component2

    def setup() {
        statusObserveGroup = new BasicSelectHandler()
        component1 = Spy( JokerComponent )
        component2 = Spy( JokerComponent )
    } //TODO FINISH THIS

    def "use construct() to init and mount components in layer, detect their state"() {

    }

    def "use destruct() to restore the state of layer and its components"() {

    }

}
