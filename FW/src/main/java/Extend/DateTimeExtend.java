package Extend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017-07-28.
 */
public class DateTimeExtend {
    //功能描述: 根据出生日期计算年龄
    //<param name="p_dteBirthday">出生日期</param>
    public static String GetAge(Date p_dteBirthday) {
        Date dateNow = new Date();
        int m_intAge;
        m_intAge = dateNow.getYear() - p_dteBirthday.getYear();
        if ((dateNow.getMonth() < p_dteBirthday.getMonth()) || (dateNow.getMonth() == p_dteBirthday.getMonth() && dateNow.getDay() < p_dteBirthday.getDay()))
            m_intAge--;
        return Integer.toString(m_intAge);
    }
    public static String GetWeekCName(Date p_dtDay) {
        Calendar m_Calendar = Calendar.getInstance();
        m_Calendar.setTime(p_dtDay);
        return new SimpleDateFormat("EEEE").format(m_Calendar.getTime());
    }
}
