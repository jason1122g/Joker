package org.joker.container;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;
import org.joker.component.listener.SelectListener;
import org.joker.container.abstracts.SelectGroup;
import org.joker.container.abstracts.SelectObserver;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JokerLayer extends JokerObject implements SelectObserver {

    private SelectGroup selectGroup = new JokerGroup();

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

    public void add( JokerComponent component ){
        SelectListener selectListener = new SelectListener().triggerFrom( component );
        component.addMouseListener( selectListener );
        component.addMouseMotionListener( selectListener );
        this.add( (Component) component );
    }
}
