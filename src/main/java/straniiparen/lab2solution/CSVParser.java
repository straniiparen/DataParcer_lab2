package straniiparen.lab2solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements FileParser {

    @Override
    public List<Address> parse(File file) throws Exception {
        List<Address> addresses = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        boolean isFirstLine = true;

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String[] parts = line.split(";");
            String city = parts[0].replaceAll("^\"|\"$", "");
            String street = parts[1].replaceAll("^\"|\"$", "");
            int house = Integer.parseInt(parts[2].replaceAll("^\"|\"$", ""));
            int floor = Integer.parseInt(parts[3].replaceAll("^\"|\"$", ""));
            addresses.add(new Address(city, street, house, floor));
        }
        br.close();
        return addresses;
    }
}