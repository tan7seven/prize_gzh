package com.prize.prize_gzh.controller;

import com.prize.prize_gzh.dto.PrizeUserDto;
import com.prize.prize_gzh.entity.PrizeUserEntity;
import com.prize.prize_gzh.service.PrizeUserService;
import com.prize.prize_gzh.utils.JsonResponse;
import com.prize.prize_gzh.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Resource(name = "prizeUserService")
    private PrizeUserService prizeUserService;

    /**
     * 新增用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser.do")
    protected String addUser(@Valid @RequestBody PrizeUserDto dto, BindingResult errorMsg){
        Map<String, Object> errorMap = ValidatorUtils.fieldValidate(errorMsg);
        String er = "";
        if (errorMap != null) {
            for (String key : errorMap.keySet()) {
                er = errorMap.get(key) + ",";
            }
            return  new JsonResponse(0,er).toJSONString();
        }
        PrizeUserEntity entity = new PrizeUserEntity();
        BeanUtils.copyProperties(dto, entity);
        String openid = (String) this.request.getSession().getAttribute("openid");
        if(StringUtils.isBlank(openid)){
            openid = "ceShi";
        }
        PrizeUserEntity oldUser = prizeUserService.getByOpenid(openid);
        if(null == oldUser){entity.setOpenid(openid);
            prizeUserService.add(entity);
            return  new JsonResponse(1,"新增成功！").toJSONString();
        }
        return  new JsonResponse(0,"用户存在！").toJSONString();
    }

    /**
     * 更新用户信息
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser.do")
    protected String updateUser(@RequestBody PrizeUserDto dto){
        String openid = (String) this.request.getSession().getAttribute("openid");
        if(StringUtils.isBlank(openid)){
            openid = "ceShi";
        }
        dto.setOpenid(openid);
        int flag = prizeUserService.update(dto);
        if(flag > 0){
            return new JsonResponse(0, "更新成功！").toJSONString();
        }else{
            return new JsonResponse(1, "更新失败！").toJSONString();
        }
    }
    /**
     * 分页查询用户信息
     * @param dto
     * @param rows
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryUser.do")
    protected List<PrizeUserEntity> queryUser(PrizeUserDto dto,Integer rows, Integer page){
        return prizeUserService.getPage(rows, page, dto).getContent();
    }
    @RequestMapping("/index.do")
    protected  String index(){
        return "index";
    }
}
