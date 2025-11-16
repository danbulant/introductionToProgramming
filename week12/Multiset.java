package week12;

import java.util.HashMap;
import java.util.Map;

public class Multiset<E> {
    Map<E, Integer> data = new HashMap<>();

    public int count(E e) {
        var entry = data.get(e);
        return entry == null ? 0 : entry.intValue();
    }

    public void add(E e) {
        data.compute(e, (key, num) -> num == null ? 1 : num+1);
    }
    
    public void remove(E e) {
        var entry = data.get(e);
        if(entry == null || entry <= 1) data.remove(e);
        else data.put(e, entry - 1);
    }

    public int size() {
        return data.size();
    }
}
