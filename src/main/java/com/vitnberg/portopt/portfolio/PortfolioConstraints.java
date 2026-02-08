package com.vitnberg.portopt.portfolio;

public final class PortfolioConstraints {
    private final boolean longOnly;
    private final boolean fullyInvested;

    private PortfolioConstraints(boolean longOnly, boolean fullyInvested) {
        this.longOnly = longOnly;
        this.fullyInvested = fullyInvested;
    }

    public boolean isLongOnly() {
        return longOnly;
    }

    public boolean isFullyInvested() {
        return fullyInvested;
    }

    public static PortfolioConstraints unconstrained() {
        return new PortfolioConstraints(false, false);
    }

    public static PortfolioConstraints fullyInvestedWithShortSellingAllowed() {
        return new PortfolioConstraints(false, true);
    }

    public static PortfolioConstraints longOnlyWithCashAllowed() {
        return new PortfolioConstraints(true, false);
    }

    public static PortfolioConstraints longOnlyFullyInvested() {
        return new PortfolioConstraints(true, true);
    }
}
