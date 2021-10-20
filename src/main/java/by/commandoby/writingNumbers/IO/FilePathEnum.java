package by.commandoby.writingNumbers.IO;

public enum FilePathEnum {
    UNIT_FILE_PATH("Units.txt"),
    TEEN_FILE_PATH("Teens.txt"),
    DOZEN_FILE_PATH("Dozens.txt"),
    HUNDRED_FILE_PATH("Hundreds.txt"),
    THOUSAND_FILE_PATH("Thousands.txt"),
    MILLION_FILE_PATH("Millions.txt"),
    BILLION_FILE_PATH("Billions.txt"),
    TRILLION_FILE_PATH("Trillions.txt"),
    QUADRILLION_FILE_PATH("Quadrillions.txt"),
    QUINTILLION_FILE_PATH("Quintillions.txt");

    private final String value;

    FilePathEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
