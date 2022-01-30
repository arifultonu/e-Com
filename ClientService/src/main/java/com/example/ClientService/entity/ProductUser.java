package com.example.ClientService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
            strategy = GenerationType.AUTO,
            generator = "productUser_id_gen"
    )
    private long id;

    private String prductUserName;

    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;



}
