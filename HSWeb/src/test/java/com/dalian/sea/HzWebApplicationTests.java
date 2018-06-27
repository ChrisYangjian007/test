package com.dalian.sea;

import com.dalian.sea.py4j.IllegalPinyinException;
import com.dalian.sea.py4j.PinyinConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HzWebApplicationTests {
    @Autowired
    private PinyinConverter pinyinConverter;

    @Test
    public void contextLoads() {
        StringBuffer buffer=new StringBuffer();
        try {
            String py = pinyinConverter.getPinyin("鲜海参加工");
            char[] chars=py.toCharArray();
            for(int i = 0; i < chars.length ; i++){
                if(chars[i] >= 'A' && chars[i] <= 'Z'){
                    buffer.append(chars[i]);
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            Date now = new Date();
            buffer.append(sdf.format(now));
            System.out.println("==============================================================================================");
            System.out.println(buffer);
        } catch (IllegalPinyinException e) {
            e.printStackTrace();
        }
    }
}
