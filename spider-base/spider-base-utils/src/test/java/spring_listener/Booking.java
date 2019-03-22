package spring_listener;

public class Booking {

    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                '}';
    }
}
