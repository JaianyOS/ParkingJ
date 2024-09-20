package br.com.zup.ParkingJ.controllers;

import br.com.zup.ParkingJ.controllers.DTO.PlateDTO;
import br.com.zup.ParkingJ.controllers.DTO.VehiclesDTO;
import br.com.zup.ParkingJ.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public ResponseEntity<List<VehiclesDTO>> showAllParking() {
        return ResponseEntity.ok(parkingService.getAllVehicles());
    }

    @PostMapping("/entry")
    public ResponseEntity<?> registerVehicleEntry(@RequestBody PlateDTO PlateDTO) {
        try {
            parkingService.addVehicle(PlateDTO.getPlate());
            return ResponseEntity.status(201).body("Registered vehicle. ");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/exit")
    public ResponseEntity<?> registerVehicleExit(@RequestBody PlateDTO PlateDTO) {
        try {
            parkingService.addVehicle(PlateDTO.getPlate());
            return ResponseEntity.status(201).body("Registered vehicle. ");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeVehicle(@RequestBody PlateDTO PlateDTO) {
        try {
            parkingService.removeVehicle(PlateDTO.getPlate());

            return ResponseEntity.status(200).body("Removed Vehicle. ");
        } catch (IllegalStateException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/fee")
    public ResponseEntity<?> calculateFee(@RequestBody PlateDTO PlateDTO) {
        try {
            VehiclesDTO vehicle = parkingService.findVehicleByID(PlateDTO.getPlate())
                    .orElseThrow(() -> new IllegalStateException("Vehicle not found."));
            float fee = ParkingService.calculateParkingFee(vehicle);
            return ResponseEntity.ok("Parking fee: R$ " + fee);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
