package pgdp.streams;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        return stream.collect(Collectors.groupingBy(PenguinData::getTrackID))
                .entrySet().stream()
                .map(entry -> {
                    List<Geo> geos = entry.getValue().stream()
                            .map(PenguinData::getGeom)
                            .collect(Collectors.toList());
                    return new Penguin(geos, entry.getKey());
                });
    }


    public static void outputPenguinStream() {

        System.out.println(getDataByTrackId(CSVReading.processInputFile()).count());
        List<Penguin> result = new ArrayList<>(getDataByTrackId(CSVReading.processInputFile()).sorted(Comparator.comparing(Penguin::getTrackID)).toList());
        result.forEach(x -> System.out.println(x.toStringUsingStreams()));
    }


    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        List<PenguinData> dataList = stream.toList();
        dataList.stream()
                .collect(Collectors.groupingBy(PenguinData::getTrackID))
                .forEach((trackID, penguinDataPerTrackId) -> {

                    DoubleSummaryStatistics longi = penguinDataPerTrackId.stream()
                            .map(PenguinData::getGeom)
                            .mapToDouble(Geo::getLongitude)
                            .summaryStatistics();

                    DoubleSummaryStatistics lati = penguinDataPerTrackId.stream()
                            .map(PenguinData::getGeom)
                            .mapToDouble(Geo::getLatitude)
                            .summaryStatistics();

        System.out.println(trackID);
        System.out.print("Min Longitude: " + longi.getMin() + " Max Longitude: " + longi.getMax() + " Avg Longitude: " + longi.getAverage());
        System.out.println(" Min Latitude: " + lati.getMin() + " Max Latitude: " + lati.getMax() + " Avg Latitude: " + lati.getAverage());
    });

}

    public static void main(String[] args) {
        System.out.println(CSVReading.processInputFile());
        getDataByTrackId(CSVReading.processInputFile());
        outputPenguinStream();
        CSVReading.processInputFile();
        outputLocationRangePerTrackid(CSVReading.processInputFile());
    }
}
