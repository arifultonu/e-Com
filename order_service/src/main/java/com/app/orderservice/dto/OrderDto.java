package com.app.orderservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    public long orderId;
    public String orderUserId;
    public Date orderDate;
    public String orderProductId;
    public String orderStatus;
    public Date orderApprovalDate;
    public Date orderUpdateDate;
    public String orderApprovedStatus;
}
