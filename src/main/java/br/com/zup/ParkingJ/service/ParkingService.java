package br.com.zup.ParkingJ.service;

import br.com.zup.ParkingJ.controllers.DTO.PlateDTO;
import br.com.zup.ParkingJ.controllers.DTO.VehiclesDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    private final List<VehiclesDTO> park = new ArrayList<>();

    public Optional<VehiclesDTO> findVehicleByID(int id) {
        return park.stream()
                .filter(vehicle -> vehicle.getPlate().equalsIgnoreCase(PlateDTO)).findFirst();
    }
    public VehiclesDTO addVehicle(String Plate) {
        Optional<VehiclesDTO> existingVehicle = findVehicleByID(Plate);
        if (existingVehicle.isPresent()) {
            throw new IllegalStateException("Already registered vehicle");
        }
        VehiclesDTO newVehicle = new VehiclesDTO();
        newVehicle.setPlate(Plate);
        newVehicle.setEntryTime(LocalDateTime.now());
        park.add(newVehicle);

        return newVehicle;
    }

    public VehiclesDTO registerExit(String Plate) {
        VehiclesDTO vehicles = findVehicleByID(Plate)
                .orElseThrow(() -> new IllegalStateException("Not found vehicle"));
        if (vehicles.getExitTime() != null){
            throw new IllegalStateException("Already registered vehicle");
        }
        vehicles.setExitTime(LocalDateTime.now());
        return vehicles;
    }

    public void removeVehicle(String Plate) {
        VehiclesDTO vehicles = findVehicleByID(Plate)
                .orElseThorow(() -> new IllegalStateException("Not exist vehicle"));
        park.remove(vehicles);
    }

    public static float calculateParkingFee(VehiclesDTO vehicle) {
        LocalDateTime entryTime = vehicle.getEntryTime();
        LocalDateTime exitTime = vehicle.getExitTime(); // Usa a hora de saída registrada

        if (exitTime == null) {
            throw new IllegalStateException("Exit time is not registered.");
        }

        Duration duration = Duration.between(entryTime, exitTime);
        long hoursParked = (duration.toMinutes() + 59) / 60;  // Arredonda para cima

        return hoursParked * VehiclesDTO.getHourlyRate();
    }


    public List<VehiclesDTO> getAllVehicles() {
        return park;
    }
}

