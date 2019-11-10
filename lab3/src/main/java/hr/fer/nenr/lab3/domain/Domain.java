package hr.fer.nenr.lab3.domain;

public abstract class Domain implements IDomain {

    public static IDomain intRange(int from, int to) {
        return new SimpleDomain(from, to - 1);
    }

    public static IDomain combine(IDomain first, IDomain second) {
        int firstSize = first.getNumberOfComponents();
        int secondSize = second.getNumberOfComponents();

        SimpleDomain[] components = new SimpleDomain[firstSize + secondSize];
        for (int i = 0; i < firstSize; i++) {
            components[i] = (SimpleDomain) first.getComponent(i);
        }
        for (int i = 0; i < secondSize; i++) {
            components[firstSize + i] = (SimpleDomain) second.getComponent(i);
        }

        return new CompositeDomain(components);
    }

    @Override
    public int indexOfElement(DomainElement element) {
        int index = 0;
        for (DomainElement e : this) {
            if (e.equals(element)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public DomainElement elementForIndex(int index) {
        int i = 0;
        for (DomainElement e : this) {
            if (i == index) return e;
            i++;
        }
        return null;
    }
}
