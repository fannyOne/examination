package com.hp.gdcc.tsportal.mapper;

import com.hp.gdcc.tsportal.model.Test;

public interface TestMapper {
	
	Test selectByPrimaryKey(Long id);
	
}