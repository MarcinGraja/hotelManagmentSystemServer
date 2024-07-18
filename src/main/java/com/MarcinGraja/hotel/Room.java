package com.MarcinGraja.hotel;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer bedsCount;
    private String number;
    @OneToMany
    private final List<Guest> guests = new ArrayList<>();

    public void free(Guest guest){
        if (guests.contains(guest)){
            guests.remove(guest);
        } else {
            throw new RuntimeException("invalid guest");
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getBedsCount() {
        return bedsCount;
    }

    public String getNumber() {
        return number;
    }

    public List<Guest> getGuests() {
        return guests;
    }


    public void setBedsCount(Integer bedsCount) {
        this.bedsCount = bedsCount;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
