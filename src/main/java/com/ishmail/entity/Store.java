package com.ishmail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
