package com.vitnberg.portopt.demo;

import com.vitnberg.portopt.data.CsvMarketData;
import com.vitnberg.portopt.data.CsvMarketDataReader;
import com.vitnberg.portopt.stats.ReturnMoments;
import com.vitnberg.portopt.stats.ReturnMomentsEstimator;

import java.io.IOException;
import java.nio.file.Path;

public class FileReadDemo {

    public static void main(String[] args) throws IOException {
        CsvMarketDataReader reader = new CsvMarketDataReader();
        CsvMarketData data = reader.read(Path.of("src/main/resources/market-data.csv"));

        ReturnMoments moments = ReturnMomentsEstimator.returnMoments(data.universe(), data.values());
        System.out.println("Finished");
    }
}
