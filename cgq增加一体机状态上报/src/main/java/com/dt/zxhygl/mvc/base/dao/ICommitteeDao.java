package com.dt.zxhygl.mvc.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.zxhygl.mvc.base.pojo.Committee;
import com.dt.zxhygl.mvc.base.pojo.User;

public interface ICommitteeDao extends IBaseDao<Committee> {

	/**
	 * 按用户名和密码查找用户
	 * @param operator
	 * @return
	 */
	User loginByUserNameAndPassword(User user);
	
	/**
	 * 查找用户是否重复(修改时用)
	 * @param username
	 * @param zid
	 * @return
	 */
	Committee selectByUsernameWithOutZid(@Param("username") String username,@Param("zid") String zid);

	/**
	 * 查找用户是否重复(添加时用)
	 * @param username
	 * @return
	 */
	Committee selectByUsername(String username);
	
	Committee findById(String id);
}
