package org.softuni.exercises.constants;

public final class StringConstants {

    public static final String APARTMENT_TEMPLATE = "<div class=\"apartment\">\n" +
            "\t\t<p>Rent: %.2f</p>\n" +
            "\t\t<p>Type: %s</p>\n" +
            "\t\t<p>Commission: %.1f%%</p>\n" +
            "\t</div>";

    public static final String THERE_ARE_NO_OFFERS_TEMPLATE = "<div class=\"noApartments\">\n" +
            "\t\t<p>There aren't any offers</p>\n" +
            "\t</div>";



    public static final String INDEX_HTML_PATH = "C:\\Users\\Ivaylo\\Documents\\SoftUni\\JavaWeb2019" +
            "\\JavaMVCFrameworks_Spring\\01_Spring_Boot_Introduction\\exercises\\src\\main\\resources" +
            "\\templates\\index.html";

    private StringConstants() {
    }
}
