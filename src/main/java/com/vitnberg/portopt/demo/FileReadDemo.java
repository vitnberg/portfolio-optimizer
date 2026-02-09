package com.vitnberg.portopt.demo;

import com.vitnberg.portopt.data.CsvMarketDataReader;
import com.vitnberg.portopt.data.DataFrequency;
import com.vitnberg.portopt.data.ReturnHistory;
import com.vitnberg.portopt.stats.ReturnMoments;
import com.vitnberg.portopt.stats.ReturnMomentsEstimator;

import java.io.IOException;
import java.nio.file.Path;

public class FileReadDemo {

    public static void main(String[] args) throws IOException {
        CsvMarketDataReader reader = new CsvMarketDataReader();
        ReturnHistory data = reader.read(Path.of("src/main/resources/market-data.csv"), DataFrequency.DAILY);

        ReturnMoments moments = ReturnMomentsEstimator.returnMoments(data);
        System.out.println("Finished");
    }
}
