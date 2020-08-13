package es.mariasoria.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long ownerid;
    private String firstname, lastname;

    // MEANS THAT THE OWNER CAN HAVE MANY CARS
    // The cascade attribute defines how cascading affects the entities.
    // ALL: means that if the owner is deleted the cars linked to that owner are deleted as well.
    // mappedBy="owner": tells us that the Car class has the owner field,
    // which is the foreign key for this relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonIgnore
    private List<Car> cars;

    // this is the version of many owners can have many cars
    // @ManyToMany(cascade = CascadeType.MERGE)
    // @JoinTable(name = "car_owner", joinColumns = { @JoinColumn(name = "ownerid") }, inverseJoinColumns = { @JoinColumn(name = "id") })
    // private Set<Car> carsmanymany = new HashSet<Car>(0);

    public Owner(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }
}


