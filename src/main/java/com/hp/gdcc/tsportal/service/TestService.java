package com.hp.gdcc.tsportal.service;

import com.hp.gdcc.tsportal.model.Test;

public interface TestService {
	
	Test selectByPrimaryKey(Long id);
	
}
