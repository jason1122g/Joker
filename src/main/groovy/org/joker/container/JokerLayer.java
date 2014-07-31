package org.joker.container;


import org.joker.JokerObject;
import org.joker.component.JokerComponent;
import org.joker.container.abstracts.SelectGroup;
import org.joker.container.abstracts.SelectObserver;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TODO TEST AND FINISH THIS
 */
public class JokerLayer extends JokerObject implements SelectObserver{

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
    public void notify( JokerComponent selectedComponent ) {
        selectGroup.select( selectedComponent );
    }

}
