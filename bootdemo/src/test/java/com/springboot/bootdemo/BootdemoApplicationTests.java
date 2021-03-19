package com.springboot.bootdemo;

import com.springboot.bootdemo.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
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

	@Resource
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
//		redisTemplate.opsForValue().set("global_id:player.list",24);
//		Object name = redisTemplate.opsForValue().get("global_id:player.list");

		Student student = new Student();
		student.setName("李四");
		student.setId(4);

//		Student stu = (Student) redisTemplate.opsForValue().get("student:" + student.getId());
//		System.out.println(stu.toString());
		redisTemplate.delete("k2");


//		List<Integer> set1 = redisTemplate.opsForSet().pop("set1",3);
//		System.out.println(set1);
	}

}
