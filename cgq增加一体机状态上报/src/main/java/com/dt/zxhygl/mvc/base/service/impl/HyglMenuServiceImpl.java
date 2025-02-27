package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.dt.zxhygl.mvc.base.dao.IMenuDao;
import com.dt.zxhygl.mvc.base.pojo.Menu;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.IMenuService;
import com.dt.zxhygl.utils.UserUtils;

@Service
public class HyglMenuServiceImpl implements IMenuService {

	@Resource
	private IMenuDao iMenuDao;
	
	@Override
	public Menu selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return iMenuDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Menu> selectMenuByName(String value) {
		// TODO Auto-generated method stub
		return iMenuDao.selectMenuByName(value);
	}

	@Override
	public int insertMenu(Menu menu) {
		// TODO Auto-generated method stub
		return iMenuDao.insertMenu(menu);
	}

	@Override
	public int updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		return iMenuDao.updateMenu(menu);
	}

	@Override
	public int deleteMenu(Menu menu) {
		// TODO Auto-generated method stub
		return iMenuDao.deleteMenu(menu);
	}

	@Override
	public List<Menu> getLeftMenu(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result = "";
		User user = UserUtils.getCurrentUser();
		String jslx = user.getRole();
		String dwbh = "";
		List<Menu> mlist = iMenuDao.selectAllMenu(jslx,dwbh);
		
		return mlist;
	}

	@Override
	public List<Menu> selectMenuByPcode(String value) {
		// TODO Auto-generated method stub
		return iMenuDao.selectMenuByPcode(value);
	}

	@Override
	public List<Menu> getAllMenu() {
		// TODO Auto-generated method stub
		return iMenuDao.getAllMenu();
	}

	@Override
	public boolean updateMenuRole(List<Menu> list) throws Exception{
		// TODO Auto-generated method stub
		int i = iMenuDao.updateMenuRole(list);
		
		return i>0;
	}

}
