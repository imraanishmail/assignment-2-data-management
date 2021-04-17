package com.ishmail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data // generates getters and setters and toString method
@AllArgsConstructor // generates a parameterized constructor for all fields in the class
@NoArgsConstructor // generates a default constructor
@Entity // specifies that this class is a table
/*
 * done via JPQL (Java Persistence Query Language).
 * JPQL --> Java Persistence Query Language. Similar to SQL, but it is more Java Object Oriented.
 */
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i from Inventory i WHERE i.name = :name")
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
// Serializable converts objects into bytes (Marker interface and will passivate bean)
public class Inventory implements Comparable<Inventory>, Serializable {

    @Id // specifies that this is the primary key
    @GeneratedValue // will autogenerate & autoincrement the id
    private Long id;
    @Column(unique = true) // specifies that name of product cannot be the same
    private String name;
    private String sport;
    private int quantity;
    private double unitPrice;
    private Date dateUpdated;



    @PrePersist // entity listener method, sets generated fields
    void createdAt() {
        this.dateUpdated = new Date();
    }

    @PreUpdate // entity listener method, sets generated fields
    void updatedAt() {
        this.dateUpdated = new Date();
    }

    @Override
    public int compareTo(Inventory o) {
        return dateUpdated.compareTo(o.dateUpdated);
    }
}
