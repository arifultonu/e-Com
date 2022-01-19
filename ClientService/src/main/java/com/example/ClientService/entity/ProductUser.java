package com.example.ClientService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUser {

    @Id
    @SequenceGenerator(
            name = "productUser_id_gen",
            sequenceName = "productUser_id_gen",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productUser_id_gen"
    )
    private long id;

    private long clientId;

    private LocalDate createdDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "clientId", nullable = false)
//    private Client client;
}
