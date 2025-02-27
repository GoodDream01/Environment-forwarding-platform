package com.dt.taje.mvc.base.converter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;

public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {

	private static final MediaType UTF8 = new MediaType("text","plain",Charset.forName("UTF-8"));
	private boolean writeAcceptCharset=true;
	
	@Override
	protected List<Charset> getAcceptedCharsets() {
		// TODO Auto-generated method stub
		return Arrays.asList(UTF8.getCharSet());
	}
	
	@Override
	protected void writeInternal(String str, HttpOutputMessage outputMessage)
			throws IOException {
		// TODO Auto-generated method stub
		if(this.writeAcceptCharset)
			outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
		Charset  charset = UTF8.getCharSet();
		StreamUtils.copy(str, charset, outputMessage.getBody());
	}
	
	@Override
	protected MediaType getDefaultContentType(String t) throws IOException {
		// TODO Auto-generated method stub
		return UTF8;
	}
	
	public boolean isWriteAcceptCharset() {
		return writeAcceptCharset;
	}
	
	public void setWriteAcceptCharset(boolean writeAcceptCharset) {
		this.writeAcceptCharset = writeAcceptCharset;
	}
	
}
