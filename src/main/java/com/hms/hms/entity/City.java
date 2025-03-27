package com.hms.hms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany(orphanRemoval = true)
//    private Property property ;

//    @OneToMany(orphanRemoval = true)
//    @JoinColumn(name = "city_id")
//    private Set<Property> properties = new LinkedHashSet<>();

//    @OneToMany(orphanRemoval = true)
//    private Set<Property> properties = new LinkedHashSet<>();


    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Property> properties = new ArrayList<>();


}
