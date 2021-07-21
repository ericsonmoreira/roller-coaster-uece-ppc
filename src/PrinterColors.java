/**
 * Enum das cores que serão usadas em {@link Printer}
 */
public enum PrinterColors {
    RESET("\u001B[0m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    WHITE("\u001B[37m");

    private final String color;

    PrinterColors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
