package com.example.ClientService.payload;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientDto {

    private long id;
    private String clientName;
    private String clientAddress;
    private LocalDate createdDate;
    private byte[] clientLogo;
    private byte[] clientType;

}
