package com.app.orderservice.responseModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseModel {
    private String outCode ="";
    private String outMessage = "";
}
