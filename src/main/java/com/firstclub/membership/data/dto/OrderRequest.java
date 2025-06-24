package com.firstclub.membership.data.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String email;
    private double originalAmount;
}
