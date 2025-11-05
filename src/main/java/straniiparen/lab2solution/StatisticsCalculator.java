package straniiparen.lab2solution;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class StatisticsCalculator {

    public void printDuplicates(List<Address> addresses) {
        Map<Address, Integer> frequencyMap = new HashMap<>();
        for (Address addr : addresses) {
            frequencyMap.put(addr, frequencyMap.getOrDefault(addr, 0) + 1);
        }

        System.out.println("Дублирующиеся записи:");
        boolean hasDuplicates = false;
        for (Map.Entry<Address, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.printf("%s - повторений: %d%n", entry.getKey(), entry.getValue());
                hasDuplicates = true;
            }
        }
        if (!hasDuplicates) {
            System.out.println("Дубликатов не найдено.");
        }
    }

    public void printFloorStats(List<Address> addresses) {
        Map<String, int[]> cityFloors = new HashMap<>();
        for (Address addr : addresses) {
            cityFloors.putIfAbsent(addr.getCity(), new int[5]);
            int[] floors = cityFloors.get(addr.getCity());
            if (addr.getFloor() >= 1 && addr.getFloor() <= 5) {
                floors[addr.getFloor() - 1]++;
            }
        }

        System.out.println("Статистика по этажам:");
        for (Map.Entry<String, int[]> entry : cityFloors.entrySet()) {
            System.out.printf("Город: %s%n", entry.getKey());
            int[] floors = entry.getValue();
            for (int i = 0; i < floors.length; i++) {
                System.out.printf("  %d-этажных: %d%n", i + 1, floors[i]);
            }
        }
    }
}