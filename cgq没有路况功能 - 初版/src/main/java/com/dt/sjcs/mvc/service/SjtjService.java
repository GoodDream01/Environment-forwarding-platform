package com.dt.sjcs.mvc.service;

import com.dt.sjcs.mvc.pojo.Sjtj;

import java.util.List;

public interface SjtjService {

	List<Sjtj> findSbbh();

	List<Sjtj> TjByDay(String sbid, String kssj, String jssj);

	List<Sjtj> TjByHour(String sbid,String kssj,String jssj);

}
