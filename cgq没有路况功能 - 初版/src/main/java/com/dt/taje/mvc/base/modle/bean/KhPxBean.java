package com.dt.taje.mvc.base.modle.bean;

import java.io.Serializable;
import java.util.Collection;

public class KhPxBean  implements Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5940025314077839861L;
	
	double fen = 0.0;
	String filed = "";
	String data = "";
	double zbaj_num = 0.0;
	public double getFen() {
		return fen;
	}
	public void setFen(double fen) {
		this.fen = fen;
	}
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getZbaj_num() {
		return zbaj_num;
	}
	public void setZbaj_num(double zbaj_num) {
		this.zbaj_num = zbaj_num;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		KhPxBean pxBean = (KhPxBean)o;
		if((pxBean.getFen()-this.getFen())>0){
			return 1;
		}else if((pxBean.getFen()-this.getFen())<0){
			return -1;
		}
		return 0;
	}
}
