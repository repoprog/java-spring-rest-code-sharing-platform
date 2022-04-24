package platform;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LoadDate{
    private static final String NANO_FORMATTER= "yyyy/MM/dd HH:mm:ss.SSSSSSSSS";
    private static final String SIMPLE_FORMATTER= "yyyy/MM/dd HH:mm:ss";

    public static String getLoadDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NANO_FORMATTER);
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    }

    public static String formatDate(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NANO_FORMATTER);
        String formatDateTime = localDateTime.format(formatter);
        return date;
    }
}
