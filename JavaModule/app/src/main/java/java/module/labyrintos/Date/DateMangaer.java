package java.module.labyrintos.Date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Labyrintos on 2019-10-10
 */
public class DateMangaer {
    // 현재 날짜(년/월/일 시/분/초)
    public String doYearMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HH-mm-ss-SSS", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }

}
