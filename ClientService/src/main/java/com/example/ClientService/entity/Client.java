package com.example.ClientService.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @SequenceGenerator(
            name = "client_id_gen",
            sequenceName = "client_id_gen",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "client_id_gen"
    )
    private long id;

    @Column(nullable = false)
    private String clientName;

    private String clientAddress;

    private LocalDate createdDate;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] clientLogo;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] clientType;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<ProductUser> productUsers = new HashSet<>();
    private List<ProductUser> productUsers;

//    public Client addProductUser(ProductUser productUser)
//    {
//        productUsers.add(productUser);
//        productUser.setClient(this);
//        return this;
//    }
//
//    public Client removeProductUser(ProductUser productUser)
//    {
//        productUsers.remove(productUser);
//        productUser.setClient(null);
//        return this;
//    }

}
