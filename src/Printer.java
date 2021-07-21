/**
 * Tratador de prints coloridos
 */
public class Printer {

    /**
     * Print uma linha com uma cor.
     *
     * @param text {@link String}
     * @param color Cor do texto
     */
    public static void printlnColor(String text, PrinterColors color) {
        System.out.println(color.getColor() + text + PrinterColors.RESET.getColor());
    }
}
