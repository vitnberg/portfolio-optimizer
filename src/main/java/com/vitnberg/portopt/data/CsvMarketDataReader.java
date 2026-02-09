package com.vitnberg.portopt.data;

import com.vitnberg.portopt.portfolio.Asset;
import com.vitnberg.portopt.portfolio.AssetUniverse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class CsvMarketDataReader {

    private final DateTimeFormatter dateFormatter;

    public CsvMarketDataReader() {
        this(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    public CsvMarketDataReader(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public ReturnHistory read(Path csvPath, DataFrequency frequency) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setTrim(true)
                .build();

        try (Reader reader = Files.newBufferedReader(csvPath)) {
            CSVParser parser = new CSVParser(reader, format);

            List<Asset> assets = parser.getHeaderNames().stream()
                    .skip(1)
                    .map(Asset::new)
                    .toList();
            AssetUniverse universe = new AssetUniverse(assets);

            List<LocalDate> dates = new ArrayList<>();
            List<double[]> rows = new ArrayList<>();

            for (CSVRecord record : parser) {
                LocalDate date = LocalDate.parse(record.get(0), dateFormatter);
                dates.add(date);
                double[] values = new double[assets.size()];
                for (int i = 0; i < assets.size(); i++) {
                    values[i] = Double.parseDouble(record.get(i + 1).trim());
                }
                rows.add(values);
            }

            return new ReturnHistory(universe, frequency, dates, rows.toArray(new double[0][]));
        }
    }

}
