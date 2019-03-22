package spring_listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements ApplicationContextAware {

//    @Autowired
//    private ApplicationContext context;


    private ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }


    //事件源

    public void persistBooking(Booking booking) {
        System.out.println("插入数据库");
//        em.persist(booking);
//        log.debug("fire BookingCreatedEvent");
        BookingCreatedEvent bookingCreatedEvent = new BookingCreatedEvent(this);
        //触发event
        this.context.publishEvent(bookingCreatedEvent);
    }
}

