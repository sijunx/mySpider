package event_listener;



public class Main {

    public static void main(String[] args) {
        EventProducer producer = new EventProducer();
        producer.addListener(new ValueChangeListenerImpl());
        producer.setValue(2);
    }
}
