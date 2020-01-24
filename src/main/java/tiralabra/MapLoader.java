package tiralabra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MapLoader {

    /**
     * Loads map from given path.
     *
     * @param path
     * @return Map
     * @throws IOException
     */
    static Map loadMap(Path path) throws IOException {
        var lines = Files.readAllLines(path);
        return constructMap(lines);
    }

    /**
     * Constructs map from list of strings second line should be in format
     * "height [Integer]" third line should be in format "width [Integer]" map
     * should start from fifth line, "." represents free cell and all other
     * characters are interpreted as blocked cell.
     *
     * @param lines
     * @return
     */
    public static Map constructMap(List<String> lines) {
        int height = Integer.parseInt(lines.get(1).split(" ")[1]);
        int width = Integer.parseInt(lines.get(2).split(" ")[1]);
        Map map = new Map(width, height);
        for (int i = 4; i < lines.size(); i++) {
            String line = lines.get(i);
            // First 4 rows 
            int y = i - 4;
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) != '.') {
                    map.setCell(x, y, true);
                }
            }
        }
        return map;
    }
}
