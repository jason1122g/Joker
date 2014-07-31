package robot.handler;

import org.fest.swing.core.MouseButton;
import robot.FestRobot;
import robot.abstracts.Path;

import java.awt.*;

public class DragPath implements Path {

    private Point from;
    private FestRobot robot;

    public DragPath( FestRobot robot ){
        this.robot = robot;
    }

    @Override
    public Path from( Point from ) {
        this.from = from;
        return this;
    }

    @Override
    public Path to( Point to ) {
        robot.pressMouse( from, MouseButton.LEFT_BUTTON );
        robot.moveMouse( to );
        robot.releaseMouse( MouseButton.LEFT_BUTTON );
        return this;
    }
}
