package com.hp.gdcc.tsportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.gdcc.tsportal.mapper.TestMapper;
import com.hp.gdcc.tsportal.model.Test;
import com.hp.gdcc.tsportal.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper testMapper;

	public Test selectByPrimaryKey(Long id) {
		return testMapper.selectByPrimaryKey(id);
	}

}
