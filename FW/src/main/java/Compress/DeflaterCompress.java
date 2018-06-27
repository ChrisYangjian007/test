package Compress;

import Interface.ICompress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017-09-20.
 */
public class DeflaterCompress implements ICompress{
    @Override
    public byte[] Compress(byte[] p_Buffer) {
        byte[] output = new byte[0];
        Deflater compresser = new Deflater();

        compresser.reset();
        compresser.setInput(p_Buffer);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(p_Buffer.length);
        try {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = p_Buffer;
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compresser.end();
        return output;
    }

    @Override
    public byte[] Compress(String p_strSource) {
        String chatacter = "UTF-8";
        Deflater compresser = null;
        byte[] arrayOfByte;
        try {
            byte[] input = p_strSource.getBytes(chatacter);
            arrayOfByte = Compress(input);
        } catch (Exception ex) {
            arrayOfByte = null;
        }
        if (compresser != null) compresser.end();
        return arrayOfByte;
    }

    @Override
    public String Decompress(byte[] p_Buffer) {
        String chatacter = "UTF-8";
        byte[] output = new byte[0];

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(p_Buffer);

        String strOgr="";
        ByteArrayOutputStream o = new ByteArrayOutputStream(p_Buffer.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
            if (decompresser!= null)decompresser.end();
            strOgr=new String(output,chatacter);
        } catch (Exception e) {
            output = p_Buffer;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strOgr;
    }

    @Override
    public Object BytesToObject(byte[] WindBuffer) {
        return null;
    }

    @Override
    public byte[] ObjectToBytes(Object WindObject) {
        return new byte[0];
    }
}
