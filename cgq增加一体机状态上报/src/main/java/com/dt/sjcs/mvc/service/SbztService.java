package com.dt.sjcs.mvc.service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.sjcs.mvc.pojo.Sbzt;
import com.dt.zxhygl.mvc.base.service.IBaseService;

import java.util.List;

public interface SbztService extends IBaseService<Sbzt> {
    List<Sbzt> findByPage2(PageBounds pageBounds);
}
