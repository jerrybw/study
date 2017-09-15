package com.jerry.study;

import com.jerry.study.bean.primary.User;
import com.jerry.study.bean.secondary.Message;
import com.jerry.study.dao.primary.UserRepository;
import com.jerry.study.dao.secondary.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataBaseApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;

	@Test
	public void test() throws Exception {

		User aaa = userRepository.findByUserName("aaa");
		System.out.println(aaa.getUserName() + "***" + aaa.getId());

		List<User> users = userRepository.findByUserNameNotEmpty();
		for (User user:users) {
			System.out.println(user.getUserName() + "***" + user.getId());
		}

	}

}
