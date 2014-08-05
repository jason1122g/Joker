package robot.handler;

import robot.EventSimulator;
import robot.abstracts.Path;

import java.awt.*;

public class EventDragPath implements Path{
    private Point from;
    private EventSimulator simulator;

    public EventDragPath( EventSimulator simulator ){
        this.simulator = simulator;
    }

    @Override
    public Path from( Point from ) {
        this.from = from;
        return this;
    }

    @Override
    public Path to( Point to ) {
        simulator.mouseMove( from );
        simulator.mousePress( from );
        simulator.mouseDrag( to );
        simulator.mouseRelease( to );
        return this;
    }
}
