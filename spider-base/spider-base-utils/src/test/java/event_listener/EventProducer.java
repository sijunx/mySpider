package event_listener;

public class EventProducer {

    ListenerRegister register = new ListenerRegister();

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        if (value != newValue) {
            value = newValue;
            ValueChangeEvent event = new ValueChangeEvent(this, value);
            register.fireAEvent(event);
        }
    }

    public void addListener(ValueChangeListener a) {
        register.addListener(a);
    }
}
