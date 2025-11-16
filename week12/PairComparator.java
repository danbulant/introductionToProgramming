package week12;

import java.util.Comparator;

public class PairComparator<A extends Comparable<A>, B> implements Comparator<Pair<A, B>> {
    @Override
    public int compare(Pair<A, B> o1, Pair<A, B> o2) {
        return compare(o1 == null ? null : o1.first, o2 == null ? null : o2.first);
    }

    static <K extends Comparable<K>> int compare(K firstKey, K secondKey) {
        if(firstKey == secondKey) return 0;
        if(firstKey == null) return 1;
        if(secondKey == null) return -1;
        return firstKey.compareTo(secondKey);
    }
}
