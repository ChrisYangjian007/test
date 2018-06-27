package Interface;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017-09-20.
 */
public interface ICompress {
    byte[] Compress(byte[] p_Buffer);
    byte[] Compress(String p_strSource) throws UnsupportedEncodingException;
    String Decompress(byte[] p_Buffer);
    Object BytesToObject(byte[] WindBuffer);
    byte[] ObjectToBytes(Object WindObject);
}
