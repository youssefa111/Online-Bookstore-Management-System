package com.example.project.entity;

import com.example.core.listeners.LowercaseEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EntityListeners(LowercaseEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String category;

}
