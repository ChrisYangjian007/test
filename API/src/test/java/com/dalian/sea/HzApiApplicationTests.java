package com.dalian.sea;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HzApiApplicationTests {

	@Test
	public void contextLoads() {
		String newPassword = new SimpleHash("md5", "123456",  ByteSource.Util.bytes("apiadmin"), 2).toHex();
		System.out.println(newPassword);
	}

}
