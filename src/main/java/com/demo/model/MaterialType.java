package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="Tipo de Material")
public class MaterialType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer maxLoanDays;


}
