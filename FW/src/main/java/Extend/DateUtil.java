package Extend;//package FW.Utils;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.StringTokenizer;
//import java.util.Vector;
///**
// * Created by Administrator on 2017-05-22.
// */
//public class DateUtil
//        implements Serializable
//{
//    private static final long serialVersionUID = 1L;
//    private static String DATE_DIVISION = "-";
//
//    private static String TIME_DIVISION = ":";
//
//    private static String DATE_TIME_DIVISION = " ";
//    private int m_year;
//    private int m_month;
//    private int m_day;
//    private int m_hour;
//    private int m_minute;
//    private int m_second;
//    private static long millisecondsPerDay = 86400000L;
//
//    public static void main(String[] args)
//    {
//        Assert.assertEquals(dateIntAddYear(5, 20130101), 20180101);
//        Assert.assertEquals(dateIntAddYear(-5, 20130101), 20080101);
//
//        Assert.assertEquals(dateIntAddYear(1, 20120229), 20130228);
//        Assert.assertEquals(dateIntAddYear(-1, 20120229), 20110228);
//
//        Assert.assertEquals(dateIntAddYear(1, 20110228), 20120228);
//        Assert.assertEquals(dateIntAddYear(-1, 20130228), 20120228);
//
//        Assert.assertEquals(dateIntAddMonth(5, 20130101), 20130601);
//        Assert.assertEquals(dateIntAddMonth(-5, 20130601), 20130101);
//
//        Assert.assertEquals(dateIntAddMonth(1, 20131201), 20140101);
//        Assert.assertEquals(dateIntAddMonth(-1, 20140101), 20131201);
//
//        Assert.assertEquals(dateIntAddMonth(5, 20131101), 20140401);
//        Assert.assertEquals(dateIntAddMonth(-5, 20140401), 20131101);
//
//        Assert.assertEquals(dateIntAddMonth(12, 20131101), 20141101);
//        Assert.assertEquals(dateIntAddMonth(-12, 20141101), 20131101);
//
//        Assert.assertEquals(dateIntAddMonth(24, 20131101), 20151101);
//        Assert.assertEquals(dateIntAddMonth(-24, 20141101), 20121101);
//
//        Assert.assertEquals(dateIntAddMonth(24, 20130101), 20150101);
//        Assert.assertEquals(dateIntAddMonth(-24, 20140101), 20120101);
//
//        Assert.assertEquals(dateIntAddMonth(25, 20121201), 20150101);
//        Assert.assertEquals(dateIntAddMonth(-25, 20140101), 20111201);
//
//        Assert.assertEquals(dateIntAddMonth(1, 20120131), 20120229);
//        Assert.assertEquals(dateIntAddMonth(-1, 20120330), 20120229);
//
//        Assert.assertEquals(dateIntAddMonth(1, 20120229), 20120329);
//        Assert.assertEquals(dateIntAddMonth(-1, 20120229), 20120129);
//
//        Assert.assertEquals(dateIntAddDay(5, 20130101), 20130106);
//        Assert.assertEquals(dateIntAddDay(-5, 20130106), 20130101);
//
//        Assert.assertEquals(dateIntAddDay(5, 20130127), 20130201);
//        Assert.assertEquals(dateIntAddDay(5, 20130227), 20130304);
//        Assert.assertEquals(dateIntAddDay(5, 20130327), 20130401);
//        Assert.assertEquals(dateIntAddDay(5, 20130427), 20130502);
//        Assert.assertEquals(dateIntAddDay(5, 20130527), 20130601);
//        Assert.assertEquals(dateIntAddDay(5, 20130627), 20130702);
//        Assert.assertEquals(dateIntAddDay(5, 20130727), 20130801);
//        Assert.assertEquals(dateIntAddDay(5, 20130827), 20130901);
//        Assert.assertEquals(dateIntAddDay(5, 20130927), 20131002);
//        Assert.assertEquals(dateIntAddDay(5, 20131027), 20131101);
//        Assert.assertEquals(dateIntAddDay(5, 20131127), 20131202);
//        Assert.assertEquals(dateIntAddDay(5, 20131227), 20140101);
//
//        Assert.assertEquals(dateIntAddDay(-5, 20130201), 20130127);
//        Assert.assertEquals(dateIntAddDay(-5, 20130304), 20130227);
//        Assert.assertEquals(dateIntAddDay(-5, 20130401), 20130327);
//        Assert.assertEquals(dateIntAddDay(-5, 20130502), 20130427);
//        Assert.assertEquals(dateIntAddDay(-5, 20130601), 20130527);
//        Assert.assertEquals(dateIntAddDay(-5, 20130702), 20130627);
//        Assert.assertEquals(dateIntAddDay(-5, 20130801), 20130727);
//        Assert.assertEquals(dateIntAddDay(-5, 20130901), 20130827);
//        Assert.assertEquals(dateIntAddDay(-5, 20131002), 20130927);
//        Assert.assertEquals(dateIntAddDay(-5, 20131101), 20131027);
//        Assert.assertEquals(dateIntAddDay(-5, 20131202), 20131127);
//        Assert.assertEquals(dateIntAddDay(-5, 20140101), 20131227);
//
//        Assert.assertEquals(dateIntAddDay(365, 20130101), 20140101);
//        Assert.assertEquals(dateIntAddDay(-365, 20140101), 20130101);
//        Assert.assertEquals(dateIntAddDay(366, 20120101), 20130101);
//        Assert.assertEquals(dateIntAddDay(-366, 20130101), 20120101);
//
//        Assert.assertEquals(dateIntAddDay(731, 20120101), 20140101);
//        Assert.assertEquals(dateIntAddDay(-731, 20130101), 20110101);
//        try
//        {
//            System.out.println(format("2009-12-04", "yy-dd-MM"));
//            java.util.Date date2 = new java.util.Date();
//            DebugUtil.debug(date2.toString());
//            DebugUtil.debug(Integer.valueOf("Sun Jan 04 00:00:00 CST 2009".indexOf(" ")));
//
//            String sDate = "2007-01-01 12:40:30";
//
//            java.util.Date date = getDate(sDate, "yyyy-MM-dd HH:mm:ss");
//
//            DebugUtil.debug(getCurrDate());
//            DebugUtil.debug(getCurrTime());
//            DebugUtil.debug(getCurrTimeStr("yyyy-MM-dd HH.mm.ss"));
//
//            DebugUtil.debug(format(date, "yyyy-MM-dd HH:mm:ss"));
//            DebugUtil.debug(format(date, "yyyy-MM-dd"));
//            DebugUtil.debug(format(date, "yy-MM-dd"));
//            DebugUtil.debug(format(date, "yyyyMMdd"));
//            DebugUtil.debug(format(date, "yyMMdd"));
//            DebugUtil.debug(format(date, "MM-DD"));
//            DebugUtil.debug(format(date, "MMDD"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public DateUtil()
//    {
//        Calendar now = Calendar.getInstance();
//        this.m_year = now.get(1);
//        this.m_month = (now.get(2) + 1);
//        this.m_day = now.get(5);
//        this.m_hour = now.get(11);
//        this.m_minute = now.get(12);
//        this.m_second = now.get(13);
//    }
//
//    public DateUtil(int year, int month, int day, int hour, int minute, int second)
//            throws Throwable
//    {
//        if (!isValid(year, month, day, hour, minute, second)) {
//            throw new Exception("DateUtil(" + year + "," + month + "," + day + "," + hour + "," + minute + "," + second + "):无效的日期格式");
//        }
//
//        this.m_year = year;
//        this.m_month = month;
//        this.m_day = day;
//        this.m_hour = hour;
//        this.m_minute = minute;
//        this.m_second = second;
//    }
//
//    public DateUtil(DateUtil dateUtil)
//            throws Throwable
//    {
//        if (dateUtil == null) {
//            throw new Exception("DateUtil(" + dateUtil + "):无效参数!");
//        }
//        if (!dateUtil.isValid()) {
//            throw new Exception("DateUtil(" + dateUtil + "):无效参数!");
//        }
//        this.m_year = dateUtil.m_year;
//        this.m_month = dateUtil.m_month;
//        this.m_day = dateUtil.m_day;
//        this.m_hour = dateUtil.m_hour;
//        this.m_minute = dateUtil.m_minute;
//        this.m_second = dateUtil.m_second;
//    }
//
//    public DateUtil(java.util.Date date)
//            throws Throwable
//    {
//        String s = getStringValueNew(date);
//        int[] r = getDateFields(s);
//        this.m_year = r[0];
//        this.m_month = r[1];
//        this.m_day = r[2];
//        this.m_hour = r[3];
//        this.m_minute = r[4];
//        this.m_second = r[5];
//        if (!isValid(this.m_year, this.m_month, this.m_day, this.m_hour, this.m_minute, this.m_second))
//            throw new Exception("DateUtil(" + date.toString() + "):无效的日期格式");
//    }
//
//    public DateUtil(String date)
//            throws Throwable
//    {
//        int[] r = getDateFields(date);
//        this.m_year = r[0];
//        this.m_month = r[1];
//        this.m_day = r[2];
//        this.m_hour = r[3];
//        this.m_minute = r[4];
//        this.m_second = r[5];
//        if (!isValid(this.m_year, this.m_month, this.m_day, this.m_hour, this.m_minute, this.m_second))
//            throw new Exception("DateUtil(" + date + "):无效的日期格式");
//    }
//
//    public String toString()
//    {
//        return getFullString();
//    }
//
//    public boolean equals(Object v)
//    {
//        if (v == null) {
//            return false;
//        }
//        if (!(v instanceof DateUtil)) {
//            return false;
//        }
//        DateUtil d = (DateUtil)v;
//        return (this.m_year == d.m_year) && (this.m_month == d.m_month) && (this.m_day == d.m_day) && (this.m_hour == d.m_hour) && (this.m_minute == d.m_minute) && (this.m_second == d.m_second);
//    }
//
//    public void setDate(Object v)
//            throws Throwable
//    {
//        if (v == null)
//            return;
//        if (((v instanceof String)) || ((v instanceof java.util.Date)))
//        {
//            String s;
//            if ((v instanceof java.util.Date))
//                s = getStringValueNew((java.util.Date)v);
//            else {
//                s = v.toString();
//            }
//            int[] r = getDateFields(s);
//            this.m_year = r[0];
//            this.m_month = r[1];
//            this.m_day = r[2];
//            this.m_hour = r[3];
//            this.m_minute = r[4];
//            this.m_second = r[5];
//        } else {
//            throw new Exception("SetDate(" + v + "):无效的日期值");
//        }
//    }
//
//    public DateUtil getAdvanceDays(int days)
//            throws Throwable
//    {
//        DateUtil r = new DateUtil(this);
//        r.advanceDays(days);
//        return r;
//    }
//
//    public void advanceDays(int days)
//    {
//        fromJulian(toJulian() + days);
//    }
//
//    private void fromJulian(int j)
//    {
//        int ja = j;
//        int JGREG = 2299161;
//        if (j >= JGREG) {
//            int jalpha = (int)((j - 1867216 - 0.25D) / 36524.25D);
//            ja += 1 + jalpha - (int)(0.25D * jalpha);
//        }
//        int jb = ja + 1524;
//        int jc = (int)(6680.0D + (jb - 2439870 - 122.09999999999999D) / 365.25D);
//        int jd = (int)(365 * jc + 0.25D * jc);
//        int je = (int)((jb - jd) / 30.600100000000001D);
//        this.m_day = (jb - jd - (int)(30.600100000000001D * je));
//        this.m_month = (je - 1);
//        if (this.m_month > 12) {
//            this.m_month -= 12;
//        }
//        this.m_year = (jc - 4715);
//        if (this.m_month > 2) {
//            this.m_year -= 1;
//        }
//        if (this.m_year <= 0)
//            this.m_year -= 1;
//    }
//
//    private static int[] getDateFields(String t)
//            throws Throwable
//    {
//        String[] dayAndtime = StringUtil.split(t, DATE_TIME_DIVISION);
//        if ((dayAndtime.length != 2) && (dayAndtime.length != 1)) {
//            throw new Exception("DateUtil.getDateFields(" + t + "):无效的日期格式");
//        }
//        int[] dates = getIntsFromStr(dayAndtime[0], DATE_DIVISION);
//        if (dates.length != 3) {
//            throw new Exception("DateUtil.getDateFields(" + t + "):无效的日期格式");
//        }
//        int[] times = new int[3];
//        if (dayAndtime.length == 2) {
//            int[] ts = getIntsFromStr(dayAndtime[1], TIME_DIVISION);
//            if (ts.length > 3) {
//                throw new Exception("DateUtil.getDateFields(" + t + "):无效的日期格式");
//            }
//            if (ts.length == 1) {
//                times[0] = ts[0];
//                times[1] = 0;
//                times[2] = 0;
//            }
//            if (ts.length == 2) {
//                times[0] = ts[0];
//                times[1] = ts[1];
//                times[2] = 0;
//            }
//            if (ts.length == 3) {
//                times[0] = ts[0];
//                times[1] = ts[1];
//                times[2] = ts[2];
//            }
//        } else {
//            times[0] = 0;
//            times[1] = 0;
//            times[2] = 0;
//        }
//        int[] r = new int[6];
//        r[0] = dates[0];
//        r[1] = dates[1];
//        r[2] = dates[2];
//        r[3] = times[0];
//        r[4] = times[1];
//        r[5] = times[2];
//        return r;
//    }
//
//    public int getDateInt()
//    {
//        Calendar cale = Calendar.getInstance();
//        cale.set(this.m_year, this.m_month, this.m_day);
//        int result = cale.get(1) * 10000 + (cale.get(2) + 1) * 100 + cale.get(5);
//        return result;
//    }
//
//    public String getDateString()
//    {
//        String affMonth = "0" + String.valueOf(this.m_month);
//        String affDay = "0" + String.valueOf(this.m_day);
//        return String.valueOf(this.m_year) + DATE_DIVISION + affMonth + DATE_DIVISION + affDay;
//    }
//
//    public String getDateStringNew()
//    {
//        String affMonth = "0" + String.valueOf(this.m_month);
//        String affDay = "0" + String.valueOf(this.m_day);
//        return String.valueOf(this.m_year) + affMonth + affDay;
//    }
//
//    public String getFullString()
//    {
//        String affMonth = "0" + String.valueOf(this.m_month);
//        String affDay = "0" + String.valueOf(this.m_day);
//        String affHour = "0" + String.valueOf(this.m_hour);
//        String affMinute = "0" + String.valueOf(this.m_minute);
//        String affSecond = "0" + String.valueOf(this.m_second);
//        return String.valueOf(this.m_year) + DATE_DIVISION + affMonth + DATE_DIVISION + affDay + DATE_TIME_DIVISION + affHour + TIME_DIVISION + affMinute + TIME_DIVISION + affSecond;
//    }
//
//    public static String getStringValueNew(java.util.Date date)
//            throws Throwable
//    {
//        return getDateFormatStr(date, "yyyy-MM-dd HH:mm:ss");
//    }
//
//    public int getYear()
//    {
//        return this.m_year;
//    }
//
//    public int getQuarter()
//    {
//        return (this.m_month + 2) / 3;
//    }
//
//    public int getMonth()
//    {
//        return this.m_month;
//    }
//
//    public int getDay()
//    {
//        return this.m_day;
//    }
//
//    public int getHour()
//    {
//        return this.m_hour;
//    }
//
//    public int getMinute()
//    {
//        return this.m_minute;
//    }
//
//    public int getSecond()
//    {
//        return this.m_second;
//    }
//
//    private static int[] getIntsFromStr(String str, String token)
//    {
//        StringTokenizer st = new StringTokenizer(str, token);
//        Vector v = new Vector();
//        while (st.hasMoreTokens()) v.add(st.nextToken());
//        int[] ints = new int[v.size()];
//        for (int i = 0; i < v.size(); i++) {
//            ints[i] = Double.valueOf((String)v.get(i)).intValue();
//        }
//        return ints;
//    }
//
//    public DateUtil getRealMonthEnd()
//            throws Throwable
//    {
//        DateUtil r = getMonthStart();
//        if ((r.getMonth() == 1) || (r.getMonth() == 3) || (r.getMonth() == 5) || (r.getMonth() == 7) || (r.getMonth() == 8) || (r.getMonth() == 10) || (r.getMonth() == 12))
//        {
//            r.advanceDays(30);
//        }
//        if ((r.getMonth() == 4) || (r.getMonth() == 6) || (r.getMonth() == 9) || (r.getMonth() == 11)) {
//            r.advanceDays(29);
//        }
//        if (r.getMonth() == 2) {
//            int year = r.getYear();
//            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
//                r.advanceDays(28);
//            else {
//                r.advanceDays(27);
//            }
//        }
//        return r;
//    }
//
//    public DateUtil getRealMonthStart()
//            throws Throwable
//    {
//        return new DateUtil(this.m_year, this.m_month, 1, 0, 0, 0);
//    }
//
//    public DateUtil getMonthStart()
//            throws Throwable
//    {
//        int m_year_last = this.m_year;
//        int m_month_last = this.m_month - 1;
//        int m_day_last = this.m_day;
//        if (m_month_last == 0) {
//            if (m_day_last >= 2006) {
//                m_month_last = this.m_month;
//            } else {
//                m_month_last = 12;
//                m_year_last = this.m_year - 1;
//            }
//        }
//        return new DateUtil(m_year_last, m_month_last, 1, 0, 0, 0);
//    }
//
//    public DateUtil getMonthStartEx()
//            throws Throwable
//    {
//        int m_year_last = this.m_year;
//        int m_month_last = this.m_month - 1;
//        int m_day_last = this.m_day;
//        if (m_day_last >= 1) {
//            m_month_last = this.m_month;
//        }
//        if (m_month_last == 0) {
//            m_month_last = 12;
//            m_year_last = this.m_year - 1;
//        }
//        return new DateUtil(m_year_last, m_month_last, 1, 0, 0, 0);
//    }
//
//    public java.sql.Date getSqlDate()
//    {
//        return java.sql.Date.valueOf(getDateString());
//    }
//
//    public String getTimeString()
//    {
//        String affHour = "0" + String.valueOf(this.m_hour);
//        String affMinute = "0" + String.valueOf(this.m_minute);
//        String affSecond = "0" + String.valueOf(this.m_second);
//        return affHour + TIME_DIVISION + affMinute + TIME_DIVISION + affSecond;
//    }
//
//    public int getWeekDay()
//            throws Throwable
//    {
//        DateUtil std = new DateUtil("2000-01-03");
//        int days = daysBetween(std);
//        int m = days % 7;
//        if (m >= 0) {
//            return m + 1;
//        }
//        return m + 8;
//    }
//
//    public int getWeekDay(int firstday)
//            throws Throwable
//    {
//        DateUtil std = new DateUtil("2000-01-03");
//        int days = daysBetween(std);
//        int m = (days + firstday) % 7;
//        return m + 1;
//    }
//
//    public DateUtil getWeekEnd()
//            throws Throwable
//    {
//        DateUtil r = getWeekStart();
//        r.advanceDays(7);
//        return r;
//    }
//
//    public DateUtil getWeekStart()
//            throws Throwable
//    {
//        DateUtil std = new DateUtil("2000-01-03");
//        int days = daysBetween(std);
//        int m = days % 7;
//        DateUtil r;
//        if (m >= 0)
//            r = getAdvanceDays(-m);
//        else {
//            r = getAdvanceDays(-m - 7);
//        }
//        return r;
//    }
//
//    public boolean isValid()
//    {
//        return isValid(this.m_year, this.m_month, this.m_day, this.m_hour, this.m_minute, this.m_second);
//    }
//
//    public static boolean isValid(int year, int month, int day, int hour, int minute, int second)
//    {
//        if ((hour < 0) || (hour > 23) || (minute < 0) || (minute > 59) || (second < 0) || (second > 59)) {
//            return false;
//        }
//        if ((year < 0) || (month < 1) || (month > 12) || (day < 1) || (day > 31)) {
//            return false;
//        }
//        switch (month) {
//            case 1:
//            case 3:
//            case 5:
//            case 7:
//            case 8:
//            case 10:
//            case 12:
//                return true;
//            case 4:
//            case 6:
//            case 9:
//            case 11:
//                return day <= 30;
//            case 2:
//                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
//                    return day <= 29;
//                }
//                return day <= 28;
//        }
//        return false;
//    }
//
//    public static boolean isValid(String sDate)
//            throws Throwable
//    {
//        int[] r;
//        try
//        {
//            r = getDateFields(sDate);
//        } catch (Throwable e) {
//            return false;
//        }
//        return isValid(r[0], r[1], r[2], r[3], r[4], r[5]);
//    }
//
//    private int toJulian()
//    {
//        int jy = this.m_year;
//        if (this.m_year < 0) {
//            jy++;
//        }
//        int jm = this.m_month;
//        if (this.m_month > 2) {
//            jm++;
//        } else {
//            jy--;
//            jm += 13;
//        }
//        int jul = (int)(Math.floor(365.25D * jy) + Math.floor(30.600100000000001D * jm) + this.m_day + 1720995.0D);
//
//        int IGREG = 588829;
//        if (this.m_day + 31 * (this.m_month + 12 * this.m_year) >= IGREG) {
//            int ja = (int)(0.01D * jy);
//            jul += 2 - ja + (int)(0.25D * ja);
//        }
//        return jul;
//    }
//
//    public static String viewDate(String d)
//            throws Throwable
//    {
//        try
//        {
//            DateUtil date = new DateUtil(d);
//            return date.getDateString(); } catch (Throwable e) {
//        }
//        return "";
//    }
//
//    public int daysBetween(DateUtil date)
//    {
//        return toJulian() - date.toJulian();
//    }
//
//    public static int between(DateUtil sd, DateUtil ed)
//    {
//        return ed.toJulian() - sd.toJulian();
//    }
//
//    public static int between(java.util.Date sd, java.util.Date ed)
//            throws Throwable
//    {
//        DateUtil sd1 = new DateUtil(sd);
//        DateUtil ed1 = new DateUtil(ed);
//        return ed1.toJulian() - sd1.toJulian();
//    }
//
//    public static int between(String sd, String ed)
//            throws Throwable
//    {
//        DateUtil sd1 = new DateUtil(sd);
//        DateUtil ed1 = new DateUtil(ed);
//        return ed1.toJulian() - sd1.toJulian();
//    }
//
//    public static boolean after(String curPeriod, String period)
//    {
//        if (compare(curPeriod, period) == -1) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean before(String curPeriod, String period)
//    {
//        return !after(curPeriod, period);
//    }
//
//    public static int compare(String curPeriod, String period)
//    {
//        java.util.Date date1 = getDate(curPeriod, "yyyy-MM-dd");
//        java.util.Date date2 = getDate(period, "yyyy-MM-dd");
//        if (date1.before(date2))
//            return 1;
//        if (date1.after(date2))
//            return -1;
//        return 0;
//    }
//
//    public static Timestamp getCurrTime()
//    {
//        return new Timestamp(System.currentTimeMillis());
//    }
//
//    public static java.sql.Date getCurrDate()
//    {
//        return new java.sql.Date(getCurrTime().getTime());
//    }
//
//    public static int getDateInt(java.util.Date date)
//    {
//        Calendar cale = Calendar.getInstance();
//        cale.setTime(date);
//        int result = cale.get(1) * 10000 + (cale.get(2) + 1) * 100 + cale.get(5);
//        return result;
//    }
//
//    public static java.util.Date getDate(String timeStr, String dateStyle)
//    {
//        try
//        {
//            return getDateParse(timeStr, dateStyle);
//        } catch (Throwable ex) {
//            throw new IllegalArgumentException("报错: " + ex.getMessage());
//        }
//    }
//
//    public static String format(String timeStr, String dateStyle)
//    {
//        String style = dateStyle == null ? "yyyy-MM-dd" : dateStyle;
//        java.util.Date date = getDate(timeStr, style);
//        return format(date, style);
//    }
//
//    public static String format(java.util.Date date, String sFormat)
//    {
//        sFormat = sFormat == null ? "yyyy-MM-dd" : sFormat;
//
//        if ("YY-MM-DD".equalsIgnoreCase(sFormat))
//            sFormat = "yy-MM-dd";
//        else if ("YYMMDD".equalsIgnoreCase(sFormat))
//            sFormat = "yyMMdd";
//        else if ("MM-DD".equalsIgnoreCase(sFormat))
//            sFormat = "MM-dd";
//        else if ("MMDD".equalsIgnoreCase(sFormat))
//            sFormat = "MMdd";
//        else if ("YYYY-MM".equalsIgnoreCase(sFormat))
//            sFormat = "yyyy-MM";
//        else if ("YYYYMM".equalsIgnoreCase(sFormat))
//            sFormat = "yyyyMM";
//        else if ("YYYY-MM-DD".equalsIgnoreCase(sFormat))
//            sFormat = "yyyy-MM-dd";
//        else if ("YYYY-MM-DD HH".equalsIgnoreCase(sFormat))
//            sFormat = "yyyy-MM-dd HH";
//        else if ("YYYY-MM-DD HH:MM".equalsIgnoreCase(sFormat))
//            sFormat = "yyyy-MM-dd HH:mm";
//        else if ("YYYY-MM-DD HH:MM:SS".equalsIgnoreCase(sFormat))
//            sFormat = "yyyy-MM-dd HH:mm:ss";
//        else if ("YYYY".equalsIgnoreCase(sFormat))
//            sFormat = "yyyy";
//        else if ("YYYYMMDD".equalsIgnoreCase(sFormat))
//            sFormat = "yyyyMMdd";
//        else if ("YYYYMMDDMMSS".equalsIgnoreCase(sFormat))
//            sFormat = "yyyyMMddmmss";
//        else if ("YYYYMMDDHH".equalsIgnoreCase(sFormat))
//            sFormat = "yyyyMMddHH";
//        else if ("YYYYMMDDHHSS".equalsIgnoreCase(sFormat))
//            sFormat = "yyyyMMddHHSS";
//        else if ("YYYYMMDDHHMM".equalsIgnoreCase(sFormat))
//            sFormat = "yyyyMMddHHmm";
//        else if ("YYYYMMDDHHMMSS".equalsIgnoreCase(sFormat))
//            sFormat = "yyyyMMddHHmmss";
//        else if ("YY-MM".equalsIgnoreCase(sFormat))
//            sFormat = "yy-MM";
//        else if ("YYMM".equalsIgnoreCase(sFormat)) {
//            sFormat = "yyMM";
//        }
//
//        return getDateFormatStr(date, sFormat);
//    }
//
//    private static String getDateFormatStr(java.util.Date date, String format)
//    {
//        return new SimpleDateFormat(format).format(date);
//    }
//
//    private static java.util.Date getDateParse(String sDate, String format) throws Throwable
//    {
//        if (sDate.endsWith("Z")) {
//            sDate = StringUtil.replaceAll(sDate.substring(0, sDate.length() - 1), "T", " ");
//            java.util.Date d = new SimpleDateFormat(format).parse(sDate);
//            return dateAdd(d, 1);
//        }
//        return new SimpleDateFormat(format).parse(sDate);
//    }
//
//    public static SimpleDateFormat getDateFormat(String format)
//    {
//        return new SimpleDateFormat(format);
//    }
//
//    public static String format2(java.util.Date date, SimpleDateFormat sf)
//    {
//        return sf.format(date);
//    }
//
//    public static String format(Object date, String dateStyle)
//    {
//        if (date == null) {
//            return null;
//        }
//
//        if ((date instanceof java.util.Date)) {
//            return format((java.util.Date)date, dateStyle);
//        }
//        String s = date.toString();
//        if (s.length() == 0) {
//            return null;
//        }
//        return format(s, dateStyle);
//    }
//
//    public static String getCurrTimeStr()
//            throws ParseException
//    {
//        return getCurrTimeStr(null);
//    }
//
//    public static String getCurrTimeStr(String dateStyle)
//            throws ParseException
//    {
//        if (dateStyle == null)
//            dateStyle = "yyyy-MM-dd HH.mm.ss";
//        return format(new java.util.Date(), dateStyle);
//    }
//
//    public static Integer[] getMonthBetween(Integer lFirstMonth, Integer lStartMonth)
//    {
//        if (lFirstMonth == lStartMonth) {
//            return new Integer[0];
//        }
//        Integer lLFirstMonth = FMonthToLMonth(lFirstMonth);
//        Integer lLStartMonth = FMonthToLMonth(lStartMonth);
//
//        Integer[] lTempArray = new Integer[lLStartMonth.intValue() - lLFirstMonth.intValue()];
//
//        for (int lLoop = lLFirstMonth.intValue(); lLoop < lLStartMonth.intValue(); lLoop++)
//            lTempArray[(lLoop - lLFirstMonth.intValue())] = LMonthToFMonth(lLoop);
//        return lTempArray;
//    }
//
//    public static Integer LMonthToFMonth(int lLMonth)
//    {
//        return Integer.valueOf(lLMonth / 12 * 100 + lLMonth % 12 + 1);
//    }
//
//    public static Integer FMonthToLMonth(Integer lFMonth)
//    {
//        return Integer.valueOf(lFMonth.intValue() / 100 * 12 + lFMonth.intValue() % 100 - 1);
//    }
//
//    public static java.util.Date getDate(String value) throws Throwable {
//        java.util.Date d = null;
//
//        if (value == null) {
//            return d;
//        }
//        if ((value.startsWith("'")) && (value.endsWith("'")) && (value.length() > 1)) {
//            value = value.substring(1, value.length() - 2);
//        }
//        if (value.length() > 10) {
//            if (value.length() < 17) {
//                value = value.substring(0, 10);
//                d = getDateParse(value, "yyyy-MM-dd");
//            }
//            else if (value.indexOf(" ") == 3) {
//                d = getDateParse(value, "EEE MMM dd HH:mm:ss zzz yyyy");
//            } else {
//                d = getDateParse(value, "yyyy-MM-dd HH:mm:ss");
//            }
//        } else if ((value.length() == 7) && (value.lastIndexOf("-") == 4))
//            d = getDateParse(value, "yyyy-MM");
//        else if (value.length() == 8)
//            d = getDateParse(value, "yyyyMMdd");
//        else if (!"null".equalsIgnoreCase(value.trim())) {
//            d = getDateParse(value, "yyyy-MM-dd");
//        }
//
//        return d;
//    }
//
//    public static int getIntervalMonths(java.sql.Date startDate, java.sql.Date endDate)
//    {
//        Calendar start = Calendar.getInstance();
//        start.setTime(startDate);
//        Calendar end = Calendar.getInstance();
//        end.setTime(endDate);
//        int startMonth = start.get(2);
//        int startYear = start.get(1);
//        int endMonth = end.get(2);
//        int endYear = end.get(1);
//        int interval = endYear * 12 + endMonth - (startYear * 12 + startMonth);
//        return interval;
//    }
//
//    public static int getPreviousMonth(int nCurMonth, int nCycle)
//    {
//        return LMonthToFMonth(FMonthToLMonth(Integer.valueOf(nCurMonth)).intValue() - nCycle).intValue();
//    }
//
//    public static int getNextMonth(int nCurMonth, int nCycle)
//    {
//        return LMonthToFMonth(FMonthToLMonth(Integer.valueOf(nCurMonth)).intValue() + nCycle).intValue();
//    }
//
//    public static java.util.Date dateAdd(String interval, int num, java.util.Date date)
//    {
//        Calendar start = Calendar.getInstance();
//        start.setTime(date);
//        int field = 0;
//        if ((interval.equalsIgnoreCase("d")) || (interval.equalsIgnoreCase("dd"))) {
//            field = 5;
//        } else if (interval.equalsIgnoreCase("s")) {
//            field = 13;
//        } else if (interval.equalsIgnoreCase("n")) {
//            field = 12;
//        } else if (interval.equalsIgnoreCase("h")) {
//            field = 10;
//        } else if (interval.equalsIgnoreCase("w")) {
//            field = 7;
//        } else if (interval.equalsIgnoreCase("ww")) {
//            field = 3;
//        } else if (interval.equalsIgnoreCase("y")) {
//            field = 6;
//        } else if ((interval.equalsIgnoreCase("m")) || (interval.equalsIgnoreCase("mm"))) {
//            field = 2;
//        } else if (interval.equalsIgnoreCase("yyyy")) {
//            field = 1;
//        } else if (interval.equalsIgnoreCase("q")) {
//            field = 2;
//            num *= 3;
//        } else {
//            DebugUtil.error("dateAdd" + interval + "not impl");
//        }
//        start.add(field, num);
//        return start.getTime();
//    }
//
//    public static int dateIntAdd(String interval, int num, int date)
//    {
//        if ((interval.equalsIgnoreCase("d")) || (interval.equalsIgnoreCase("dd")))
//            return dateIntAddDay(num, date);
//        if ((interval.equalsIgnoreCase("m")) || (interval.equalsIgnoreCase("mm")))
//            return dateIntAddMonth(num, date);
//        if (interval.equalsIgnoreCase("yyyy"))
//            return dateIntAddYear(num, date);
//        if (interval.equalsIgnoreCase("q")) {
//            return dateIntAddMonth(num * 3, date);
//        }
//        throw new RuntimeException("dateIntAdd" + interval + "not impl.支持天:d,dd,月:m,mm,年:yyyy,旬:q.");
//    }
//
//    public static int dateIntAddYear(int deltaYear, int date)
//    {
//        int oldDay = date % 100;
//        int oldMonth = date / 100 % 100;
//        int oldYear = date / 10000;
//        int newYear = oldYear + deltaYear;
//        if (oldDay > getDateCountInMonth(newYear, oldMonth)) {
//            oldDay = getDateCountInMonth(newYear, oldMonth);
//        }
//        return newYear * 10000 + oldMonth * 100 + oldDay;
//    }
//
//    public static int dateIntAddMonth(int deltaMonth, int date)
//    {
//        int oldMonth = date / 100 % 100;
//        int tmpMonth = oldMonth + deltaMonth;
//        int deltaYear = 0;
//        int newMonth;
//        if (tmpMonth <= 0) {
//            while (tmpMonth <= 0) {
//                deltaYear--;
//                tmpMonth += 12;
//            }
//            newMonth = tmpMonth;
//        } else {
//            while (tmpMonth > 12) {
//                deltaYear++;
//                tmpMonth -= 12;
//            }
//            newMonth = tmpMonth;
//        }
//        int newYear = date / 10000 + deltaYear;
//        int oldDay = date % 100;
//        if (oldDay > getDateCountInMonth(newYear, newMonth)) {
//            oldDay = getDateCountInMonth(newYear, newMonth);
//        }
//        return newYear * 10000 + newMonth * 100 + oldDay;
//    }
//
//    public static int dateIntAddDay(int deltaDay, int date)
//    {
//        int oldYear = date / 10000;
//        int oldMonth = date / 100 % 100;
//        int oldDay = date % 100;
//        int tmpDay = oldDay + deltaDay;
//        if (tmpDay <= 0) {
//            while (tmpDay <= 0) {
//                oldMonth--;
//                if (oldMonth == 0) {
//                    oldYear--;
//                    oldMonth = 12;
//                }
//                tmpDay += getDateCountInMonth(oldYear, oldMonth);
//            }
//            return oldYear * 10000 + oldMonth * 100 + tmpDay;
//        }
//        while (tmpDay > getDateCountInMonth(oldYear, oldMonth)) {
//            tmpDay -= getDateCountInMonth(oldYear, oldMonth);
//            oldMonth++;
//            if (oldMonth == 13) {
//                oldYear++;
//                oldMonth = 1;
//            }
//        }
//        return oldYear * 10000 + oldMonth * 100 + tmpDay;
//    }
//
//    private static int getDateCountInMonth(int year, int month)
//    {
//        switch (month) {
//            case 1:
//            case 3:
//            case 5:
//            case 7:
//            case 8:
//            case 10:
//            case 12:
//                return 31;
//            case 4:
//            case 6:
//            case 9:
//            case 11:
//                return 30;
//            case 2:
//                return (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0)) ? 29 : 28;
//        }
//        throw new RuntimeException("地球上不存在" + month + "月.");
//    }
//
//    public static java.util.Date getDate(int year, int month, int date)
//    {
//        Calendar cale = Calendar.getInstance();
//        cale.clear();
//        cale.set(year, month - 1, date);
//        return cale.getTime();
//    }
//
//    public static java.util.Date getDate(int year, int month, int date, int hour, int min, int sec)
//    {
//        return getDate(getDate(year, month, date), hour, min, sec);
//    }
//
//    public static java.util.Date getDate(java.util.Date d, int hour, int min, int sec)
//    {
//        Calendar c = Calendar.getInstance();
//        c.setTime(d);
//        c.set(11, hour);
//        c.set(12, min);
//        c.set(13, sec);
//        return c.getTime();
//    }
//
//    public static java.util.Date getLastDateInMonth(int year, int month)
//    {
//        Calendar cale = Calendar.getInstance();
//        cale.clear();
//        cale.set(1, year);
//        cale.set(2, month - 1);
//        cale.set(5, cale.getActualMaximum(5));
//        return cale.getTime();
//    }
//
//    public static java.util.Date getFirstDateInMonth(int year, int month)
//    {
//        Calendar cale = Calendar.getInstance();
//        cale.clear();
//        cale.set(1, year);
//        cale.set(2, month - 1);
//        cale.set(5, cale.getActualMinimum(5));
//        return cale.getTime();
//    }
//
//    public static java.util.Date dateAdd(java.util.Date date, int days)
//    {
//        java.util.Date result = date;
//        long time = date.getTime();
//        time += millisecondsPerDay * days;
//        result.setTime(time);
//        return result;
//    }
//}
