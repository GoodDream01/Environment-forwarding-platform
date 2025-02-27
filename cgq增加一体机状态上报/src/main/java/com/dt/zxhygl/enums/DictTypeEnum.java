package com.dt.zxhygl.enums;

public enum DictTypeEnum {
	
	/**角色类型**/
	PUB_USER_JSLX("100901"),
	/**用户状态**/
	PUB_USER_YHZT("100902"),
	/**出字方式**/
	PUB_PMKZ_CZFS("100001"),
	/**文字颜色**/
	PUB_PMKZ_ZTYS("100002"),
	/**字体宽高**/
	PUB_PMKZ_ZTKG("100003"),
	/**屏幕类型**/
	PUB_PMKZ_PMLX("100004");
	
	// 成员变量
	private String name;
	private int index;
	
	DictTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
	
}
