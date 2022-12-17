package subway.domain;

import java.util.List;
import java.util.Objects;

public class Section {
    private final String NONE_STATION = "[ERROR] 없는 역입니다.";
    private String name;
    private List<Station> section;

    public Section(String name, List<Station> section) {
        this.name = name;
        this.section = section;
    }

    public void addStation(Station station, int sequence) {
        section.add(sequence, station);
    }

    public Station findStation(Station target) {
        return section.stream()
                .filter(station -> Objects.equals(station.getName(), target.getName()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NONE_STATION));
    }

    public void removeStation(Station station) {
        section.removeIf(eleStation -> Objects.equals(eleStation.getName(), station.getName()));
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return section.size();
    }
}
