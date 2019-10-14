package hr.fer.nenr.lab1.domain;

import java.util.Arrays;

public class DomainElement {

    private int[] values;

    public DomainElement(int[] values) {
        this.values = values;
    }

    public int getNumberOfComponents() {
        return values.length;
    }

    public int getComponentValue(int index) {
        return values[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainElement that = (DomainElement) o;
        return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        if (values.length == 1) return String.valueOf(values[0]);

        StringBuilder sb = new StringBuilder("(");
        for (int value : values) {
            sb.append(value).append(',');
        }
        sb.setCharAt(sb.length() - 1, ')');
        return sb.toString();
    }

    public static DomainElement of(int... values) {
        return new DomainElement(values);
    }
}
