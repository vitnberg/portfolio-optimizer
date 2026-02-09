package com.vitnberg.portopt.stats;

import com.vitnberg.portopt.data.DataFrequency;
import com.vitnberg.portopt.data.ReturnHistory;
import com.vitnberg.portopt.portfolio.Asset;
import com.vitnberg.portopt.portfolio.AssetUniverse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReturnMomentsEstimatorTest {

    private static final double EPS = 1e-12;
    private static final Asset ASSET1 = new Asset("AAPL");
    private static final Asset ASSET2 = new Asset("MSFT");

    @Test
    void returnMomentsComputesCorrectMeanReturns() {
        ReturnMoments moments = ReturnMomentsEstimator.returnMoments(returnHistory(assetUniverse(), historicReturns()));
        assertEquals(2.0, moments.getMeanReturn(ASSET1), EPS);
        assertEquals(0.0, moments.getMeanReturn(ASSET2), EPS);
    }

    @Test
    void returnMomentsComputesCorrectCovariances() {
        ReturnMoments moments = ReturnMomentsEstimator.returnMoments(returnHistory(assetUniverse(), historicReturns()));

        assertEquals(1.0, moments.getCovariance(ASSET1, ASSET1), EPS);
        assertEquals(1.5, moments.getCovariance(ASSET1, ASSET2), EPS);
        assertEquals(1.5, moments.getCovariance(ASSET2, ASSET1), EPS);
        assertEquals(3.0, moments.getCovariance(ASSET2, ASSET2), EPS);
    }

    @Test
    void returnMomentsThrowsWhenInsufficientHistoricalData() {
        assertThrows(IllegalArgumentException.class, () -> ReturnMomentsEstimator.returnMoments(returnHistory(assetUniverse(), insufficientHistoricReturns())));
    }

    @Test
    void returnMomentsThrowsWhenAssetDimensionMismatch() {
        assertThrows(IllegalArgumentException.class, () -> ReturnMomentsEstimator.returnMoments(returnHistory(incompleteAssetUniverse(), historicReturns())));
        assertThrows(IllegalArgumentException.class, () -> ReturnMomentsEstimator.returnMoments(returnHistory(overcompleteAssetUniverse(), historicReturns())));
    }

    @Test
    void returnMomentsThrowsWhenRaggedHistoricalData() {
        assertThrows(IllegalArgumentException.class, () -> ReturnMomentsEstimator.returnMoments(returnHistory(assetUniverse(), raggedHistoricReturns())));
    }

    private static ReturnHistory returnHistory(AssetUniverse universe, double[][] values) {
        return new ReturnHistory(universe, DataFrequency.DAILY, List.of(), values);
    }

    private static double[][] historicReturns() {
        return new double[][] {
                {1.0, -2.0},
                {2.0, 1.0},
                {3.0, 1.0}
        };
    }

    private static double[][] insufficientHistoricReturns() {
        return new double[][] {
                {1.0, -2.0}
        };
    }

    private static double[][] raggedHistoricReturns() {
        return new double[][] {
                {1.0, -2.0},
                {2.0}
        };
    }

    private static AssetUniverse assetUniverse() {
        return new AssetUniverse(List.of(ASSET1, ASSET2));
    }

    private static AssetUniverse incompleteAssetUniverse() {
        return new AssetUniverse(List.of(ASSET1));
    }

    private static AssetUniverse overcompleteAssetUniverse() {
        return new AssetUniverse(List.of(ASSET1, ASSET2, new Asset("IRRELEVANT")));
    }

}
