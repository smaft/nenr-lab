package hr.fer.nenr.lab1.domain;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDomain extends Domain {

    private int first;

    private int last;

    public SimpleDomain(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    @Override
    public int getCardinality() {
        return last - first + 1;
    }

    @Override
    public IDomain getComponent(int index) {
        return this;
    }

    @Override
    public int getNumberOfComponents() {
        return 1;
    }

    @Override
    public Iterator<DomainElement> iterator() {
        return new SimpleDomainIterator();
    }

    private class SimpleDomainIterator implements Iterator<DomainElement> {

        private int current = first;

        @Override
        public boolean hasNext() {
            return current <= last;
        }

        @Override
        public DomainElement next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iteration end reached");
            }

            DomainElement next = DomainElement.of(current);
            current++;
            return next;
        }
    }
}
