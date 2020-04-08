package Repository;

import java.util.Date;

public class ParkInfo {
    private String stallNumber;
    private String plateNumber;
    private Date parkingStartTime;

    public String getPlateNumber() {
        return plateNumber;
    }

    public Date getParkingStartTime() {
        return parkingStartTime;
    }


    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setParkingStartTime(Date parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public String getStallNumber() {
        return stallNumber;
    }

    public void setStallNumber(String stallNumber) {
        this.stallNumber = stallNumber;
    }
}
