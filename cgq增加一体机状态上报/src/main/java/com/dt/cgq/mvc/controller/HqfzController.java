package com.dt.cgq.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dt.cgq.mvc.pojo.Fzxx;
import com.dt.common.utils.Token;
import com.dt.zxhygl.entity.DataGridReturn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/gzh/fzxx")
public class HqfzController {

    @RequestMapping("/listUi")
    public String listUi(){
        return "/cgq/gzh/list";
    }

    /**
     * 查询所有分组
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public DataGridReturn getAllGroups() {
        String  appId = "wx34bb5f16cd8ad29b";
        String  appSecret = "be567d060925f6d618d3516a85eccc2a";
        String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + Token.getToken(appId,appSecret).getAccessToken();;
        String rtn = Token.weixinRequest(url, null, "GET");
        System.out.println("分组信息：" + rtn);
        JSONObject json;
        List<Fzxx> fzxxList;
        try {
            json = new JSONObject(JSON.parseObject(rtn));
            fzxxList = JSONArray.parseArray(String.valueOf(json.get("groups")),Fzxx.class);

        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        DataGridReturn grid = null;
        grid = new DataGridReturn(fzxxList.size(), fzxxList);
        return grid;
    }


}
