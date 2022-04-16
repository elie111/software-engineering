package com.example;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="garages")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String phonenum;
    @ManyToMany
//    @JoinTable
    private List<Owner> owners;
    @ManyToMany
//    @JoinTable
    private List<Car> cars;

    public Garage( String address, String phonenum) {
        super();


        this.address = address;
        this.phonenum = phonenum;
        this.owners = new ArrayList<Owner>();
        this.cars = new ArrayList<Car>();
    }
public void addCar(Car car){
        this.cars.add(car);
}
    public void addOwner(Owner owner){
        this.owners.add(owner);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
