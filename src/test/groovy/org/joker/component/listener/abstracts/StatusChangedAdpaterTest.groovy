package org.joker.component.listener.abstracts

import spock.lang.Specification

class StatusChangedAdpaterTest extends Specification {

    def "this is just a convenient class of StatusChangedListener"(){
        given:
            def listener = new StatusChangedAdpater()
        when:
            listener.draggableChanged ( null )
            listener.resizableChanged ( null )
            listener.selectableChanged( null )
            listener.selectedChanged  ( null )
        then: "nothing happened"
            true
    }
}
