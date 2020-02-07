package tiralabra.datastructure;

import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyHashMapTest {

    @Test
    public void canSetAndGetIntegerValue() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        map.put(2, 0);
        Assert.assertEquals(0, (int) map.get(2));
    }

    @Test
    public void canAddLargeAmountsOfItems() {
        Random r = new Random(1239087);
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        int[] reverse = new int[1000];
        for (int i = 0; i < reverse.length; i++) {
            int val = r.nextInt();
            map.put(val, i);
            reverse[i] = val;
        }
        for(int i = 0; i < reverse.length; i++){
            Assert.assertEquals(i, (int) map.get(reverse[i]));
        }
    }

    @Test
    public void canUpdateKey() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        map.put(2, 44);
        map.put(2, 66);
        Assert.assertEquals(66, (int) map.get(2));
    }

    @Test
    public void getOrDefaultReturnsDefaultWhenKeyDoesNotExist() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, i - 2);
        }
        Assert.assertEquals(2, (int) map.getOrDefault(1001, 2));
    }
    
    @Test
    public void getOrDefaultReturnsValueIfItExist() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, i - 2);
        }
        Assert.assertEquals(997, (int) map.getOrDefault(999, 2));
    }

    @Test
    public void canUseObjectsAsKey() {
        MyHashMap<MyHashMap, Integer> map = new MyHashMap<>();
        map.put(map, 44);
        Assert.assertEquals(44, (int) map.get(map));
    }

}
