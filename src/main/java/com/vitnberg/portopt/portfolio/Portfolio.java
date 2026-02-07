package com.vitnberg.portopt.portfolio;

public final class Portfolio {

    private final AssetUniverse universe;
    private final double[] weights;

    public Portfolio(AssetUniverse universe, double[] weights) {
        if (universe.size() != weights.length) {
            throw new IllegalArgumentException("Weight vector dimension mismatch");
        }
        this.universe = universe;
        this.weights = weights;
    }

}
