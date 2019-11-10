package hr.fer.nenr.lab3.domain;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompositeDomain extends Domain {

    private SimpleDomain[] components;

    public CompositeDomain(SimpleDomain[] components) {
        this.components = components;
    }

    @Override
    public int getCardinality() {
        int cardinality = 1;
        for (SimpleDomain component : components) {
            cardinality *= component.getCardinality();
        }
        return cardinality;
    }

    @Override
    public IDomain getComponent(int index) {
        return components[index];
    }

    @Override
    public int getNumberOfComponents() {
        return components.length;
    }

    @Override
    public Iterator<DomainElement> iterator() {
        return new CompositeDomainIterator();
    }

    private class CompositeDomainIterator implements Iterator<DomainElement> {

        private int[] currentValues = new int[components.length];

        private boolean endReached;

        CompositeDomainIterator() {
            for (int i = 0; i < components.length; i++) {
                currentValues[i] = components[i].getFirst();
            }
        }

        @Override
        public boolean hasNext() {
            return !endReached;
        }

        @Override
        public DomainElement next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iteration end reached");
            }

            int[] values = new int[components.length];
            System.arraycopy(currentValues, 0, values, 0, values.length);
            prepareNextValues();
            return DomainElement.of(values);
        }

        private void prepareNextValues() {
            for (int i = currentValues.length - 1; i >= 0; i--) {
                currentValues[i]++;
                if (currentValues[i] > components[i].getLast()) {
                    if (i == 0) {
                        endReached = true;
                        break;
                    }
                    currentValues[i] = components[i].getFirst();
                } else {
                    break;
                }
            }
        }
    }
}
