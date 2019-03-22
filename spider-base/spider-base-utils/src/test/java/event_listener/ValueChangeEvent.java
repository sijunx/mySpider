package event_listener;

import java.util.EventObject;

public class ValueChangeEvent extends EventObject {

    private static final long serialVersionUID = 767352958358520268L;
    private int value;

    public ValueChangeEvent(Object source) {
        this(source, 0);
    }

    public ValueChangeEvent(Object source, int newValue) {
        super(source);
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}

