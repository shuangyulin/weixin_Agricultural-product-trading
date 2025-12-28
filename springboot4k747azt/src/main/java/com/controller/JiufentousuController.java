package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.JiufentousuEntity;
import com.entity.view.JiufentousuView;

import com.service.JiufentousuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 纠纷投诉
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-08 10:58:38
 */
@RestController
@RequestMapping("/jiufentousu")
public class JiufentousuController {
    @Autowired
    private JiufentousuService jiufentousuService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiufentousuEntity jiufentousu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			jiufentousu.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JiufentousuEntity> ew = new EntityWrapper<JiufentousuEntity>();



		PageUtils page = jiufentousuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiufentousu), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiufentousuEntity jiufentousu, 
		HttpServletRequest request){
        EntityWrapper<JiufentousuEntity> ew = new EntityWrapper<JiufentousuEntity>();

		PageUtils page = jiufentousuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiufentousu), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiufentousuEntity jiufentousu){
       	EntityWrapper<JiufentousuEntity> ew = new EntityWrapper<JiufentousuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiufentousu, "jiufentousu")); 
        return R.ok().put("data", jiufentousuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiufentousuEntity jiufentousu){
        EntityWrapper< JiufentousuEntity> ew = new EntityWrapper< JiufentousuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiufentousu, "jiufentousu")); 
		JiufentousuView jiufentousuView =  jiufentousuService.selectView(ew);
		return R.ok("查询纠纷投诉成功").put("data", jiufentousuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiufentousuEntity jiufentousu = jiufentousuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiufentousu,deSens);
        return R.ok().put("data", jiufentousu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiufentousuEntity jiufentousu = jiufentousuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiufentousu,deSens);
        return R.ok().put("data", jiufentousu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiufentousuEntity jiufentousu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiufentousu);
        jiufentousuService.insert(jiufentousu);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiufentousuEntity jiufentousu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiufentousu);
        jiufentousuService.insert(jiufentousu);
        return R.ok().put("data",jiufentousu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiufentousuEntity jiufentousu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiufentousu);
        //全部更新
        jiufentousuService.updateById(jiufentousu);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiufentousuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}
