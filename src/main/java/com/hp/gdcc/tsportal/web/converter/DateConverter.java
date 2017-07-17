package com.hp.gdcc.tsportal.web.converter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {
	class DateEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {

		}
	}

	@Override
	public Date convert(String source) {
		try {
			if (StringUtils.isEmpty(source)) {
				return null;
			}
			Date date;
			if (source.indexOf(":") == -1) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
			} else {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			}
			return date;
		} catch (ParseException e) {
			throw new IllegalArgumentException("转换日期格式失败！");
		}
	}

}
