package Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 使spring mvc与fastjson结合
 * 利用@ResponseBody注解返回JSON字符串
 */
public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
	public final static Charset UTF8 = Charset.forName("UTF-8");
	private Charset charset = UTF8;
	private SerializerFeature[] serializerFeature = new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat};
	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		//输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//入字节流
		InputStream in = inputMessage.getBody();
		byte[] buf = new byte[1024];
		for (;;) {
			int len = in.read(buf);
			if (len == -1) {
				break;
			}
			if (len > 0) {
				baos.write(buf, 0, len);
			}
		}
		byte[] bytes = baos.toByteArray();
		//转对象
		if (charset == UTF8) {
			return JSON.parseObject(bytes, clazz);
		} else {
			return JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), clazz);//获得该字符集下的解码器
		}
	}
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		//输出流
		OutputStream out = outputMessage.getBody();
		byte[] bytes;
		if (charset == UTF8) {
			//转json字节
			if (serializerFeature != null) {
				bytes = JSON.toJSONBytes(obj, serializerFeature);
			} else {
				bytes = JSON.toJSONBytes(obj, SerializerFeature.WriteDateUseDateFormat);
			}
		} else {
			String text;
			//转json字符串
			if (serializerFeature != null) {
				text = JSON.toJSONString(obj, serializerFeature);
			} else {
				text = JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
			}
			//转字节
			bytes = text.getBytes(charset);
		}
		//写出JSON
		out.write(bytes);
	}
}
