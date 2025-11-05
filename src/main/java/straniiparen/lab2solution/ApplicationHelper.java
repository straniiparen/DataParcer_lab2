package straniiparen.lab2solution;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ApplicationHelper {
    private final Scanner scanner;
    private final StatisticsCalculator statsCalculator;

    public ApplicationHelper() {
        this.scanner = new Scanner(System.in);
        this.statsCalculator = new StatisticsCalculator();
    }

    public void run() {
        System.out.println("Приложение для анализа справочников городов");
        System.out.println("============================================");

        while (true) {
            System.out.println("\nВведите путь к файлу или 'exit' для выхода:");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            File file = new File(input);
            if (!file.exists()) {
                System.out.println("Файл не найден. Попробуйте снова.");
                continue;
            }

            processFile(file);
        }

        scanner.close();
        System.out.println("Работа приложения завершена.");
    }

    private void processFile(File file) {
        long startTime = System.currentTimeMillis();

        try {
            FileParser parser = getParser(file);
            List<Address> addresses = parser.parse(file);

            System.out.println("\n=== РЕЗУЛЬТАТЫ ОБРАБОТКИ ===");
            statsCalculator.printDuplicates(addresses);
            System.out.println();
            statsCalculator.printFloorStats(addresses);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка обработки файла: " + e.getMessage());
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("\nВремя обработки: %d мс.%n", endTime - startTime);
    }

    private FileParser getParser(File file) {
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".xml")) {
            return new XMLParser();
        } else if (fileName.endsWith(".csv")) {
            return new CSVParser();
        }
        throw new IllegalArgumentException("Неподдерживаемый формат файла: " + fileName);
    }
}