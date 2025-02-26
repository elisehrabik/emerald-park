package edu.kirkwood.shared;

import org.jsoup.Jsoup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Helpers {
    public static String round(double number, int numDecPlaces) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
        bigDecimal = bigDecimal.setScale(numDecPlaces, RoundingMode.HALF_UP).stripTrailingZeros();
        return bigDecimal.toString();
    }

    // https://stackoverflow.com/a/3149645/6629315
    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}