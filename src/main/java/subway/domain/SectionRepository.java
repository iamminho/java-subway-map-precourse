package subway.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectionRepository {
    private static final String NONE_SECTION = "[ERROR] 없는 노선입니다.";
    private static final String INVALID_SEQUENCE = "[ERROR] 잘못된 순서입니다.";
    private static final int MIN_INDEX = 0;
    private static final int MAKE_MAX_INDEX = 1;

    private static List<Section> sections;

    public SectionRepository(List<Section> sections) {
        this.sections = sections;
    }

    public static void registerLine(Line line, Station first, Station end) {
        Section section = new Section(line.getName(), Arrays.asList(first, end));
        sections.add(section);
    }

    public static void addSection(Line line, Station station, int sequence) {
        Section section = findSection(line.getName());
        validateSequence(section, sequence);
        section.addStation(station, sequence);
    }

    private static Section findSection(String lineName) {
        return sections.stream()
                .filter(section -> Objects.equals(section.getName(), lineName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NONE_SECTION));
    }

    private static void validateSequence(Section section, int sequence) {
        if (MIN_INDEX > sequence || section.getSize() - MAKE_MAX_INDEX < sequence) {
            throw new IllegalArgumentException(INVALID_SEQUENCE);
        }
    }

    public static void removeSection(Line line, Station station) {
        Section section = findSection(line.getName());
        Station target = section.findStation(station);
        section.removeStation(target);
    }
}
