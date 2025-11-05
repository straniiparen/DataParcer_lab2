package straniiparen.lab2solution;

import java.io.File;
import java.util.List;

public interface FileParser {
    List<Address> parse(File file) throws Exception;
}