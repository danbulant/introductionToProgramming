package week12;

public class Dict<K, V> {
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] data = new Pair[4];

    public static void main(String[] args) {
        System.out.printf("%s %s %s\n", 1, 4, getIndex(1, 4));
        System.out.printf("%s %s %s\n", 1, 8, getIndex(1, 8));
        System.out.printf("%s %s %s\n", 1073741824, 4, getIndex(1073741824, 4));
        System.out.printf("%s %s %s\n", 1073741824, 8, getIndex(1073741824, 8));
        System.out.printf("%s %s %s\n", Integer.MIN_VALUE, 4, getIndex(Integer.MIN_VALUE, 4));
        System.out.printf("%s %s %s\n", Integer.MIN_VALUE, 8, getIndex(Integer.MIN_VALUE, 8));
        System.out.printf("%s %s %s\n", Integer.MAX_VALUE, 4, getIndex(Integer.MAX_VALUE, 4));
        System.out.printf("%s %s %s\n", Integer.MAX_VALUE, 8, getIndex(Integer.MAX_VALUE, 8));
    }

    public V get(K key) {
        var hashCode = key.hashCode();
        var hash = hashCode;
        // try getting a pair as long as there's a hash collision
        while(true) {
            var index = getIndex(hash);
            var pair = data[index];
            if(pair == null) return null;
            if(pair.first.equals(key)) return pair.second;
            var secondHash = pair.first.hashCode();
            if(hashCode != secondHash) return null;
            // this can overflow but should always be in range of the array when used with getIndex
            hash += 1;
        }
    }

    private static int getIndex(int hashCode, int length) {
        // using this instead of modulo allows us to simply insert nulls on every odd number when doubling array instead of having to rehash
        // this changes the range of number from <MIN_VALUE, MAX_VALUE> to <0, data.length>
        long hash = hashCode;
        hash -= Integer.MIN_VALUE; // make the number positive, i.e. from 0 to 2*MAX_VALUE+1 (MIN_VALUE = -MAX_VALUE - 1)
        hash *= length; // this may cause overflow in int
        hash /= Integer.MAX_VALUE; // this will always put the number back into int range, as array length is at most MAX_VALUE
        hash /= 2;
        return (int)hash;
    }

    private int getIndex(int hashCode) {
        return getIndex(hashCode, data.length);
    }

    private void increaseBackingSize() {
        @SuppressWarnings("unchecked")
        Pair<K, V>[] newData = new Pair[data.length * 2];
        for(var i = 0; i < data.length; i++) {
            newData[i * 2] = data[i];
        }
        data = newData;
    }
    
    public void put(K key, V value) {
        var hashCode = key.hashCode();
        var hash = hashCode;
        var index = getIndex(hash);
        var existingPair = data[index];
        var newPair = new Pair<>(key, value);
        if(existingPair != null && existingPair.first.equals(key)) {
            data[index] = existingPair.withSnd(value);
            return; // some of these returns aren't needed but directly show exit points of the function at a glance
        } else if(existingPair != null) {
            var originalHash = existingPair.first.hashCode();
            if(hash == originalHash) {
                // full collision of hash, cannot solve so add +1 to hash and try to insert it again
                // a different possible solution is to use 2d arrays, with the topmost being a mapping of hashcodes to arrays of pairs

                // insert the pair into first empty space after hash collisions. If there's something else though, increase backing size and try again, it should fit right after then.
                // Simply trying to put again recomputes stuff needlessly but simplifies code.
                while(true) {
                    hash += 1;
                    index = getIndex(hash);
                    existingPair = data[index];
                    if(existingPair == null) {
                        data[index] = newPair;
                        return;
                    }
                    originalHash = existingPair.first.hashCode();
                    if(hash == originalHash) {
                        continue;
                    } else {
                        increaseBackingSize();
                        put(key, value);
                        return;
                    }
                }
            } else {
                // a better algorithm would be to calculate the required length for the collision to disappear, but this should also work eventually and is simpler
                increaseBackingSize();
                put(key, value);
                return;
            }
        } else {
            data[index] = newPair;
        }
    }
}
