package org.joker.container.handler;

import org.joker.component.JokerComponent;
import org.joker.component.event.SelectEvent;
import org.joker.component.listener.SelectAreaListener;
import org.joker.component.listener.SelectSingleListener;
import org.joker.container.JokerLayer;
import org.joker.container.abstracts.ComponentSelectHandler;
import org.joker.container.abstracts.SelectGroup;
import org.joker.container.abstracts.SelectObserver;
import org.joker.container.group.SelectedGroup;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SelectableHandler implements ComponentSelectHandler, SelectObserver {

    private Map<JokerComponent, SelectSingleListener> listenerMap;

    private SelectGroup selectGroup;
    private SelectAreaListener selectAreaListener;
    private MouseListener unselectListener;

    private JokerLayer layer;

    @Override
    public void notify( SelectEvent selectEvent ) {
        MouseEvent     mouseEvent      = selectEvent.getMouseEvent();
        JokerComponent componentSource = selectEvent.getSource();
        if( mouseEvent.isControlDown() ) {
            if( componentSource.isSelected() ) {
                selectGroup.unselect( componentSource );
            }else {
                selectGroup.select  ( componentSource );
            }
        }else {
            selectGroup.unselectAll();
            selectGroup.select( componentSource );
        }
        layer.repaint();
    }

    @Override
    public void construct( JokerLayer layer ) {

        this.layer = layer;
        listenerMap = new HashMap<>();
        selectGroup = new SelectedGroup();

        selectAreaListener = SelectAreaListener.useSelectGroup( selectGroup ).withContainer( layer );
        unselectListener   = new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent e ) {
                selectGroup.unselectAll();
            }
        };

        layer.addMouseListener( unselectListener );
        layer.addMouseListener( selectAreaListener );
        layer.addMouseMotionListener( selectAreaListener );

    }

    @Override
    public void destruct( JokerLayer layer ) {
        layer.removeMouseListener( unselectListener );
        layer.removeMouseListener( selectAreaListener );
        layer.removeMouseMotionListener( selectAreaListener );
        unmountAllJokerComponents();
    }

    private void unmountAllJokerComponents() {
        for( Map.Entry< JokerComponent, SelectSingleListener > entry :  new HashMap<>( listenerMap ).entrySet() ){
            unmount( entry.getKey() );
        }
    }

    @Override
    public void mount( JokerComponent component ) {
        if( !listenerMap.containsKey( component ) ){
            listenerMap.put( component, addAndGetListener( component ) );
        }
    }

    private SelectSingleListener addAndGetListener( JokerComponent component ) {
        SelectSingleListener selectSingleListener = SelectSingleListener.triggerFrom( component ).toObserver( this );
        component.addMouseListener( selectSingleListener );
        component.addMouseMotionListener( selectSingleListener );
        return selectSingleListener;
    }

    @Override
    public void unmount( JokerComponent component ) {
        if( listenerMap.containsKey( component ) ){
            removeListener      ( component );
            listenerMap.remove  ( component );
            selectGroup.unselect( component );
        }
    }

    private void removeListener( JokerComponent component ) {
        SelectSingleListener selectSingleListener = listenerMap.get( component );
        component.removeMouseListener      ( selectSingleListener );
        component.removeMouseMotionListener( selectSingleListener );
    }

    @Override
    public Set<JokerComponent> components() {
        return listenerMap.keySet();
    }

    @Override
    public Set<JokerComponent> selectedComponents() {
        return selectGroup.components();
    }

    @Override
    public boolean isSelectingArea() {
        return selectAreaListener.isSelecting();
    }

    @Override
    public Rectangle getSelectingArea() {
        return selectAreaListener.getSelectArea();
    }

}
