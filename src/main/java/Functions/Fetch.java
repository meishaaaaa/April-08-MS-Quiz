package Functions;

import Repository.ParkInfo;
import Repository.ParkingRepository;
import exception.InvalidTicketException;

import java.util.Date;
import java.util.Objects;

public class Fetch {
    private Date fetchTime = new Date();

    public boolean fetch(String[] infoSet) throws InvalidTicketException {

        String stallNumber = infoSet[0] + infoSet[1];
        String plateNumber = infoSet[2];


        ParkInfo info = getInfo(plateNumber);

        if (Objects.equals(stallNumber, info.getStallNumber())) {
            ParkingRepository.refresh(plateNumber);
            return true;
        } else {
            throw new InvalidTicketException("停车券无效");
        }
    }

    private ParkInfo getInfo(String plateNumber) throws InvalidTicketException {
        ParkInfo parkInfo;
        try {
            parkInfo = ParkingRepository.getInfo(plateNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTicketException("停车券无效");
        }
        return parkInfo;


    }


}
