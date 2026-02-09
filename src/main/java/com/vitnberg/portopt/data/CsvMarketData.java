package com.vitnberg.portopt.data;

import com.vitnberg.portopt.portfolio.AssetUniverse;

import java.time.LocalDate;
import java.util.List;

public record CsvMarketData(
        AssetUniverse universe,
        List<LocalDate> dates,
        double[][] values
) {
}
