package com.vitnberg.portopt.stats;

import com.vitnberg.portopt.data.DataFrequency;
import com.vitnberg.portopt.portfolio.Asset;
import com.vitnberg.portopt.portfolio.AssetUniverse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.vitnberg.portopt.data.DataFrequency.ANNUAL;
import static com.vitnberg.portopt.data.DataFrequency.DAILY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnMomentsScalerTest {

    private static final double EPS = 1e-12;
    private static final Asset ASSET_1 = new Asset("AAPL");
    private static final Asset ASSET_2 = new Asset("MSFT");
    private static final AssetUniverse UNIVERSE = universe();

    @Test
    void scaleConvertsDailyMomentsToAnnual() {
        ReturnMoments scaled = ReturnMomentsScaler.scale(dailyReturnMoments(), ANNUAL);
        assertEquals(UNIVERSE, scaled.getUniverse());

        assertEquals(252.0, scaled.getMeanReturn(ASSET_1), EPS);
        assertEquals(504.0, scaled.getMeanReturn(ASSET_2), EPS);

        assertEquals(252.0, scaled.getCovariance(ASSET_1, ASSET_1));
        assertEquals(504.0, scaled.getCovariance(ASSET_1, ASSET_2));
        assertEquals(504.0, scaled.getCovariance(ASSET_2, ASSET_1));
        assertEquals(1008.0, scaled.getCovariance(ASSET_2, ASSET_2));

        assertEquals(ANNUAL, scaled.getFrequency());
    }

    private static ReturnMoments dailyReturnMoments() {
        return new ReturnMoments(UNIVERSE, meanReturns(), covarianceMatrix(), DAILY);
    }

    private static AssetUniverse universe() {
        return new AssetUniverse(List.of(ASSET_1, ASSET_2));
    }

    private static double[] meanReturns() {
        return new double[]{1.0, 2.0};
    }

    private static double[][] covarianceMatrix() {
        return new double[][]{
                {1.0, 2.0},
                {2.0, 4.0}
        };
    }

}
