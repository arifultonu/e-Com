package com.app.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    public long cartId;
    public Date cartAddDate;
    public String cartSessionId;
    public long cartUserId;
}
