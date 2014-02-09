package com.pivotallabs.lunchtime.model;

public class Match {
    private final Person first;
    private final Person second;

    public Match(Person first, Person second) {

        this.first = first;
        this.second = second;
    }

    public Person getFirst() {
        return first;
    }

    @Override
    public String toString() {
        return "Match{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (first != null ? !first.equals(match.first) : match.first != null) return false;
        if (second != null ? !second.equals(match.second) : match.second != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    public Person getSecond() {
        return second;
    }
}
