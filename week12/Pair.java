package week12;

class Pair<A, B> {
    final A first;
    final B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    // these getters doesn't seem to be needed, stdlib doesn't usually use getters for final values and instead just makes them public to be used directly
    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public Pair<B, A> swap() {
        return new Pair<B, A>(second, first);
    }

    public<C> Pair<C, B> withFst(C first) {
        return new Pair<C, B>(first, second);
    }

    public<C> Pair<A, C> withSnd(C second) {
        return new Pair<A, C>(first, second);
    }
}