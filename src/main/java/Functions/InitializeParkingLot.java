package Functions;

import Repository.ParkingRepository;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InitializeParkingLot {
    static {
        ParkingRepository.clearHistory();
    }

    public void initial(String[] dataSet) {

        String lot1 = dataSet[0];
        int lot1Id = Integer.parseInt(dataSet[1]);
        String lot2 = dataSet[2];
        int lot2Id = Integer.parseInt(dataSet[3]);

        Stream<String> set1 = IntStream.rangeClosed(1, lot1Id).boxed().map(a -> lot1 + a.toString());
        Stream<String> set2 = IntStream.rangeClosed(1, lot2Id).boxed().map(a -> lot2 + a.toString());

        List<String> result=
                (getASCValue(lot1) < getASCValue(lot2))
                        ?Stream.concat(set1, set2).collect(Collectors.toList())
                        :Stream.concat(set2, set1).collect(Collectors.toList());


        ParkingRepository.initializePark(result);

    }


    private static int getASCValue(String lot) {
        char[] firstLetter = lot.toCharArray();
        return firstLetter[0];
    }
}



