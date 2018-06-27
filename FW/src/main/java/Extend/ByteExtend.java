package Extend;


/**
 * Created by Administrator on 2017-07-28.
 */
public class ByteExtend {
    /// 功能描述: 字节转字符串
    public static String BytesToString(byte[] p_Buffer, boolean p_IsGeneral) {
        try {
            if (p_IsGeneral)
                return new String(p_Buffer, "UTF-8");
            else {
                return  new String(p_Buffer,"UTF-8");
//                StringBuilder stringBuilder = new StringBuilder("");
//                for (int i = 0; i < p_Buffer.length; i++) {
//                    // 之所以用byte和0xff相与，是因为int是32位，与0xff相与后就舍弃前面的24位，只保留后8位
//                    String str = Integer.toHexString(p_Buffer[i] & 0xff);
//                    if (str.length() < 2) { // 不足两位要补0
//                        stringBuilder.append(0);
//                    }
//                    stringBuilder.append(str);
//                }
//                return stringBuilder.toString();
            }
        } catch (Exception ex) {
            return "";
        }
    }

    /// 字节转16进制字符串
    public static String BytesToHex(byte[] p_Buffer, char p_SplitChar) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (p_Buffer == null || p_Buffer.length <= 0) {
            return null;
        }
        for (int i = 0; i < p_Buffer.length; i++) {
            int v = p_Buffer[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /// 字符串转16进制
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
