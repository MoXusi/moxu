package com.awx.moxu;

import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.mapper.BladeUserMapper;
import com.awx.moxu.service.impl.BladeUserServiceImpl;
import com.awx.moxu.utils.Aes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MoxuApplicationTests {

	@Autowired
	private BladeUserMapper  bladeUserMapper;
	@Autowired
	private BladeUserServiceImpl bladeUserService;
	@Autowired
	Aes aes;
	@Test
	void contextLoads() {
//		List<BladeUser> bladeUsers = bladeUserMapper.selectList(null);
//		QueryWrapper queryWrapper = new QueryWrapper();
//		BladeUser bladeUser = bladeUserMapper.queryById(1);
//		bladeUsers.forEach(System.out::println);
//		bladeUser.setId(null);
//		bladeUser.setCreateTime(null);
//		bladeUser.setPassword("231250731");
//		int insert = bladeUserService.saveUser(bladeUser);
//		System.out.println(insert);
		String admin = aes.encryptHex("admin");
		System.out.println(admin);
	}

}
