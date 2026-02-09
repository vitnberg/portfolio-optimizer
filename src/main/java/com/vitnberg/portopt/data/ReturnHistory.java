package com.vitnberg.portopt.data;

import com.vitnberg.portopt.portfolio.AssetUniverse;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public final class ReturnHistory {
    private final AssetUniverse universe;
    private final DataFrequency frequency;
    private final List<LocalDate> dates;
    private final double[][] returns;

    public ReturnHistory(AssetUniverse universe, DataFrequency frequency, List<LocalDate> dates, double[][] returns) {
        this.universe = Objects.requireNonNull(universe);
        this.frequency = Objects.requireNonNull(frequency);
        this.dates = Objects.requireNonNull(dates);
        this.returns = Objects.requireNonNull(returns);
    }

    public AssetUniverse universe() {
        return universe;
    }

    public DataFrequency frequency() {
        return frequency;
    }

    public List<LocalDate> dates() {
        return dates;
    }

    public double[][] returns() {
        return returns;
    }

    public int observations() {
        return returns.length;
    }
}
