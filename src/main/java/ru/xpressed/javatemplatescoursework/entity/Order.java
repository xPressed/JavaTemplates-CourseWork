package ru.xpressed.javatemplatescoursework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Loading point can not be empty!")
    private String load;

    @NotEmpty(message = "Destination point can not be empty!")
    private String destination;

    @NotEmpty(message = "Contact information can not be empty!")
    private String contact;

    private String info;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.y HH:mm");

    private String departure;

    private String arrival;

    private Date arrivalDate;

    private int btn;

    public void setDeparture(Date departure) {
        this.departure = dateFormat.format(departure);

        Calendar cal = Calendar.getInstance();
        cal.setTime(departure);
        cal.add(Calendar.MINUTE, 2);
        this.arrivalDate = cal.getTime();
        this.arrival = dateFormat.format(cal.getTime());
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Customer customer;
}
