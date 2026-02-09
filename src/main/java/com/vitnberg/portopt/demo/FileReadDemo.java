package com.vitnberg.portopt.demo;

import com.vitnberg.portopt.data.CsvMarketDataReader;

import java.io.IOException;
import java.nio.file.Path;

public class FileReadDemo {

    public static void main(String[] args) throws IOException {
        CsvMarketDataReader reader = new CsvMarketDataReader();
        reader.read(Path.of("src/main/resources/market-data.csv"));
    }
}
