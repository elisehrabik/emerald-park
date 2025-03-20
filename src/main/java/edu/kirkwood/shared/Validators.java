package edu.kirkwood.shared;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static boolean isStrongPassword(String password) {
        // Password requires 8 characters with at least 3 out 4 (uppercase letter, lowercase letter, number, special character ~`!@#$%^&*()_-+={}[]|\:;"'<>,.?/
        Pattern pattern = Pattern.compile("^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})[A-Za-z0-9~`!\\@#\\$%\\^&*()_\\-+={}\\[\\]\\|\\\\:;\"'<>,.?\\/]{8,128}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isANumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Source: Google Gemini
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Source: OWASP: https://owasp.org/www-community/OWASP_Validation_Regex_Repository
    public static boolean isValidState(String state) {
        state = state.toUpperCase();
        String regex = "^(AL|AK|AZ|AR|CA|CO|CT|DE|DC|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|TN|TX|UT|VT|VA|WA|WV|WI|WY)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(state);
        return matcher.matches();
    }
    // Source: OWASP: https://owasp.org/www-community/OWASP_Validation_Regex_Repository
    public static boolean isValidZip(String zip) {
        String regex = "^\\d{5}(-\\d{4})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(zip);
        return matcher.matches();
    }
    // Source: https://stackoverflow.com/questions/22214408/regular-expressions-for-country-name/35178090#35178090
    public static boolean isValidCountry(String country) {
        country = country.toUpperCase();
        String regex = "^(AF|AX|AL|DZ|AS|AD|AO|AI|AQ|AG|AR|AM|AW|AU|AT|AZ|BS|BH|BD|BB|BY|BE|BZ|BJ|BM|BT|BO|BQ|BA|BW|BV|BR|IO|BN|BG|BF|BI|KH|CM|CA|CV|KY|CF|TD|CL|CN|CX|CC|CO|KM|CG|CD|CK|CR|CI|HR|CU|CW|CY|CZ|DK|DJ|DM|DO|EC|EG|SV|GQ|ER|EE|ET|FK|FO|FJ|FI|FR|GF|PF|TF|GA|GM|GE|DE|GH|GI|GR|GL|GD|GP|GU|GT|GG|GN|GW|GY|HT|HM|VA|HN|HK|HU|IS|IN|ID|IR|IQ|IE|IM|IL|IT|JM|JP|JE|JO|KZ|KE|KI|KP|KR|KW|KG|LA|LV|LB|LS|LR|LY|LI|LT|LU|MO|MK|MG|MW|MY|MV|ML|MT|MH|MQ|MR|MU|YT|MX|FM|MD|MC|MN|ME|MS|MA|MZ|MM|NA|NR|NP|NL|NC|NZ|NI|NE|NG|NU|NF|MP|NO|OM|PK|PW|PS|PA|PG|PY|PE|PH|PN|PL|PT|PR|QA|RE|RO|RU|RW|BL|SH|KN|LC|MF|PM|VC|WS|SM|ST|SA|SN|RS|SC|SL|SG|SX|SK|SI|SB|SO|ZA|GS|SS|ES|LK|SD|SR|SJ|SZ|SE|CH|SY|TW|TJ|TZ|TH|TL|TG|TK|TO|TT|TN|TR|TM|TC|TV|UG|UA|AE|GB|US|UM|UY|UZ|VU|VE|VN|VG|VI|WF|EH|YE|ZM|ZW|AFG|ALB|DZA|ASM|AND|AGO|AIA|ATA|ATG|ARG|ARM|ABW|AUS|AUT|AZE|BHS|BHR|BGD|BRB|BLR|BEL|BLZ|BEN|BMU|BTN|BOL|BIH|BWA|BVT|BRA|IOT|VGB|BRN|BGR|BFA|BDI|KHM|CMR|CAN|CPV|CYM|CAF|TCD|CHL|CHN|CXR|CCK|COL|COM|COD|COG|COK|CRI|CIV|CUB|CYP|CZE|DNK|DJI|DMA|DOM|ECU|EGY|SLV|GNQ|ERI|EST|ETH|FRO|FLK|FJI|FIN|FRA|GUF|PYF|ATF|GAB|GMB|GEO|DEU|GHA|GIB|GRC|GRL|GRD|GLP|GUM|GTM|GIN|GNB|GUY|HTI|HMD|VAT|HND|HKG|HRV|HUN|ISL|IND|IDN|IRN|IRQ|IRL|ISR|ITA|JAM|JPN|JOR|KAZ|KEN|KIR|PRK|KOR|KWT|KGZ|LAO|LVA|LBN|LSO|LBR|LBY|LIE|LTU|LUX|MAC|MKD|MDG|MWI|MYS|MDV|MLI|MLT|MHL|MTQ|MRT|MUS|MYT|MEX|FSM|MDA|MCO|MNG|MSR|MAR|MOZ|MMR|NAM|NRU|NPL|ANT|NLD|NCL|NZL|NIC|NER|NGA|NIU|NFK|MNP|NOR|OMN|PAK|PLW|PSE|PAN|PNG|PRY|PER|PHL|PCN|POL|PRT|PRI|QAT|REU|ROU|RUS|RWA|SHN|KNA|LCA|SPM|VCT|WSM|SMR|STP|SAU|SEN|SCG|SYC|SLE|SGP|SVK|SVN|SLB|SOM|ZAF|SGS|ESP|LKA|SDN|SUR|SJM|SWZ|SWE|CHE|SYR|TWN|TJK|TZA|THA|TLS|TGO|TKL|TON|TTO|TUN|TUR|TKM|TCA|TUV|VIR|UGA|UKR|ARE|GBR|UMI|USA|URY|UZB|VUT|VEN|VNM|WLF|ESH|YEM|ZMB|ZWE)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(country);
        return matcher.matches();
    }

    // Source: https://chatgpt.com/share/6758fbb8-4c74-8006-a1ff-ef08b23f0ceb
    public static boolean isValidImage(String trail_image) {
        trail_image = trail_image.trim();
        String regex = "^(?:[a-zA-Z]:\\\\|\\.\\/|\\/)?(?:[\\w\\s-]+\\/)*[\\w\\s-]+\\.(?:jpg|jpeg|png|gif|bmp|svg)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trail_image);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        String regex = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isValidLanguage(String language) {
        String regex = "^(en-US|es-MX|fr-FR)$"; // define more languages here
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(language);
        return matcher.matches();
    }

    public static boolean isValidTimezone(String timezone) {
        String regex = "^(America/Chicago|America/New_York|America/Denver|America/Los_Angeles|America/Anchorage|Pacific/Honolulu)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timezone);
        return matcher.matches();
    }

}
