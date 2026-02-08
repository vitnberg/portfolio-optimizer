package com.vitnberg.portopt.portfolio;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PortfolioValidatorTest {

    @Test
    void validateFullyInvestedDoesNotThrowWhenWeightsSumToOne() {
        assertDoesNotThrow(() -> PortfolioValidator.validateFullyInvested(completeLongOnlyPortfolio()));
        assertDoesNotThrow(() -> PortfolioValidator.validateFullyInvested(completeLongShortPortfolio()));
    }

    @Test
    void validateFullyInvestedThrowsWhenPortfolioNotFullyInvested() {
        assertThrows(IllegalArgumentException.class, () -> PortfolioValidator.validateFullyInvested(incompletePortfolio()));
        assertThrows(IllegalArgumentException.class, () -> PortfolioValidator.validateFullyInvested(overInvestedPortfolio()));
    }

    @Test
    void validateLongOnlyDoesNotThrowForNonNegativeWeights() {
        assertDoesNotThrow(() -> PortfolioValidator.validateLongOnly(completeLongOnlyPortfolio()));
    }

    @Test
    void validateLongOnlyThrowsIfPortfolioHasShortPositions() {
        assertThrows(IllegalArgumentException.class, () -> PortfolioValidator.validateLongOnly(completeLongShortPortfolio()));
    }

    private static Portfolio incompletePortfolio() {
        return portfolio(new double[]{0.3, 0.4});
    }

    private static Portfolio overInvestedPortfolio() {
        return portfolio(new double[]{0.5, 0.8});
    }

    private static Portfolio completeLongOnlyPortfolio() {
        return portfolio(new double[]{0.2, 0.8});
    }

    private static Portfolio completeLongShortPortfolio() {
        return portfolio(new double[]{1.2, -0.2});
    }

    private static Portfolio portfolio(double[] weights) {
        return new Portfolio(universe(), weights);
    }

    private static AssetUniverse universe() {
        Asset asset1 = new Asset("AAPL");
        Asset asset2 = new Asset("MSFT");

        return new AssetUniverse(List.of(asset1, asset2));
    }
}
