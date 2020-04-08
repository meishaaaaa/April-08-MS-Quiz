package Functions;

import Repository.ParkInfo;
import Repository.ParkingRepository;
import exception.ParkingLotFullException;

import java.util.Date;

public class ParkCar {

    public String park(String plateNumber) throws ParkingLotFullException{
        if (ParkingRepository.checkFulled()) {
            throw new ParkingLotFullException("停车场已经停满");
        }
        ParkInfo car = new ParkInfo();
        car.setPlateNumber(plateNumber);
        car.setParkingStartTime(new Date());

        ParkingRepository.parkCar(car);
        String stallNumber = ParkingRepository.getInfo(plateNumber).getStallNumber();

        String parkingLot = stallNumber.substring(0, 1);
        String stallId = stallNumber.substring(1);

        return parkingLot + "," + stallId + "," + plateNumber;
    }

}
