package org.joker.component.abstracts;


public interface Selectable {
    public void select();
    public void unselect();
    public void setSelectable( boolean isSelectable );
    public boolean isSelected();
    public boolean isSelectable();
}
