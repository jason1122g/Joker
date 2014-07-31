package org.joker.container.abstracts;

import org.joker.component.JokerComponent;

public interface SelectObserver {
    public void notify(JokerComponent selectedComponent);
}
