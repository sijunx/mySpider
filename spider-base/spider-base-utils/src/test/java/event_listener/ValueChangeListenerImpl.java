package event_listener;

public class ValueChangeListenerImpl implements ValueChangeListener {

    @Override
    public void performed(ValueChangeEvent e) {
        System.out.println("value changed, new value = " + e.getValue());
    }
}

