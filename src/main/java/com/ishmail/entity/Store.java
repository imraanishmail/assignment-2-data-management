package com.ishmail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data // generates getters and setters and toString method
@AllArgsConstructor // generates a parameterized constructor for all fields in the class
@NoArgsConstructor // generates a default constructor
@Entity // specifies that this class is a table
public class Store implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    private String location;

    @OneToMany // specifies a one-to-many relationship (each store contains multiple inventory items)
    @JoinColumn(name = "id_inventory") // foreign key
    private List<Inventory> inventoryList;
}
