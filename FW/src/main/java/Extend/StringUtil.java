package Extend;//package FW.Utils;
//
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.StringTokenizer;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.ArrayList;
//import java.lang.reflect.Array;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.Set;
///**
// * Created by Administrator on 2017-05-22.
// */
//public final class StringUtil
//{
//    public static final char DOT = '.';
//    public static final char UNDERSCORE = '_';
//    public static final String EMPTY_STRING = "";
//    public static final String COMMA = ",";
//    public static final String OPEN_PAREN = "(";
//    public static final String CLOSE_PAREN = ")";
//    public static final String STR_TRUE = "True";
//    public static final String STR_FALSE = "False";
//    public static final String STR_EMPTY = "";
//    public static final String STR_INFINITUDE = "∞";
//
//    public static Map<Character, Integer> getCharMaps(String s)
//    {
//        Map map = new HashMap();
//        for (int i = 0; i < s.length(); i++) {
//            Character c = Character.valueOf(s.charAt(i));
//            Integer count = (Integer)map.get(c);
//            map.put(c, Integer.valueOf(count == null ? 1 : count.intValue() + 1));
//        }
//        return map;
//    }
//
//    public static String join(String seperator, String[] strings)
//    {
//        int length = strings.length;
//        if (length == 0)
//            return "";
//        StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
//
//        for (int i = 1; i < length; i++) {
//            buf.append(seperator).append(strings[i]);
//        }
//        return buf.toString();
//    }
//
//    public static String join(String seperator, Iterator<?> objects)
//    {
//        StringBuffer buf = new StringBuffer();
//        buf.append(objects.next());
//        while (objects.hasNext()) {
//            buf.append(seperator).append(objects.next());
//        }
//        return buf.toString();
//    }
//
//    public static String[] add(String[] x, String sep, String[] y)
//    {
//        String[] result = new String[x.length];
//        for (int i = 0; i < x.length; i++) {
//            result[i] = new StringBuilder().append(x[i]).append(sep).append(y[i]).toString();
//        }
//        return result;
//    }
//
//    public static String repeat(String string, int times)
//    {
//        StringBuffer buf = new StringBuffer(string.length() * times);
//        for (int i = 0; i < times; i++)
//            buf.append(string);
//        return buf.toString();
//    }
//
//    public static String[] split(String list, String seperators)
//    {
//        return split(list, seperators, false);
//    }
//
//    public static String[] split(String list, String seperators, boolean include)
//    {
//        list = list == null ? "" : list;
//        StringTokenizer tokens = new StringTokenizer(list, seperators, include);
//        String[] result = new String[tokens.countTokens()];
//        int i = 0;
//        while (tokens.hasMoreTokens()) {
//            result[(i++)] = tokens.nextToken();
//        }
//        return result;
//    }
//
//    public static String[] splitVB(String list, String seperators)
//    {
//        return list.split(seperators, list.length());
//    }
//
//    public static String[] splitExpSeq(String seq, char separator)
//    {
//        if (seq.isEmpty()) {
//            return null;
//        }
//        int length = seq.length();
//        LinkedList listResult = new LinkedList();
//
//        int pos = -1;
//        int deep = 0;
//        String s = null;
//        boolean leftQuFound = false;
//        for (int i = 0; i < length; i++) {
//            char c = seq.charAt(i);
//            if ((c == separator) && (deep == 0)) {
//                s = seq.substring(pos + 1, i);
//                if (!s.isEmpty()) {
//                    listResult.add(s);
//                }
//                pos = i;
//            } else if (c == '(') {
//                deep++;
//            } else if (c == ')') {
//                deep--;
//            } else if (c == '\'') {
//                if (leftQuFound) {
//                    deep--;
//                    leftQuFound = false;
//                } else {
//                    leftQuFound = true;
//                    deep++;
//                }
//            }
//        }
//        if (pos == -1)
//            s = seq;
//        else {
//            s = seq.substring(pos + 1, length);
//        }
//        if (!s.isEmpty()) {
//            listResult.add(s);
//        }
//        return (String[])listResult.toArray(new String[0]);
//    }
//
//    private static ArrayList<String> pSplitEx(String srcStr, String seperators, boolean include, int k)
//    {
//        ArrayList list = new ArrayList();
//
//        String sep = seperators.substring(k, k + 1);
//        StringTokenizer tokens = new StringTokenizer(srcStr, sep, include);
//
//        boolean canAdd = true;
//        while (tokens.hasMoreTokens())
//        {
//            String sonStr = tokens.nextToken();
//            if (k < seperators.length() - 1) {
//                String sep2 = seperators.substring(k + 1, k + 2);
//                ArrayList sonList = pSplitEx(sonStr, sep2, include, k);
//                if (sonList.size() > 0) {
//                    list.addAll(sonList);
//                    canAdd = false;
//                }
//            }
//            if (canAdd) {
//                list.add(sonStr);
//            }
//        }
//        return list;
//    }
//
//    public static String[] splitEx(String srcStr, String seperators, boolean include)
//    {
//        return (String[])pSplitEx(srcStr, seperators, include, 0).toArray(new String[0]);
//    }
//
//    public static String[] splitEx2(String str, String delimiters)
//    {
//        String[] a = null;
//        if (isBlankOrNull(delimiters))
//            a = splitEx(str, "!@#$%^&*()-+=\\/[]{};:'\"<>,.? ", true);
//        else
//            a = splitEx(str, delimiters, true);
//        for (int i = 0; i < a.length; i++) {
//            str = replaceAll(str, new StringBuilder().append("\\").append(a[i]).toString(), new StringBuilder().append("€").append(i).toString());
//        }
//
//        String[] aResult = splitEx(str, delimiters, false);
//        for (int l = 0; l < aResult.length; l++) {
//            for (int i = 0; i < a.length; i++) {
//                aResult[l] = replaceAll(aResult[l], new StringBuilder().append("€").append(i).toString(), a[i]);
//            }
//
//        }
//
//        return aResult;
//    }
//
//    public static String unqualify(String qualifiedName)
//    {
//        return unqualify(qualifiedName, ".");
//    }
//
//    public static String unqualify(String qualifiedName, String seperator)
//    {
//        return qualifiedName.substring(qualifiedName.lastIndexOf(seperator) + 1);
//    }
//
//    public static String qualifier(String qualifiedName)
//    {
//        int loc = qualifiedName.lastIndexOf(".");
//        if (loc < 0) {
//            return "";
//        }
//        return qualifiedName.substring(0, loc);
//    }
//
//    public static String[] suffix(String[] strs, String suffix)
//    {
//        if (suffix == null)
//            return strs;
//        String[] result = new String[strs.length];
//        for (int i = 0; i < strs.length; i++) {
//            result[i] = suffix(strs[i], suffix);
//        }
//        return result;
//    }
//
//    public static String suffix(String str, String suffix)
//    {
//        return suffix == null ? str : new StringBuilder().append(str).append(suffix).toString();
//    }
//
//    public static String[] prefix(String[] strs, String prefix)
//    {
//        if (prefix == null)
//            return strs;
//        String[] result = new String[strs.length];
//        for (int i = 0; i < strs.length; i++) {
//            result[i] = new StringBuilder().append(prefix).append(strs[i]).toString();
//        }
//        return result;
//    }
//
//    public static String root(String qualifiedName)
//    {
//        int loc = qualifiedName.indexOf(".");
//        return loc < 0 ? qualifiedName : qualifiedName.substring(0, loc);
//    }
//
//    public static boolean booleanValue(String tfString)
//    {
//        String trimmed = tfString.trim().toLowerCase();
//        return (trimmed.equals("true")) || (trimmed.equals("t"));
//    }
//
//    public static String toString(Object[] array)
//    {
//        return toString(array, null);
//    }
//
//    public static String toString(Object[] array, String delimiter)
//    {
//        int len = array.length;
//        if (len == 0)
//            return "";
//        StringBuffer buf = new StringBuffer(len * 12);
//        for (int i = 0; i < len - 1; i++) {
//            if (delimiter == null)
//                buf.append(array[i]).append(",");
//            else
//                buf.append(array[i]).append(delimiter);
//        }
//        return buf.append(array[(len - 1)]).toString();
//    }
//
//    public static String[] multiply(String string, Iterator<?> placeholders, Iterator<?> replacements)
//    {
//        String[] result = { string };
//        while (placeholders.hasNext()) {
//            result = multiply(result, (String)placeholders.next(), (String[])replacements.next());
//        }
//        return result;
//    }
//
//    private static String[] multiply(String[] strings, String placeholder, String[] replacements)
//    {
//        String[] results = new String[replacements.length * strings.length];
//        int n = 0;
//        for (int i = 0; i < replacements.length; i++) {
//            for (int j = 0; j < strings.length; j++) {
//                results[(n++)] = replace(strings[j], placeholder, replacements[i]);
//            }
//        }
//        return results;
//    }
//
//    public static String getStrByArray(String[] strs, String delimiter)
//    {
//        StringBuffer buf = new StringBuffer();
//        int count = 0;
//        if (strs != null) {
//            for (int i = 0; i < strs.length; i++)
//                if (!isBlankOrNull(strs[i]))
//                {
//                    if (count > 0)
//                        buf.append(delimiter);
//                    buf.append(strs[i]);
//                    count++;
//                }
//        }
//        return buf.toString();
//    }
//
//    public static String getStrByArray(Object[] objs, String delimiter)
//    {
//        StringBuffer buf = new StringBuffer();
//        int count = 0;
//        if (objs != null) {
//            for (int i = 0; i < objs.length; i++)
//                if (!isBlankOrNull(objs[i]))
//                {
//                    if (count > 0)
//                        buf.append(delimiter);
//                    buf.append(objs[i]);
//                    count++;
//                }
//        }
//        return buf.toString();
//    }
//
//    public static String getExtName(String fileName)
//    {
//        String[] names = split(fileName, "\\.");
//        String extName = "";
//        if ((names != null) && (names.length > 0))
//            extName = names[(names.length - 1)];
//        return extName;
//    }
//
//    public static String[] splitHasNVL(String str, String div)
//    {
//        return splitHasNVL(str, div, false);
//    }
//
//    public static String[] splitHasNVL(String str, String div, boolean ignored)
//    {
//        ArrayList result = new ArrayList();
//        String temp = str;
//        while ((temp != null) && (temp.indexOf(div) != -1)) {
//            String value = temp.substring(0, temp.indexOf(div));
//
//            result.add(value);
//            temp = temp.substring(temp.indexOf(div) + 1, temp.length());
//        }
//
//        if (!ignored)
//            result.add(temp);
//        else if (!isBlankOrNull(temp)) {
//            result.add(temp);
//        }
//
//        String[] datas = new String[result.size()];
//        for (int i = 0; i < datas.length; i++) {
//            datas[i] = ((String)result.get(i));
//        }
//        return datas;
//    }
//
//    public static Integer[] getIntArray(String str)
//    {
//        String[] strs = split(str, ",");
//        Integer[] result = new Integer[strs.length];
//        for (int i = 0; i < strs.length; i++) {
//            result[i] = Integer.valueOf(Double.valueOf(strs[i]).intValue());
//        }
//        return result;
//    }
//
//    public static String getString(Integer[] ints)
//    {
//        if (ints == null) {
//            return "";
//        }
//        StringBuffer str = new StringBuffer();
//        for (int i = 0; i < ints.length; i++) {
//            if (i != 0) {
//                str.append(",");
//            }
//            str.append(ints[i]);
//        }
//        return str.toString();
//    }
//
//    public static String formatNum(Object obj, String style)
//    {
//        DecimalFormat format = new DecimalFormat(style);
//        return format.format(obj);
//    }
//
//    public static String formatDate(Object obj, String dateStyle)
//    {
//        return DateUtil.format(obj, dateStyle);
//    }
//
//    public static String[] splitUniq(String str, String div)
//    {
//        StringTokenizer stk = new StringTokenizer(str, div);
//        HashMap valueMap = new HashMap();
//        while (stk.hasMoreElements()) {
//            Object value = stk.nextToken();
//            valueMap.put(value, value);
//        }
//        Set set = valueMap.keySet();
//        String[] datas = new String[set.size()];
//        int i = 0;
//        Iterator iter = set.iterator();
//        while (iter.hasNext()) {
//            datas[(i++)] = ((String)iter.next());
//        }
//        return datas;
//    }
//
//    public static String replace(String template, String placeholder, String replacement)
//    {
//        int loc = template.indexOf(placeholder);
//        if (loc < 0) {
//            return template;
//        }
//        return new StringBuffer(template.substring(0, loc)).append(replacement).append(template.substring(loc + placeholder.length())).toString();
//    }
//
//    public static String replaceAll(String template, String placeholder, String replacement)
//    {
//        if (template == null)
//            return "";
//        int loc = template.indexOf(placeholder);
//        if (loc < 0) {
//            return template;
//        }
//        return new StringBuffer(template.substring(0, loc)).append(replacement).append(replaceAll(template.substring(loc + placeholder.length()), placeholder, replacement)).toString();
//    }
//
//    public static boolean isUndefined(Object o)
//    {
//        return (isBlankOrNull(o)) || ("undefined".equals(o));
//    }
//
//    public static boolean isEmpty(Object o)
//    {
//        return isBlankOrNull(o);
//    }
//
//    public static boolean isBlankOrNull(String str)
//    {
//        return (str == null) || (str.isEmpty());
//    }
//
//    public static boolean isBlankOrNull(Object o)
//    {
//        if ((o instanceof RefParameter)) {
//            RefParameter refStr = (RefParameter)o;
//            return (refStr == null) || (refStr.getValue() == null) || (refStr.getValue().toString().length() == 0);
//        }
//        if ((o == null) || (o.toString().length() == 0)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isBlankOrStrNull(Object o)
//    {
//        if ((o instanceof RefParameter)) {
//            RefParameter refStr = (RefParameter)o;
//            return (refStr == null) || (refStr.getValue() == null) || (refStr.getValue().toString().length() == 0);
//        }
//        String str = String.valueOf(o);
//        if ((str == null) || (str.length() == 0) || (str.equalsIgnoreCase("null"))) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isBlankOrStrNull(String str)
//    {
//        if ((str == null) || (str.length() == 0) || (str.equalsIgnoreCase("null"))) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isTrimBlankOrNull(String str)
//    {
//        if ((str == null) || (str.length() == 0) || (str.trim().length() == 0)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static synchronized String readString(InputStream inputStream)
//            throws Throwable
//    {
//        if (inputStream == null) {
//            return null;
//        }
//        ByteArrayOutputStream outputStream = null;
//        try {
//            outputStream = new ByteArrayOutputStream();
//
//            int bytesRead = -1;
//            while ((bytesRead = inputStream.read()) != -1) {
//                outputStream.write(bytesRead);
//            }
//            return new String(outputStream.toByteArray());
//        }
//        catch (Throwable e) {
//            throw e;
//        } finally {
//            if (outputStream != null)
//                outputStream.close();
//        }
//    }
//
//    public static synchronized InputStream writeIntoStream(String str)
//    {
//        if (isBlankOrNull(str))
//            return null;
//        try {
//            return new ByteArrayInputStream(str.getBytes());
//        } catch (Throwable e) {
//            throw BKException.dealEx(e);
//        }
//    }
//
//    public static String join(List<?> arrObject, String str)
//    {
//        if (arrObject == null) {
//            return "";
//        }
//        String sReturn = "";
//        for (Iterator i$ = arrObject.iterator(); i$.hasNext(); ) { Object o = i$.next();
//            sReturn = new StringBuilder().append(sReturn).append(String.valueOf(o)).append(str).toString();
//        }
//
//        if (sReturn.length() != 0) {
//            sReturn = sReturn.substring(0, sReturn.length() - str.length());
//        }
//        return sReturn;
//    }
//
//    public static String join(String[] arrStr, String str)
//    {
//        String sReturn = "";
//        if (arrStr == null) {
//            return null;
//        }
//        for (String s : arrStr) {
//            sReturn = new StringBuilder().append(sReturn).append(s).append(str).toString();
//        }
//
//        if (sReturn.length() != 0) {
//            sReturn = sReturn.substring(0, sReturn.length() - str.length());
//        }
//        return sReturn;
//    }
//
//    public static String join(Object[] arrO, String str)
//    {
//        String sReturn = "";
//        if (arrO == null) {
//            return null;
//        }
//        for (Object s : arrO) {
//            sReturn = new StringBuilder().append(sReturn).append(s.toString()).append(str).toString();
//        }
//
//        if (sReturn.length() != 0) {
//            sReturn = sReturn.substring(0, sReturn.length() - str.length());
//        }
//        return sReturn;
//    }
//
//    public static String join(int[] ints, String str)
//    {
//        String sReturn = "";
//        for (int i : ints) {
//            sReturn = new StringBuilder().append(sReturn).append(i).append(str).toString();
//        }
//
//        if (sReturn.length() != 0) {
//            sReturn = sReturn.substring(0, sReturn.length() - str.length());
//        }
//        return sReturn;
//    }
//
//    public static int instr(String s, String seperator)
//    {
//        return s.toUpperCase().indexOf(seperator.toUpperCase());
//    }
//
//    public static boolean instr(String s, String find, String seperator)
//    {
//        return new StringBuilder().append(seperator).append(s).append(seperator).toString().toUpperCase().indexOf(new StringBuilder().append(seperator).append(find).append(seperator).toString().toUpperCase()) >= 0;
//    }
//
//    public static boolean instrCase(String s, String find, String seperator)
//    {
//        return new StringBuilder().append(seperator).append(s).append(seperator).toString().indexOf(new StringBuilder().append(seperator).append(find).append(seperator).toString()) >= 0;
//    }
//
//    public static String[] splitTrim(String s, String seperator)
//    {
//        while (s.startsWith(seperator)) {
//            s = s.substring(seperator.length());
//        }
//        while (s.endsWith(seperator)) {
//            s = s.substring(0, s.length() - seperator.length());
//        }
//        return split(s, seperator);
//    }
//
//    public static String left(String str, int i)
//    {
//        str = str == null ? "" : str;
//        if (i > str.length())
//            i = str.length();
//        return str.substring(0, i);
//    }
//
//    public static String right(String str, int i)
//    {
//        str = str == null ? "" : str;
//        if (i > str.length())
//            i = str.length();
//        return str.substring(str.length() - i, str.length());
//    }
//
//    public static String mid(String str, int i, int len)
//    {
//        if (str == null)
//            return null;
//        if (i + len > str.length())
//            return str.substring(i, str.length());
//        return str.substring(i, i + len);
//    }
//
//    public static String mid(String str, int i)
//    {
//        return mid(str, i, str.length());
//    }
//
//    public static int len(Object str)
//    {
//        if (str == null) {
//            return 0;
//        }
//        return str.toString().length();
//    }
//
//    public static int len(Object[] args)
//    {
//        if (args == null) {
//            return 0;
//        }
//        return args.length;
//    }
//
//    public static boolean isDate(Object obj)
//    {
//        if (obj == null)
//            return false;
//        return isDate(obj.toString());
//    }
//
//    public static boolean isDate(Object[] obj)
//    {
//        if (obj == null)
//            return false;
//        for (int i = 0; i < obj.length; i++) {
//            if (!isDate(obj[i])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isNumeric(Object obj)
//    {
//        if (obj == null)
//            return false;
//        if (((obj instanceof BigDecimal)) || ((obj instanceof Integer)) || ((obj instanceof Double)) || ((obj instanceof Long))) {
//            return true;
//        }
//        return isNumeric(obj.toString());
//    }
//
//    public static boolean isNumeric(Object[] obj)
//    {
//        if (obj == null)
//            return false;
//        for (int i = 0; i < obj.length; i++) {
//            if (!isNumeric(obj[i])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    static boolean isDate(String str)
//    {
//        Date result = GenericTypeValidator.formatDate(str, null);
//        if (result != null)
//            return true;
//        return false;
//    }
//
//    static boolean isNumeric(String str)
//    {
//        Double result = GenericTypeValidator.formatDouble(str);
//        if (result != null) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public static boolean isBoolean(Object[] obj)
//    {
//        if (obj == null)
//            return false;
//        for (int i = 0; i < obj.length; i++) {
//            if (!isBoolean(obj[i])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isBoolean(Object obj)
//    {
//        if (obj == null)
//            return false;
//        return isBoolean(obj.toString());
//    }
//
//    static boolean isBoolean(String str)
//    {
//        return (str != null) && ((str.equalsIgnoreCase("TRUE")) || (str.equalsIgnoreCase("FALSE")) || (isNumeric(str)));
//    }
//
//    public static String[] getTableFromSQL(String SQL)
//    {
//        SQL = new StringBuilder().append(" ").append(SQL).toString();
//        String regex = "\\s+(?:(?:from|join|update|into)\\s+)";
//        regex = new StringBuilder().append(regex).append("(?:(?:(?:\\w+\\.)?\\w+(?:\\s+as)?(?:\\s+\\w+)?\\s*,\\s*)*)").toString();
//        regex = new StringBuilder().append(regex).append("(?:\\w+\\.)?(\\w+)").toString();
//
//        DebugUtil.debug(new StringBuilder().append(">tableErrHandle.matcher(SQL)=").append(SQL).toString());
//        Pattern patt = Pattern.compile(regex, 2);
//        Matcher matcher = patt.matcher(SQL);
//        ArrayList list = new ArrayList();
//        while (matcher.find()) {
//            DebugUtil.debug(new StringBuilder().append(">getTableFromSQL().match() \"").append(matcher.group()).append("\" at positions ").append(matcher.start()).append("-").append(matcher.end() - 1).toString());
//
//            String temp = matcher.group().toUpperCase().replaceAll(" FROM ", "").replaceAll(" JOIN ", "").replaceAll(" UPDATE ", "").replaceAll(" INTO ", "").replaceAll(" DELETE ", "").replaceAll("INSERT INTO", "");
//
//            String[] tbls = null;
//            if (!temp.trim().equals("")) {
//                tbls = split(temp, ",");
//
//                for (int i = 0; i < tbls.length; i++) {
//                    temp = split(tbls[i].trim(), " ")[0];
//                    if (!list.contains(temp))
//                        list.add(temp);
//                }
//            }
//        }
//        return (String[])list.toArray(new String[0]);
//    }
//
//    public static String Str2SQL(String sStr)
//    {
//        if (isBlankOrNull(sStr))
//            return "''";
//        return new StringBuilder().append("'").append(sStr.replaceAll("'", "''")).append("'").toString();
//    }
//
//    public static String GetJoinSQL(String sql1, String sql2)
//    {
//        return join(sql1, sql2, null);
//    }
//
//    public static String join(String s1, String s2, String spt)
//    {
//        if (spt == null)
//            spt = " and ";
//        return isTrimBlankOrNull(s1) ? s2 : new StringBuilder().append(s1).append(isTrimBlankOrNull(s2) ? "" : new StringBuilder().append(spt).append(s2).toString()).toString();
//    }
//
//    public static String getJoin(String s1, String s2, String spt)
//    {
//        return isBlankOrNull(s1) ? s2 : new StringBuilder().append(s1).append(isBlankOrNull(s2) ? "" : new StringBuilder().append(spt).append(s2).toString()).toString();
//    }
//
//    public static String getNnofStrValue(Object value)
//    {
//        return value == null ? "" : value.toString();
//    }
//
//    public static Boolean like(String str1, String str2)
//    {
//        return Boolean.valueOf(str1.matches(new StringBuilder().append(str2).append("((\\-*\\d*|\\s*|\\w*)*)").toString()));
//    }
//
//    private static Matcher pSplitExpression(String s)
//    {
//        String regex = "([^,\\(\\)\"]|\"([^\"]|\"\")*\")+|,|\\(|\\)";
//        Pattern patt = Pattern.compile(regex, 2);
//        Matcher matcher = patt.matcher(s);
//        return matcher;
//    }
//
//    public static ArrayList<String> sPlitSparse(String s)
//    {
//        Matcher matcher = pSplitExpression(s);
//
//        Integer lLeftParenthesis = Integer.valueOf(0);
//        String sPara = "";
//        ArrayList aParaValue = new ArrayList();
//        Integer lPara = Integer.valueOf(0);
//        while (matcher.find()) {
//            String value = matcher.group();
//            if (value.equalsIgnoreCase(","))
//            {
//                if (lLeftParenthesis.intValue() >= 1) {
//                    sPara = new StringBuilder().append(sPara).append(",").toString();
//                } else {
//                    aParaValue.add(lPara.intValue(), sPara);
//                    lPara = Integer.valueOf(lPara.intValue() + 1);
//                    sPara = "";
//                }
//            } else if (value.equalsIgnoreCase(")")) {
//                if (lLeftParenthesis.intValue() == 1)
//                {
//                    if (sPara.length() != 0) {
//                        sPara = new StringBuilder().append(sPara).append(")").toString();
//                        lLeftParenthesis = Integer.valueOf(lLeftParenthesis.intValue() - 1);
//                    }
//                } else {
//                    lLeftParenthesis = Integer.valueOf(lLeftParenthesis.intValue() - 1);
//                    sPara = new StringBuilder().append(sPara).append(")").toString();
//                }
//            } else if (value.equalsIgnoreCase("(")) {
//                sPara = new StringBuilder().append(sPara).append("(").toString();
//                lLeftParenthesis = Integer.valueOf(lLeftParenthesis.intValue() + 1);
//            } else {
//                sPara = new StringBuilder().append(sPara).append(value).toString();
//            }
//        }
//        if (sPara.length() > 0) {
//            aParaValue.add(lPara.intValue(), sPara);
//            sPara = "";
//        }
//        return aParaValue;
//    }
//
//    public static ArrayList<String> SplitSemicolonParse(String s)
//    {
//        String regex = "([^;\"]|\"([^\"]|\"\")*\")+|;";
//        Pattern patt = Pattern.compile(regex, 2);
//        Matcher matcher = patt.matcher(s);
//
//        ArrayList aRValue = new ArrayList();
//        String value = "";
//        int lPara = 0;
//        while (matcher.find()) {
//            value = matcher.group();
//            if (!value.equalsIgnoreCase(";")) {
//                aRValue.add(lPara, value);
//                lPara++;
//            }
//        }
//        return aRValue;
//    }
//
//    public static String pSprintfEx(String sPart, String sFormat, String[] args)
//    {
//        String pSprintfEx = "";
//        String sSplit = ",";
//        if (len(args) > 0)
//            sSplit = args[0];
//        String[] sPiece = split(sPart, ",");
//
//        boolean bNot1St = false;
//        for (int i = 0; i < sPiece.length; i++) {
//            if (len(sPiece[i]) != 0) {
//                if (!bNot1St) {
//                    pSprintfEx = replaceAll(sFormat, "%a", sPiece[i]);
//                    bNot1St = true;
//                } else {
//                    pSprintfEx = new StringBuilder().append(pSprintfEx).append(sSplit).append(replaceAll(sFormat, "%a", sPiece[i])).toString();
//                }
//            }
//        }
//        return pSprintfEx;
//    }
//
//    public static String[] getStringArrayBYArray(Object[] objs)
//    {
//        Integer objsSize = Integer.valueOf(objs.length);
//        String[] strs = new String[objsSize.intValue()];
//        Integer localInteger1;
//        Integer localInteger2;
//        for (Integer i = Integer.valueOf(0); i.intValue() < objsSize.intValue(); localInteger2 = i = Integer.valueOf(i.intValue() + 1)) {
//            strs[i.intValue()] = objs[i.intValue()].toString();
//
//            localInteger1 = i;
//        }
//
//        return strs;
//    }
//
//    public static String CStr(Object ret)
//    {
//        if (ret == null)
//            return "";
//        return ret.toString();
//    }
//
//    public static String string(int num, String sValue)
//    {
//        StringBuffer sbuf = new StringBuffer();
//        for (int i = 0; i < num; i++) {
//            sbuf.append(sValue);
//        }
//        return sbuf.toString();
//    }
//
//    public static String Space(int num)
//    {
//        return string(num, " ");
//    }
//
//    public static String[] readInternUTFs(ObjectInput in)
//            throws IOException
//    {
//        boolean b = in.readBoolean();
//        if (b) {
//            int length = in.readInt();
//            String[] result = new String[length];
//            for (int i = 0; i < length; i++) {
//                result[i] = readInternUTF(in);
//            }
//            return result;
//        }
//        return null;
//    }
//
//    public static String readInternUTF(ObjectInput in)
//            throws IOException
//    {
//        try
//        {
//            boolean b = in.readBoolean();
//            if (b)
//            {
//                String s = in.readUTF().intern();
//                if ((s != null) && (s.length() == 0)) {
//                    return "";
//                }
//
//                return s;
//            }
//
//            return null;
//        }
//        catch (UTFDataFormatException ex) {
//            throw new IOException("数据过长", ex);
//        } catch (IOException ex) {
//            throw ex;
//        } catch (Throwable e) {
//            throw BKException.dealEx(e);
//        }
//    }
//
//    public static String readUTF(ObjectInput in)
//            throws IOException
//    {
//        try
//        {
//            boolean b = in.readBoolean();
//            if (b)
//            {
//                String s = in.readUTF();
//                if ((s != null) && (s.length() == 0)) {
//                    return "";
//                }
//
//                return s;
//            }
//
//            return null;
//        }
//        catch (UTFDataFormatException ex) {
//            throw new IOException("数据过长", ex);
//        } catch (IOException ex) {
//            throw ex;
//        } catch (Throwable e) {
//            throw BKException.dealEx(e);
//        }
//    }
//
//    public static void writeUTF(ObjectOutput out, String s)
//            throws IOException
//    {
//        try
//        {
//            boolean b = s != null;
//            out.writeBoolean(b);
//            if (b)
//            {
//                out.writeUTF(s);
//            }
//
//            out.flush();
//        } catch (UTFDataFormatException ex) {
//            throw new IOException(new StringBuilder().append("数据过长:").append(s.length()).toString(), ex);
//        } catch (IOException ex) {
//            throw ex;
//        } catch (Throwable e) {
//            throw BKException.dealEx(e);
//        }
//    }
//
//    public static void writeUTFs(ObjectOutput out, String[] ss)
//            throws IOException
//    {
//        boolean b = ss != null;
//        out.writeBoolean(b);
//        if (b) {
//            int length = ss.length;
//            out.writeInt(ss.length);
//            for (int i = 0; i < length; i++)
//                writeUTF(out, ss[i]);
//        }
//    }
//
//    public static final String QBchange(String QJstr)
//    {
//        String outStr = "";
//        String Tstr = "";
//        byte[] b = null;
//
//        for (int i = 0; i < QJstr.length(); i++) {
//            try {
//                Tstr = QJstr.substring(i, i + 1);
//                b = Tstr.getBytes("unicode");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            if (b[3] == -1) {
//                b[2] = ((byte)(b[2] + 32));
//                b[3] = 0;
//                try {
//                    outStr = new StringBuilder().append(outStr).append(new String(b, "unicode")).toString();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                outStr = new StringBuilder().append(outStr).append(Tstr).toString();
//            }
//        }
//        return outStr;
//    }
//
//    public static final boolean CheckBoxNoString(String sCheckBoxNoString)
//    {
//        sCheckBoxNoString = sCheckBoxNoString.trim();
//
//        if (sCheckBoxNoString.length() != 11) {
//            return false;
//        }
//
//        if (sCheckBoxNoString.startsWith("HLCU")) {
//            return true;
//        }
//
//        if (!sCheckBoxNoString.substring(0, 4).matches("[A-Z]*")) {
//            return false;
//        }
//
//        if (!sCheckBoxNoString.substring(4).matches("[0-9]*")) {
//            return false;
//        }
//
//        String[][] arrValueTable = new String[36][2];
//
//        int iAscii = 48;
//        int iAsciiValue = 0;
//
//        for (int i = 0; i < arrValueTable.length; i++)
//        {
//            if (i == 30) {
//                System.out.print("");
//            }
//            arrValueTable[i] = { String.valueOf((char)iAscii), String.valueOf(iAsciiValue) };
//
//            iAscii++;
//            iAsciiValue++;
//
//            if (iAscii == 58) {
//                iAscii = 65;
//            }
//
//            if (iAsciiValue % 11 == 0) {
//                iAsciiValue++;
//            }
//        }
//
//        double iSumValue = 0.0D;
//        int iCurrValue = 0;
//
//        for (int i = 0; i < sCheckBoxNoString.length() - 1; i++) {
//            for (int j = 0; j < arrValueTable.length; j++) {
//                if (arrValueTable[j][0].equals(sCheckBoxNoString.substring(i, i + 1)))
//                {
//                    iCurrValue = Integer.parseInt(arrValueTable[j][1]);
//                    iSumValue += iCurrValue * Math.pow(2.0D, i);
//                }
//
//            }
//
//        }
//
//        return iSumValue % 11.0D % 10.0D == Integer.parseInt(sCheckBoxNoString.substring(10, 11));
//    }
//
//    public static String removeExcelIllegals(String str)
//    {
//        if (!isBlankOrNull(str)) {
//            str = str.replaceAll("/", "").replaceAll(":", "").replaceAll("[?]", "").replaceAll("[*]", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("[\\\\]", "");
//        }
//
//        return str;
//    }
//
//    public static Object[] arrayCopy(Object[] array1, Object[] array2)
//    {
//        if (array1 == null)
//            return clone(array2);
//        if (array2 == null) {
//            return clone(array1);
//        }
//        Object[] joinedArray = (Object[])Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);
//
//        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
//        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
//        return joinedArray;
//    }
//
//    private static Object[] clone(Object[] array) {
//        if (array == null) {
//            return null;
//        }
//        return (Object[])array.clone();
//    }
//
//    public static void main(String[] args) {
//        String sCheckNo = "GESU2475463";
//        System.out.print(CheckBoxNoString(sCheckNo));
//    }
//
//    public static String AttributeMap2String(Map<String, Object> map)
//    {
//        String reString = "";
//        Object value;
//        if (map != null) {
//            value = null;
//            for (String key : map.keySet()) {
//                value = map.get(key);
//                if (value != null)
//                {
//                    if ((value instanceof Map))
//                        reString = new StringBuilder().append(reString).append(AttributeMap2String((Map)value)).append("||").toString();
//                    else
//                        reString = new StringBuilder().append(reString).append(key).append(":=").append(value.toString()).append("||").toString();
//                }
//            }
//        }
//        return reString;
//    }
//
//    public static Map<String, String> AttributeString2Map(String strAttribute) {
//        if ((strAttribute == null) || (strAttribute.length() == 0)) {
//            return null;
//        }
//
//        Map map = new HashMap();
//        String[] Attributes = strAttribute.split("\\|\\|");
//        String[] arrAttribute = null;
//        for (String Attribute : Attributes) {
//            arrAttribute = Attribute.split(":=");
//            if (arrAttribute.length == 2)
//            {
//                map.put(arrAttribute[0], arrAttribute[1]);
//            }
//        }
//        return map;
//    }
//
//    public static int getCharPosition(String sStr, String sSearch, int n)
//    {
//        Matcher matcher = Pattern.compile(sSearch).matcher(sStr);
//        int mIdx = 0;
//        while (matcher.find()) {
//            mIdx++;
//            if (mIdx == n) {
//                return matcher.start();
//            }
//        }
//        return 0;
//    }
//
//    public static boolean isInteger(String s)
//    {
//        boolean isDigit = true;
//        int length = s.length();
//        for (int i = 0; i < length; i++) {
//            char c = s.charAt(i);
//            if (!Character.isDigit(c)) {
//                isDigit = false;
//                break;
//            }
//        }
//        return isDigit;
//    }
//
//    public static boolean endsWith(String str, String suffix) {
//        return endsWith(str, suffix, false);
//    }
//
//    public static boolean endsWithIgnoreCase(String str, String suffix) {
//        return endsWith(str, suffix, true);
//    }
//
//    private static boolean endsWith(String str, String suffix, boolean ignoreCase)
//    {
//        if ((str == null) || (suffix == null)) {
//            return (str == null) && (suffix == null);
//        }
//        if (suffix.length() > str.length()) {
//            return false;
//        }
//        int strOffset = str.length() - suffix.length();
//        return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
//    }
//
//    public static String capitalize(String str)
//    {
//        int strLen;
//        if ((str == null) || ((strLen = str.length()) == 0))
//            return str;
//        int strLen;
//        return new StringBuilder().append(strLen + Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
//    }
//
//    /** @deprecated */
//    public static String capitalise(String str) {
//        return capitalize(str);
//    }
//
//    public static String uncapitalize(String str)
//    {
//        int strLen;
//        if ((str == null) || ((strLen = str.length()) == 0))
//            return str;
//        int strLen;
//        return new StringBuilder().append(strLen + Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
//    }
//
//    /** @deprecated */
//    public static String uncapitalise(String str) {
//        return uncapitalize(str);
//    }
//}
