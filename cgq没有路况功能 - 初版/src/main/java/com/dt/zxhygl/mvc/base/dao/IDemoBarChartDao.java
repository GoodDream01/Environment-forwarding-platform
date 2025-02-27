package com.dt.zxhygl.mvc.base.dao;

import org.apache.ibatis.annotations.Param;

public interface IDemoBarChartDao {

	int getHyCountByMonth(@Param("year") String year,@Param("month") String month);
}
