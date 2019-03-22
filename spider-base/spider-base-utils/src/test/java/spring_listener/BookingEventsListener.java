package spring_listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BookingEventsListener implements ApplicationListener<BookingCreatedEvent> {

    //listener实现
    public void onApplicationEvent(BookingCreatedEvent event) {
        //        log.debug("bookingId:" + event.getBooking().getId());
        System.out.println("bookingId:" + event.getBooking().getId());
    }
}