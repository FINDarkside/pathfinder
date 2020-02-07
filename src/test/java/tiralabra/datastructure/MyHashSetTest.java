package tiralabra.datastructure;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class MyHashSetTest {

    @Test
    public void containsReturnsTrueWhenItemExists() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(2);
        Assert.assertEquals(true, set.contains(2));
    }

    @Test
    public void canAddLargeAmountsOfItems() {
        Random r = new Random(1239087);
        MyHashSet<Integer> set = new MyHashSet<>();
        int[] items = new int[1000];
        for (int i = 0; i < items.length; i++) {
            int val = r.nextInt();
            set.add(val);
            items[i] = val;
        }
        for (int i = 0; i < items.length; i++) {
            Assert.assertEquals(true, set.contains(items[i]));
        }
    }

    @Test
    public void canUseObjectsAsKey() {
        MyHashSet<MyHashSet> set = new MyHashSet<>();
        set.add(set);
        Assert.assertEquals(true, set.contains(set));
    }
    
    @Test
    public void containsReturnsFalseWhenItemDoesNotExist() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(2);
        Assert.assertEquals(false, set.contains(3));
    }
}
