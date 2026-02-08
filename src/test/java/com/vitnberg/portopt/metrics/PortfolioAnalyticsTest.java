package com.vitnberg.portopt.metrics;

import com.vitnberg.portopt.portfolio.Asset;
import com.vitnberg.portopt.portfolio.AssetUniverse;
import com.vitnberg.portopt.portfolio.Portfolio;
import com.vitnberg.portopt.stats.ReturnMoments;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PortfolioAnalyticsTest {

    private static final double EPS = 1e-12;
    private static final Asset ASSET_1 = new Asset("AAPL");
    private static final Asset ASSET_2 = new Asset("MSFT");
    private static final AssetUniverse ASSET_UNIVERSE = new AssetUniverse(List.of(ASSET_1, ASSET_2));

    @Test
    void calculateExpectedReturn() {
        PortfolioAnalytics instance = new PortfolioAnalytics(returnMoments());
        assertEquals(1.0, instance.expectedReturn(portfolio()), EPS);
    }

    @Test
    void calculateVariance() {
        PortfolioAnalytics instance = new PortfolioAnalytics(returnMoments());
        assertEquals(0.0225, instance.variance(portfolio()), EPS);
    }

    @Test
    void calculateVolatility() {
        PortfolioAnalytics instance = new PortfolioAnalytics(returnMoments());
        assertEquals(0.15, instance.volatility(portfolio()), EPS);
    }

    @Test
    void throwWhenAssetUniverseMismatch() {
        AssetUniverse otherUniverse = new AssetUniverse(List.of(new Asset("AMZN"), new Asset("F")));
        PortfolioAnalytics instance = new PortfolioAnalytics(returnMoments(otherUniverse));

        Portfolio p = portfolio();
        assertThrows(IllegalArgumentException.class, () -> instance.expectedReturn(p));
        assertThrows(IllegalArgumentException.class, () -> instance.variance(p));
        assertThrows(IllegalArgumentException.class, () -> instance.volatility(p));
    }

    private static ReturnMoments returnMoments() {
        return new ReturnMoments(ASSET_UNIVERSE, meanReturns(), covarianceMatrix());
    }

    private static ReturnMoments returnMoments(AssetUniverse universe) {
        return new ReturnMoments(universe, meanReturns(), covarianceMatrix());
    }

    private static Portfolio portfolio() {
        double[] weights = {0.5, 0.5};
        return new Portfolio(ASSET_UNIVERSE, weights);
    }

    private static double[] meanReturns() {
        return new double[] {4.0, -2.0};
    }

    private static double[][] covarianceMatrix() {
        return new double[][] {
                {0.01, 0.02},
                {0.02, 0.04}
        };
    }

}
