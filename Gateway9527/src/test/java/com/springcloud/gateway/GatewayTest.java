package com.springcloud.gateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

/**
 * @author : renjiahui
 * @date : 2021/1/8 19:38
 * @desc :
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GatewayTest {

    @Test
    public void timeTest() {
        System.out.println(ZonedDateTime.now());
    }
}
