package com.dt.zxhygl.entity;

import java.util.Map;

public class TreeNode {

	private String id;
	private String text;
	private String iconCls;
	private String children;
	private String pid;
	private String img;
	private String type;
	private boolean isLeaf = false;
	private boolean expanded = false;
	
	private Map<String,String> otherParam;
	
	
	
	public TreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public TreeNode(String id, String text, String pid, Map<String,String> otherParam) {
		super();
		this.id = id;
		this.text = text;
		this.pid = pid;
		this.otherParam = otherParam;
	}

	public TreeNode(String id, String text, String pid, boolean isLeaf, boolean expanded, Map<String,String> otherParam) {
		super();
		this.id = id;
		this.text = text;
		this.pid = pid;
		this.isLeaf = isLeaf;
		this.expanded = expanded;
		this.otherParam = otherParam;
	}


	public TreeNode(String id, String text, String iconCls, String children, String pid, String img, String type,
			boolean isLeaf, boolean expanded, Map<String,String> otherParam) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.children = children;
		this.pid = pid;
		this.img = img;
		this.type = type;
		this.isLeaf = isLeaf;
		this.expanded = expanded;
		this.otherParam = otherParam;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}


	public Map<String, String> getOtherParam() {
		return otherParam;
	}


	public void setOtherParam(Map<String, String> otherParam) {
		this.otherParam = otherParam;
	}
	
	
}
