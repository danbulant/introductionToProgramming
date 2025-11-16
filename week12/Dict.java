package week12;

import java.util.Arrays;

public class Dict<K extends Comparable<K>, V> {
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] data = new Pair[4];
    private int ptr = 0;

    public V get(K key) {
        var index = binarySearch(key);
        if(index >= 0) return data[index].second;
        return null;
    }

    // adapted from Arrays.binarySearch
    // edited as Arrays.binarySearch doesn't accept a mapping function
    private int binarySearch(K key) {
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            var midVal = data[mid];
            int cmp = PairComparator.compare(midVal == null ? null : midVal.first, key);
            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1); // key not found.
    }
    
    private void increaseBackingSize() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    public void put(K key, V value) {
        if(data.length >= ptr) increaseBackingSize();
        data[ptr] = new Pair<>(key, value);
        ptr += 1;
        // O(n) when almost sorted, which would be the case here. Allows use of binarySearch
        Arrays.sort(data, new PairComparator<>());
    }
}
