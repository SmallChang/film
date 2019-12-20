package org.cly;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cly.dao.entity.User;
import org.cly.dao.mapper.UserMapper;
import org.cly.example.dao.ExampleUserMapper;
import org.cly.example.dao.entity.ExampleUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmApplication.class)
class FilmApplicationTests {

	@Autowired
	private ExampleUserMapper exampleUserMapper;

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	//test自己编写
	@Test
	void mybatisPlusTest(){
		List<ExampleUser> exampleUsers = exampleUserMapper.selectList(null);
		exampleUsers.forEach(System.out::println);
	}

	//test代码生成
	@Test
	void mybatisPlusTest2(){
		AbstractWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("user_name","admin");
		List<User> users = userMapper.selectList(queryWrapper);
		users.forEach(System.out::println);
	}

	@Test
	public void addUserTest(){

		User user = new User();
		user.setUserName("cly");
		user.setUserPwd("970623");

		int insert = userMapper.insert(user);
		System.out.println("isSuccess:"+ (insert == 1? true : false));

	}

	@Test
	public void updateUserTest(){
		User user = new User();
//		user.setUuid(6);
		user.setUserName("lfy");
//		user.setUserPwd("970623");

//		int update = userMapper.updateById(user);

		AbstractWrapper abstractWrapper = new UpdateWrapper();
		abstractWrapper.eq("user_name","chang");

		int update = userMapper.update(user, abstractWrapper);
		System.out.println("isSuccess:"+ (update == 1? true : false));
	}

	@Test
	public void deleteUserTest(){

		int id = 6;
		int delete = userMapper.deleteById(6);
		System.out.println("isSuccess:"+ (delete == 1? true : false));
	}

	@Test
	public void queryUserTest(){
		int id = 5;
		User user = userMapper.selectById(id);
		System.out.println(user);
	}

	/**
	 * 自定义sql
	 */
	@Test
	public void defineSqlTest(){
//		List<User> users = userMapper.getUsers();
//		users.forEach(System.out::println);
	}

	/**
	 * 自定义sql 加 分页
	 */
	@Test
	public void defineSqlPageTest(){
		IPage<User> iPage = new Page<>();
		iPage.setCurrent(1);
		iPage.setSize(2);

		List<User> users = userMapper.getUsers(iPage);
		users.forEach(System.out::println);
	}

	/**
	 * pageTest分页
	 */
	@Test
	void pageTest(){

		IPage<User> iPage = new Page<>();
		iPage.setCurrent(1);
		iPage.setSize(2);
		IPage<User> userPage = userMapper.selectPage(iPage, null);
		List<User> users = userPage.getRecords();
		System.out.println("userPage="+userPage.getTotal()); //5
		users.forEach(System.out::println); //第一页前两条
	}



}
