package com.example.elhmel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BeerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    @NotNull
    private String type;

    @OneToMany(mappedBy = "beerType")
    private List<Beer> beerList;

    @PreRemove
    public void preRemove() {
        beerList.forEach(beer -> beer.setBeerType(null));
    }
}
