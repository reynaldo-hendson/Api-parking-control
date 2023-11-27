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
public record ParkingSpotDto(
        @NotBlank String parkingSpotNumber,
        @NotBlank
        @Size(max=7) String licensePlateCar,
        @NotBlank String brandCar,
        @NotBlank String modelCar,
        @NotBlank String colorCar,
        @NotBlank String responsibleName,
        @NotBlank String apartment,
        @NotBlank String block
) {
}
