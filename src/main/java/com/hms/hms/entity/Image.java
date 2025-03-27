package com.hms.hms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "image")
public  class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image_url", length = 1000)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}