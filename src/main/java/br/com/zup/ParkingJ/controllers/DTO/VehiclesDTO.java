package br.com.zup.ParkingJ.controllers.DTO;

import java.time.LocalDateTime;

public class VehiclesDTO {
    private String Plate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private static final float hourlyRate = 1.50f;

    public VehiclesDTO(String Plate, LocalDateTime entryTime, LocalDateTime exitTime) {

    }
    public static float getHourlyRate() {
        return hourlyRate;
    }
    public String getPlate() {
        return Plate;
    }
    public void setPlate(String Plate) {
        this.Plate = Plate;
    }
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
    public LocalDateTime getExitTime() {
        return exitTime;
    }
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}
