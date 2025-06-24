package com.firstclub.membership.service;

import com.firstclub.membership.data.dto.OrderRequest;
import com.firstclub.membership.data.dto.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {
   ResponseEntity<OrderResponse>  processOrder(OrderRequest request);
}