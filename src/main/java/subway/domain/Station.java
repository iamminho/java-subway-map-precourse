package subway.domain;

public class Station {
    private String STATION_FORM = "^[가-힣]+역$";
    private String INVALID_FORM = "[ERROR] 역은 한글만 가능하며, 역으로 끝나야 합니다.";
    private String name;

    public Station(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        if (!name.matches(STATION_FORM)) {
            throw new IllegalArgumentException(INVALID_FORM);
        }
    }
}
