package com.api.parkingcontrol.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) ou simplesmente Transfer Object é um padrão de projetos
 * bastante usado em Java para o transporte de dados entre diferentes componentes de um sistema,
 * diferentes instâncias ou processos de um sistema distribuído.
 * */
@Getter
@Setter
public class ParkingSpotDto {
    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    @Size(max=7)
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;

}
