package com.vitnberg.portopt.data;

public enum DataFrequency {

    DAILY(252),
    WEEKLY(52),
    MONTHLY(12),
    QUARTERLY(4),
    ANNUAL(1);

    private final int periodsPerYear;

    DataFrequency(int periodsPerYear) {
        this.periodsPerYear = periodsPerYear;
    }

    public int periodsPerYear() {
        return periodsPerYear;
    }
}
