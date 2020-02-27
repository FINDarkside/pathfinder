package tiralabra.benchmark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.datastructure.MyArrayDeque;

public class ScenarioLoader {

    /**
     * Loads scenarios from given path.
     *
     * @param path
     * @return List of scenarios.
     * @throws IOException
     */
    static MyArrayDeque<Scenario> loadScenarios(Path path, Path mapFolder) throws IOException {
        var lines = Files.readAllLines(path);
        return constructScenarios(lines, mapFolder);
    }

    /**
     * Constructs map from list of strings second line should be in format
     * "height [Integer]" third line should be in format "width [Integer]" map
     * should start from fifth line, "." represents free cell and all other
     * characters are interpreted as blocked cell.
     *
     * @param lines
     * @param mapFolder
     * @return
     * @throws java.io.IOException
     */
    public static MyArrayDeque<Scenario> constructScenarios(List<String> lines, Path mapFolder) throws IOException {
        MyArrayDeque<Scenario> scenarios = new MyArrayDeque<>();
        Map map = null;
        String prevMapPath = "";

        for (String s : lines) {
            String[] splitted = s.split("\t");
            if (splitted.length != 9) {
                continue;
            }
            String mapPath = splitted[1];
            if (!mapPath.equals(prevMapPath)) {
                map = MapLoader.loadMap(Paths.get(mapFolder.toString(), mapPath));
                prevMapPath = mapPath;
            }
            Cell start = new Cell(Integer.parseInt(splitted[4]), Integer.parseInt(splitted[5]));
            Cell goal = new Cell(Integer.parseInt(splitted[6]), Integer.parseInt(splitted[7]));
            scenarios.add(new Scenario(map, start, goal));
        }

        return scenarios;
    }
}
