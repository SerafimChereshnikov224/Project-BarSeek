package com.example.barseek_bar_mservice.model.entity;


import com.example.barseek_bar_mservice.model.types.BarType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bars")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "creation_date")
    private LocalDateTime createdAt;

    @Column(name = "last_update")
    private LocalDateTime updatedAt;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BarType type;

    @OneToMany(mappedBy = "bar",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Drink> drinks;

    @OneToOne
    @Column(name = "avatar")
    private Avatar avatar;
}
