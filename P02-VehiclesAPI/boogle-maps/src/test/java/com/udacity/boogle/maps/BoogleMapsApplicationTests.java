package com.udacity.boogle.maps;

import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoogleMapsApplicationTests {
	@LocalServerPort // (Spring) allow injection od server`s Port
	private Integer port;


	@Test
	public void contextLoads() {
	}

}
