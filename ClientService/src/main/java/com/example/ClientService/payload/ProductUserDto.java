package com.example.ClientService.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductUserDto {

    private long id;
    private String productUserName;
    private LocalDate createdDate;

}
