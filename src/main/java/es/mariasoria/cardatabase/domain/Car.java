package es.mariasoria.cardatabase.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Car {

    @Id // identifies every car. Primary key
    @GeneratedValue(strategy= GenerationType.AUTO)
    // AUTO significa que el Id se generará automáticamente por la BD
    private long id;
    private String brand;
    private String model;
    private String color;
    private String registerNumber;
    private int year, price;

    // @ManyToOne means that many cars can be assigned to one owner
    // FetchType defines the strategy for fetching data from the database.
    // The value can be either EAGER or LAZY
    // LAZY  means that when the owner is fetched from the database,
    // all the cars associated with the owner will be fetched WHEN NEEDED.
    // EAGER means that the cars will be fetched immediately with the owner.
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="owner")
    private Owner owner;

    // this is the version of many cars can belong to many owners
    // @ManyToMany(mappedBy = "carsmanymany")
    // private Set<Owner> owners;

    public Car (){}

    public Car(String brand, String model, String color,
               String registerNumber, int year, int price){
        super();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
    }

    public Car(String brand, String model, String color,
               String registerNumber, int year, int price, Owner owner){
        super();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owner = owner;
    }
}
