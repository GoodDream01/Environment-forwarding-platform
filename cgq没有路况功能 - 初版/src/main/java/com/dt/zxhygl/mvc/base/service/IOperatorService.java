package com.dt.zxhygl.mvc.base.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.zxhygl.mvc.base.pojo.Operator;
import com.dt.zxhygl.mvc.base.pojo.User;

public interface IOperatorService extends IBaseService<Operator> {

	/**
	 * 按用户名和密码查找用户
	 * @param operator
	 * @return
	 */
	User loginByUserNameAndPassword(User user);
	
	/**
	 * 取得工作人员列表
	 * @param dwbh
	 * @return
	 */
	List<Operator> selectByGzry();
	
	/**
	 * 查找用户是否重复(修改时用)
	 * @param username
	 * @param zid
	 * @return
	 */
	Operator selectByUsernameWithOutZid(@Param("username") String username,@Param("zid") String zid);

	/**
	 * 查找用户是否重复(添加时用)
	 * @param username
	 * @return
	 */
	Operator selectByUsername(String username);
}
