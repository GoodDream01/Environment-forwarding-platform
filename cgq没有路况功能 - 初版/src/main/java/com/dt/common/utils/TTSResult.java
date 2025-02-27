package com.dt.common.utils;

public class TTSResult {
	private boolean result = false;
	private String url = "";
	
	public TTSResult(boolean result, String url) {
		super();
		this.result = result;
		this.url = url;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
