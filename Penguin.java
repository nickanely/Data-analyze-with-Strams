package pgdp.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Penguin {
    private List<Geo> locations;
    private String trackID;

    public Penguin(List<Geo> locations, String trackID) {
        this.locations = locations;
        this.trackID = trackID;
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "locations=" + locations +
                ", trackID='" + trackID + '\'' +
                '}';
    }

    public List<Geo> getLocations() {
        return locations;
    }

    public String getTrackID() {
        return trackID;
    }

    public String toStringUsingStreams() {
        locations = locations.stream()
                .sorted(Comparator.comparing(Geo::getLatitude)
                        .thenComparing(Geo::getLongitude)
                        .reversed())
                .toList();
        return toString();
    }
}

        //chemi  !!
//        StringBuilder sb = new StringBuilder();
//        sb.append("Penguin{locations=[");
//        List<Geo> sorted = locations.stream().sorted(Comparator.comparing(Geo::getLatitude).reversed()
//                        .thenComparing(Geo::getLongitude).reversed())
//                .toList();
//        sorted.forEach(location ->
//                sb.append("Geo{latitude=").append(location.getLatitude())
//                        .append(", longitude=").append(location.getLongitude()).append("}, "));
//
//        sb.append("], trackID='").append(trackID).append("'}");
//        return sb.toString();
//    }