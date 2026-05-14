package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "categories")
public class Category {
    //Long id
    //String name
    //String imageUrl
    //String description
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @Column(length = 1000)
    private String description;
    private Boolean activo;
    // TODO private String imageUrl;
    @Column(length = 1000)
    private String image;
}
