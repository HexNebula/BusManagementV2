package com.microservices.busapp.dto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArretDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}
