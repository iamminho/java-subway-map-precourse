package subway.domain;

import java.util.List;

public class Subway {
    private final String NONE_STATION = "[ERROR] 해당 역은 없습니다.";
    private final String NONE_LINE = "[ERROR] 해당 노선은 없습니다.";

    public void registerStation(Station station) {
        StationRepository.addStation(station);
    }

    public void removeStation(Station station) {
        boolean isDelete = StationRepository.deleteStation(station.getName());
        validateStationDelete(isDelete);
    }

    private void validateStationDelete(boolean isDelete) {
        if (!isDelete) {
            throw new IllegalArgumentException(NONE_STATION);
        }
    }

    public void getStations() {
        StationRepository.getStations();
    }

    public void registerLine(Line line, Station first, Station end) {
        LineRepository.addLine(line);
        SectionRepository.registerLine(line, first, end);
    }

    public void removeLine(Line line) {
        boolean isDelete = LineRepository.deleteLineByName(line.getName());
        validateLineDelete(isDelete);
    }

    private void validateLineDelete(boolean isDelete) {
        if (!isDelete) {
            throw new IllegalArgumentException(NONE_LINE);
        }
    }

    public List<Line> getLines() {
        return LineRepository.lines();
    }

    public void registerSection(Line line, Station station, int sequence) {
        SectionRepository.addSection(line, station, sequence);
    }

    public void removeSection(Line line, Station station) {
        SectionRepository.removeSection(line, station);
    }
}
