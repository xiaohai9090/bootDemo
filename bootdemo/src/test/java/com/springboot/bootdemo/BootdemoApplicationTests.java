package com.springboot.bootdemo;

import com.springboot.bootdemo.domain.Student;
import com.springboot.bootdemo.service.TestService;
import com.springboot.bootdemo.service.impl.TestServiceImpl1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BootdemoApplicationTests {

//	@Resource
//	private RedisTemplate redisTemplate;

	@Resource(name = "service1")
	private TestService testService;

	@Test
	public void contextLoads() {

		testService.run();
//		redisTemplate.opsForValue().set("global_id:player.list",24);
//		Object name = redisTemplate.opsForValue().get("global_id:player.list");

//		Student student = new Student();
//		student.setName("李四");
//		student.setId(4);

//		Student stu = (Student) redisTemplate.opsForValue().get("student:" + student.getId());
//		System.out.println(stu.toString());
//		redisTemplate.delete("k2");


//		List<Integer> set1 = redisTemplate.opsForSet().pop("set1",3);
//		System.out.println(set1);
	}

}
