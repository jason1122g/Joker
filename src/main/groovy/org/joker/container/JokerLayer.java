package org.joker.container;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;
import org.joker.container.abstracts.SelectGroup;
import org.joker.container.abstracts.SelectObserver;
import org.joker.container.abstracts.StatusGroup;
import org.joker.container.group.EventGroup;
import org.joker.container.group.JokerSelectGroup;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JokerLayer extends JokerObject implements SelectObserver {

    private SelectGroup selectGroup = new JokerSelectGroup();
    private StatusGroup eventGroup  = new EventGroup();

    public JokerLayer(){
        this.setLayout( null );
        this.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent e ) {
                selectGroup.unselectAll();
            }
        });
    }

    @Override
    public void notify( SelectEvent selectEvent ) {
        MouseEvent mouseEvent = selectEvent.getMouseEvent();
        if( mouseEvent.isControlDown() ){
            selectGroup.select( selectEvent.getSource() );
        }else{
            selectGroup.unselectAll();
            selectGroup.select( selectEvent.getSource() );
        }
    }

    @Override
    public Component add( Component component ){
        if( component instanceof JokerComponent ){
            eventGroup.add( (JokerComponent) component );
        }
        return super.add( component );
    }

    @Override
    public void remove( Component component ){
        if( component instanceof JokerComponent ){
            JokerComponent jokerComponent = (JokerComponent) component;
            selectGroup.unselect( jokerComponent );
            eventGroup.remove( jokerComponent );
        }
        super.remove( component );
    }

}
  
