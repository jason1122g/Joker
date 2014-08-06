package org.joker.component.event

import org.joker.component.listener.abstracts.StatusChangedListener
import spock.lang.Specification

class StatusProviderTest extends Specification {

    StatusProvider statusProvider
    StatusChangedListener listener

    def setup(){
        statusProvider = new StatusProvider()
        listener       = Mock( StatusChangedListener )
    }

    def "use addStatusChangedListener() to add listener, use dispatchEvent() to trigger1"(){
        when:
            statusProvider.addStatusChangedListener( listener )
        and:
            statusProvider.dispatchEvent( new StatusEvent( null, StatusType.Draggable, true ) )
        then:
            1 * listener.draggableChanged( _ as StatusEvent )
    }

    def " resizableChanged() of listener will be triggered if correspond event is sent"(){
        when:
            statusProvider.addStatusChangedListener( listener )
        and:
            statusProvider.dispatchEvent( new StatusEvent( null, StatusType.Resizable, true ) )
        then:
            1 * listener.resizableChanged( _ as StatusEvent )
    }

    def "selectableChanged() of listener will be triggered if correspond event is sent"(){
        when:
            statusProvider.addStatusChangedListener( listener )
        and:
            statusProvider.dispatchEvent( new StatusEvent( null, StatusType.Selectable, true ) )
        then:
            1 * listener.selectableChanged( _ as StatusEvent )
    }

    def " selectedChanged() of listener will be triggered if correspond event is sent"(){
        when:
            statusProvider.addStatusChangedListener( listener )
        and:
            statusProvider.dispatchEvent( new StatusEvent( null, StatusType.Selected, true ) )
        then:
            1 * listener.selectedChanged( _ as StatusEvent )
    }

    def "use addStatusChangedListener() more than twice will only trigger once"(){
        when:
            statusProvider.addStatusChangedListener( listener )
            statusProvider.addStatusChangedListener( listener )
        and:
            statusProvider.dispatchEvent( new StatusEvent( null, StatusType.Resizable, true ) )
        then:
            1 * listener.resizableChanged( _ as StatusEvent )
    }

    def "use removeStatusChangedListener() to remove added listener"(){
        when:
            statusProvider.addStatusChangedListener( listener )
            statusProvider.removeStatusChangedListener( listener )
        and:
            statusProvider.dispatchEvent( new StatusEvent( null, StatusType.Resizable, true ) )
        then:
            0 * listener.resizableChanged( _ as StatusEvent )
    }

}
