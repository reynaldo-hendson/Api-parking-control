package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dto.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;
import com.api.parkingcontrol.services.ParkingSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2) em uma
 * interface simples e coesa (API REST).
 * {@link CrossOrigin} Resource Sharing (CORS) é um mecanismo baseado em cabeçalho HTTP
 * que permite a um servidor indicar qualquer origem (domínio, esquema ou porta) diferente da sua própria,
 * a partir da qual um navegador deve permitir o carregamento de recursos.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
    final ParkingSpotService parkingSpotService;
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotController(ParkingSpotService parkingSpotService,
                                 ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotService = parkingSpotService;
        this.parkingSpotRepository = parkingSpotRepository;
    }
    /*
    @Valid - validates all attributes of the class DTO. */
    @PostMapping
    @Operation(summary = "Create a new Parking Spot", description = "Create a new Parking Spot and return the created Parking Spot data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parking Spot created successfully"),
            @ApiResponse(responseCode = "400", description = "One or more parameters are incorrect, check and try again."),
            @ApiResponse(responseCode = "409", description = "CONFLICT: Parking Spot data provided")
    })
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        // validation conditions
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.licensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: There is already a vehicle registered with this license plate.");
        }
        if(parkingSpotService.exitsByParkingSpotNumber(parkingSpotDto.parkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Parking spot is already in use! ");
        }
        if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.apartment(),parkingSpotDto.block())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Parking spot already registered for this apartment or block!");
        }

        var parkingSpotModel = new ParkingSpotModel();

        //Turns dto into model
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);

        /* Date will be assigned at the time of creation because it does not exist in the DTO.*/
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping
    public ResponseEntity <Page<ParkingSpotModel>> getAllParkingSpot(@PageableDefault(page=0,size=10,sort="id",direction= Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable (value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable (value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted successufully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable (value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var parkingSpotModel = parkingSpotModelOptional.get();
        parkingSpotModel.setParkingSpotNumber(parkingSpotDto.parkingSpotNumber());
        parkingSpotModel.setLicensePlateCar(parkingSpotDto.licensePlateCar());
        parkingSpotModel.setBrandCar(parkingSpotDto.brandCar());
        parkingSpotModel.setModelCar(parkingSpotDto.modelCar());
        parkingSpotModel.setColorCar(parkingSpotDto.colorCar());
        parkingSpotModel.setResponsibleName(parkingSpotDto.responsibleName());
        parkingSpotModel.setApartment(parkingSpotDto.apartment());
        parkingSpotModel.setBlock(parkingSpotDto.block());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }

}
