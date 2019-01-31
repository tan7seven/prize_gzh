package com.prize.prize_gzh.controller;


import com.prize.prize_gzh.utils.PageBean;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:类说明：基础控制类
 * @author: gzh
 * @date: 2018年9月23日上午10:18:50
 */
public class BaseController {

	/** 
	 * 请求 
	 **/
     protected HttpServletRequest request;
     /**
      *  响应 
      **/
     protected HttpServletResponse response;
     /** 
      * 分页信息封装 
      **/
     protected PageBean pageBean;
     /**
      * 参数封装 
      **/
     protected Map<String,Object> param;

     @ModelAttribute
     private void setReqAndRes(HttpServletRequest request,
                              HttpServletResponse response) {
          this.request = request;
          this.response = response;
          // 获取分页信息 当前第几页
          Integer start = Integer
                  .parseInt(request.getParameter("page") == null ? "1" : request
                          .getParameter("page"));
          //一页显示多少条
          Integer number = Integer
                  .parseInt(request.getParameter("rows") == null ? "20" : request
                          .getParameter("rows"));
          if(pageBean != null){
               pageBean.setStart((start - 1) * number);
               pageBean.setRows(number);
          }else {
               pageBean = new PageBean();
               pageBean.setStart((start - 1) * number);
               pageBean.setRows(number);
          }
          this.setParam();
     }

	private void setParam(){
          param = new HashMap<String, Object>();
          Map<String, String[]> temp = request.getParameterMap();
          for (Object key : temp.keySet()) {
               String k =  key.toString();
               String[] p = (String[])temp.get(k);
               if (p.length > 1){
                    param.put(k,p);
               } else {
                    Object o = p[0];
                    //处理提交参数datatime为""
                    if("".equals(o)){
                         o = null;
                    }
                    param.put(k,o);
               }
          }
     }
}
