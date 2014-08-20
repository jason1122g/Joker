package org.joker.container;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;
import org.joker.component.listener.SelectRangeListener;
import org.joker.container.abstracts.SelectGroup;
import org.joker.container.abstracts.SelectObserver;
import org.joker.container.abstracts.StatusGroup;
import org.joker.container.group.SelectedGroup;
import org.joker.container.group.StatusObserveGroup;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class JokerLayer extends JokerObject implements SelectObserver {

    private SelectGroup selectGroup        = new SelectedGroup();
    private StatusGroup statusObserveGroup = new StatusObserveGroup();

    public JokerLayer(){
        this.setLayout( null );
        this.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent e ) {
                selectGroup.unselectAll();
            }
        }  );
        this.addMouseListener( SelectRangeListener.useGroup( selectGroup ).withContainer( this ) );
    }

    @Override
    public void notify( SelectEvent selectEvent ) {
        MouseEvent     mouseEvent      = selectEvent.getMouseEvent();
        JokerComponent componentSource = selectEvent.getSource();
        if( mouseEvent.isControlDown() ){
            if( componentSource.isSelected() ){
                selectGroup.unselect( componentSource );
            }else{
                selectGroup.select  ( componentSource );
            }
        }else{
            selectGroup.unselectAll();
            selectGroup.select( componentSource );
        }
        repaint();
    }

    @Override
    public Component add( Component component ){
        if( component instanceof JokerComponent ){
            statusObserveGroup.add( (JokerComponent) component );
        }
        return super.add( component );
    }

    @Override
    public void remove( Component component ){
        if( component instanceof JokerComponent ){
            JokerComponent jokerComponent = (JokerComponent) component;
            selectGroup.unselect     ( jokerComponent );
            statusObserveGroup.remove( jokerComponent );
        }
        super.remove( component );
    }

    @Override
    public Set<JokerComponent> selectedComponents() {
        return selectGroup.components();
    }

}
  
