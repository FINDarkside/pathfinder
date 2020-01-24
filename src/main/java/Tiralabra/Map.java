package Tiralabra;

import java.util.BitSet;

public class Map {
    
    private int width, height;
    private BitSet map;
    
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new BitSet(width * height);
    }
    
    public boolean getBlock(int x, int y) {
        return map.get(x + y * width);
    }
    
    public void setBlock(int x, int y, boolean blocked) {
        map.set(x + y * width);
    }
}
