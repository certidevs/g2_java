package com.demo.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "favorites")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime createdAt =  LocalDateTime.now();

    @ManyToOne
    @ToString.Exclude
    private User user;

    @ToString.Exclude
    @ManyToOne
    private Product product;

}
