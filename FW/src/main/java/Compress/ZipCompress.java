package Compress;

import Interface.ICompress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2017-09-20.
 */
public class ZipCompress implements ICompress {
    @Override
    public byte[] Compress(byte[] p_Buffer) {
        return new byte[0];
    }

    @Override
    public byte[] Compress(String p_strSource) {
        String chatacter = "UTF-8";
        if (p_strSource == null) return null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        byte[] arrayOfByte;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry("0"));
            zipOutputStream.write(p_strSource.getBytes(chatacter));
            zipOutputStream.closeEntry();
            arrayOfByte = byteArrayOutputStream.toByteArray();
        } catch (IOException localIOException5) {
            arrayOfByte = null;
        } finally {
            if (zipOutputStream != null)
                try {
                    zipOutputStream.close();
                } catch (IOException localIOException6) {
                }
            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException7) {
                }
        }
        return arrayOfByte;
    }

    @Override
    public String Decompress(byte[] p_Buffer) {
        String chatacter = "UTF-8";
        if (p_Buffer == null) return null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ZipInputStream zipInputStream = null;
        String str;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayInputStream = new ByteArrayInputStream(p_Buffer);
            zipInputStream = new ZipInputStream(byteArrayInputStream);
            ZipEntry localZipEntry = zipInputStream.getNextEntry();
            byte[] arrayOfByte = new byte[1024];
            int i = -1;
            while ((i = zipInputStream.read(arrayOfByte)) != -1)
                byteArrayOutputStream.write(arrayOfByte, 0, i);
            str = byteArrayOutputStream.toString(chatacter);
        } catch (IOException localIOException7) {
            str = null;
        } finally {
            if (zipInputStream != null)
                try {
                    zipInputStream.close();
                } catch (IOException localIOException8) {
                }
            if (byteArrayInputStream != null)
                try {
                    byteArrayInputStream.close();
                } catch (IOException localIOException9) {
                }
            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException10) {
                }
        }
        return str;
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
