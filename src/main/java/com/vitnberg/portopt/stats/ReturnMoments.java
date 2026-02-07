package com.vitnberg.portopt.stats;

import com.vitnberg.portopt.math.MatrixUtils;
import com.vitnberg.portopt.portfolio.Asset;
import com.vitnberg.portopt.portfolio.AssetUniverse;

public class ReturnMoments {

    private final AssetUniverse universe;
    private final double[] meanReturns;
    private final double[][] covarianceMatrix;

    public ReturnMoments(AssetUniverse universe, double[] meanReturns, double[][] covarianceMatrix) {
        if (universe.size() != meanReturns.length) {
            throw new IllegalArgumentException("Mean returns vector dimension mismatch");
        }
        MatrixUtils.validateSquareMatrix(covarianceMatrix, universe.size());

        this.universe = universe;
        this.meanReturns = meanReturns.clone();
        this.covarianceMatrix = MatrixUtils.deepCopy(covarianceMatrix);
    }

    public AssetUniverse getUniverse() {
        return universe;
    }

    public double[] getMeanReturns() {
        return meanReturns.clone();
    }

    public double[][] getCovarianceMatrix() {
        return MatrixUtils.deepCopy(covarianceMatrix);
    }

    public double getMeanReturn(Asset asset) {
        return meanReturns[getIndex(asset)];
    }

    public double getVariance(Asset asset) {
        int assetIndex = getIndex(asset);
        return covarianceMatrix[assetIndex][assetIndex];
    }

    public double getVolatility(Asset asset) {
        return Math.sqrt(getVariance(asset));
    }

    public double getCovariance(Asset asset1, Asset asset2) {
        return covarianceMatrix[getIndex(asset1)][getIndex(asset2)];
    }

    private int getIndex(Asset asset) {
        int assetIndex = universe.indexOf(asset);
        if (assetIndex == -1) {
            throw new IllegalArgumentException("Asset is out of the asset universe");
        }
        return assetIndex;
    }

}
