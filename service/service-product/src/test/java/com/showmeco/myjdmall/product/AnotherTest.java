package com.showmeco.myjdmall.product;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/7 9:18
 */
public class AnotherTest {

    @Test
    public void test01(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(date);
    }
}
