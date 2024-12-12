package com.microservices.frontendservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private Boolean success;
    private int status;
    private Object data;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
