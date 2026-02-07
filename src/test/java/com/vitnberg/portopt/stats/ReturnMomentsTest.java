package com.vitnberg.portopt.stats;

import com.vitnberg.portopt.portfolio.Asset;
import com.vitnberg.portopt.portfolio.AssetUniverse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReturnMomentsTest {

    private static final double EPS = 1e-12;
    private static final Asset ASSET_1 = new Asset("AAPL");
    private static final Asset ASSET_2 = new Asset("MSFT");

    @Test
    void constructorThrowsIfMeanReturnsDimensionMismatch() {
        double[] wrongSizeMeanReturns = {0.01}; // wrong size: should be 2

        assertThrows(IllegalArgumentException.class, () -> new ReturnMoments(twoAssetUniverse(), wrongSizeMeanReturns, twoAssetCovarianceMatrix()));
    }

    @Test
    void constructorThrowsIfCovarianceMatrixSizeMismatch() {
        double[][] wrongSizeCovarianceMatrix = {{0.1}}; // wrong size: should contain 2 rows and 2 columns
        assertThrows(IllegalArgumentException.class, () -> new ReturnMoments(twoAssetUniverse(), twoAssetReturns(), wrongSizeCovarianceMatrix));

        double[][] wrongFormCovarianceMatrix = {
                {0.1},
                {0.2}
        }; // wrong matrix form: should be a square matrix
        assertThrows(IllegalArgumentException.class, () -> new ReturnMoments(twoAssetUniverse(), twoAssetReturns(), wrongFormCovarianceMatrix));
    }

    @Test
    void createDefensiveCopiesOfMeanReturns() {
        double[] inputMeanReturns = twoAssetReturns();
        ReturnMoments instance = new ReturnMoments(twoAssetUniverse(), inputMeanReturns, twoAssetCovarianceMatrix());

        inputMeanReturns[0] = -0.099;
        assertEquals(0.012, instance.getMeanReturns()[0], EPS);

        double[] returnedMeanReturns = instance.getMeanReturns();
        returnedMeanReturns[0] = -0.099;
        assertEquals(0.012, instance.getMeanReturns()[0], EPS);
    }

    @Test
    void createDefensiveCopiesOfCovarianceMatrix() {
        double[][] inputCovarianceMatrix = twoAssetCovarianceMatrix();
        ReturnMoments instance = new ReturnMoments(twoAssetUniverse(), twoAssetReturns(), inputCovarianceMatrix);

        inputCovarianceMatrix[0][0] = 0d;
        assertEquals(0.01, instance.getCovarianceMatrix()[0][0], EPS);

        double[][] returnedCovarianceMatrix = instance.getCovarianceMatrix();
        returnedCovarianceMatrix[0][0] = 0d;
        assertEquals(0.01, instance.getCovarianceMatrix()[0][0], EPS);
    }

    @Test
    void getMeanReturnForAsset() {
        ReturnMoments instance = returnMoments();
        assertEquals(0.012, instance.getMeanReturn(ASSET_1), EPS);
        assertEquals(-0.033, instance.getMeanReturn(ASSET_2), EPS);
    }

    @Test
    void getVarianceForAsset() {
        ReturnMoments instance = returnMoments();
        assertEquals(0.01, instance.getVariance(ASSET_1), EPS);
        assertEquals(0.04, instance.getVariance(ASSET_2), EPS);
    }

    @Test
    void getVolatilityForAsset() {
        ReturnMoments instance = returnMoments();
        assertEquals(0.1, instance.getVolatility(ASSET_1), EPS);
        assertEquals(0.2, instance.getVolatility(ASSET_2), EPS);
    }

    @Test
    void getCovarianceForAssets() {
        ReturnMoments instance = returnMoments();
        assertEquals(0.01, instance.getCovariance(ASSET_1, ASSET_1), EPS);
        assertEquals(0.02, instance.getCovariance(ASSET_1, ASSET_2), EPS);
        assertEquals(0.02, instance.getCovariance(ASSET_2, ASSET_1), EPS);
        assertEquals(0.04, instance.getCovariance(ASSET_2, ASSET_2), EPS);
    }

    @Test
    void throwIfAssetOutOfUniverse() {
        ReturnMoments instance = returnMoments();
        Asset externalAsset = new Asset("AMZN");
        assertThrows(IllegalArgumentException.class, () -> instance.getMeanReturn(externalAsset));
        assertThrows(IllegalArgumentException.class, () -> instance.getVariance(externalAsset));
        assertThrows(IllegalArgumentException.class, () -> instance.getVolatility(externalAsset));
        assertThrows(IllegalArgumentException.class, () -> instance.getCovariance(externalAsset, ASSET_1));
    }

    private ReturnMoments returnMoments() {
        return new ReturnMoments(twoAssetUniverse(), twoAssetReturns(), twoAssetCovarianceMatrix());
    }

    private AssetUniverse twoAssetUniverse() {
        return new AssetUniverse(List.of(ASSET_1, ASSET_2));
    }

    private double[] twoAssetReturns() {
        return new double[] { 0.012, -0.033 };
    }

    private double[][] twoAssetCovarianceMatrix() {
        return new double[][] {
                {0.01, 0.02},
                {0.02, 0.04}
        };
    }

}
