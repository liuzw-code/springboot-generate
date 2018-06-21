package com.liuzw.generate;

import com.liuzw.generate.service.IGenerateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateTests {

	@Autowired
	private IGenerateService generateService;

	@Test
	public void generateBean(){
		String tableNames = "t_push_message";
		generateService.generateBean(tableNames);
	}

	@Test
	public void generateService(){
		String tableNames="t_push_message";
		generateService.generateService(tableNames);

	}

	@Test
	public void generateServiceImpl(){
		String tableNames = "t_push_message";
		generateService.generateServiceImpl(tableNames);
	}

	@Test
	public void generateMapper(){
		String tableNames = "t_push_message";
		generateService.generateMapper(tableNames);
	}

	@Test
	public void generateMapperXml(){
		String tableNames = "t_push_message";
		generateService.generateMapperXml(tableNames);
	}

	@Test
	public void generateAll(){
		String tableNames = "t_push_message";
		generateService.generateAll(tableNames);
	}

}
