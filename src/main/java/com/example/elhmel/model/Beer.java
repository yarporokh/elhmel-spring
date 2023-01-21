package com.example.elhmel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotNull
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="description_id")
    @NotNull
    private BeerDescription description;

    @Column
    @NotNull
    private Float alcohol;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "beertype_id")
    private BeerType beerType;
}




