package com.dt.sjcs.mvc.dao;

import com.dt.sjcs.mvc.pojo.Sjtj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SjtjDao{

	List<Sjtj> findSbbh();

	List<Sjtj> TjByDay(@Param("sbid")String sbid, @Param("kssj")String kssj, @Param("jssj")String jssj);

	List<Sjtj> TjByHour(@Param("sbid")String sbid,@Param("kssj")String kssj,@Param("jssj")String jssj);
	
}
