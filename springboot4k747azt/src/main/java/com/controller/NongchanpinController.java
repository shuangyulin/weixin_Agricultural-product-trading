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
import com.entity.OrdersEntity;
import com.service.OrdersService;

import com.entity.NongchanpinEntity;
import com.entity.view.NongchanpinView;

import com.service.NongchanpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 农产品
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-08 10:58:37
 */
@RestController
@RequestMapping("/nongchanpin")
public class NongchanpinController {
    @Autowired
    private NongchanpinService nongchanpinService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,NongchanpinEntity nongchanpin,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shangjia")) {
			nongchanpin.setShangjiazhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<NongchanpinEntity> ew = new EntityWrapper<NongchanpinEntity>();
                if(pricestart!=null) ew.ge("price", pricestart);
                if(priceend!=null) ew.le("price", priceend);



		PageUtils page = nongchanpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpin), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,NongchanpinEntity nongchanpin, 
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        EntityWrapper<NongchanpinEntity> ew = new EntityWrapper<NongchanpinEntity>();
                if(pricestart!=null) ew.ge("price", pricestart);
                if(priceend!=null) ew.le("price", priceend);

		PageUtils page = nongchanpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpin), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( NongchanpinEntity nongchanpin){
       	EntityWrapper<NongchanpinEntity> ew = new EntityWrapper<NongchanpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( nongchanpin, "nongchanpin")); 
        return R.ok().put("data", nongchanpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(NongchanpinEntity nongchanpin){
        EntityWrapper< NongchanpinEntity> ew = new EntityWrapper< NongchanpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( nongchanpin, "nongchanpin")); 
		NongchanpinView nongchanpinView =  nongchanpinService.selectView(ew);
		return R.ok("查询农产品成功").put("data", nongchanpinView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NongchanpinEntity nongchanpin = nongchanpinService.selectById(id);
		nongchanpin.setClicktime(new Date());
		nongchanpinService.updateById(nongchanpin);
        nongchanpin = nongchanpinService.selectView(new EntityWrapper<NongchanpinEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(nongchanpin,deSens);
        return R.ok().put("data", nongchanpin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        NongchanpinEntity nongchanpin = nongchanpinService.selectById(id);
		nongchanpin.setClicktime(new Date());
		nongchanpinService.updateById(nongchanpin);
        nongchanpin = nongchanpinService.selectView(new EntityWrapper<NongchanpinEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(nongchanpin,deSens);
        return R.ok().put("data", nongchanpin);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        NongchanpinEntity nongchanpin = nongchanpinService.selectById(id);
        if(type.equals("1")) {
        	nongchanpin.setThumbsupnum(nongchanpin.getThumbsupnum()+1);
        } else {
        	nongchanpin.setCrazilynum(nongchanpin.getCrazilynum()+1);
        }
        nongchanpinService.updateById(nongchanpin);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NongchanpinEntity nongchanpin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(nongchanpin);
        nongchanpinService.insert(nongchanpin);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NongchanpinEntity nongchanpin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(nongchanpin);
        nongchanpinService.insert(nongchanpin);
        return R.ok().put("data",nongchanpin.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody NongchanpinEntity nongchanpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongchanpin);
        //全部更新
        nongchanpinService.updateById(nongchanpin);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        nongchanpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {

        Wrapper<NongchanpinEntity> wrapper = new EntityWrapper<NongchanpinEntity>();

        // 从map中获取remindStart和remindEnd
    Object remindStart = map.get("remindstart") != null ? map.get("remindstart").toString() : null;
    Object remindEnd = map.get("remindend") != null ? map.get("remindend").toString() : null;
        if ("2".equals(type)) {
            Date startDate = null;
            Date endDate = null;
            if (null != remindStart) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindStart.toString()));
                startDate = c.getTime();
            }
            if (null != remindEnd) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindEnd.toString()));
                endDate = c.getTime();
            }
            if (startDate != null && endDate != null) {
                if (startDate.before(endDate)) {
                    wrapper.ge(columnName, startDate).le(columnName, endDate);
                } else {
                    wrapper.ge(columnName, startDate).or().le(columnName, endDate);
                }
            } else if (startDate == null && endDate != null) {
                wrapper.le(columnName, endDate);
            } else if (startDate != null && endDate == null) {
                wrapper.ge(columnName, startDate);
            }
        } else {
            if (remindStart != null && remindEnd != null) {
                if (Double.parseDouble(remindStart.toString()) < Double.parseDouble(remindEnd.toString())) {
                    wrapper.ge(columnName, remindStart).le(columnName, remindEnd);
                } else {
                    wrapper.ge(columnName, remindStart).or().le(columnName, remindEnd);
                }
            } else if (remindStart == null && remindEnd != null) {
                wrapper.le(columnName, remindEnd);
            } else if (remindStart != null && remindEnd == null) {
                wrapper.ge(columnName, remindStart);
            }
        }

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shangjia")) {
			wrapper.andNew().eq("shangjiazhanghao", (String)request.getSession().getAttribute("username"));
		}
        List<NongchanpinEntity> list = nongchanpinService.selectList(wrapper);
        Map<String,Object> res= new HashMap<>();
        res.put("count", list.size());
        res.put("data",list.stream().map(NongchanpinEntity::getChanpinmingcheng).collect(Collectors.toList()));
        return R.ok(res);
	}
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,NongchanpinEntity nongchanpin, HttpServletRequest request,String pre){
        EntityWrapper<NongchanpinEntity> ew = new EntityWrapper<NongchanpinEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = nongchanpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpin), params), params));
        return R.ok().put("data", page);
    }


        /**
     * 协同算法（按用户购买推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,NongchanpinEntity nongchanpin, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String goodtypeColumn = "chanpinleixing";
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>().eq("userid", userId).eq("tablename", "nongchanpin").orderBy("addtime", false));
        List<String> goodtypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<NongchanpinEntity> nongchanpinList = new ArrayList<NongchanpinEntity>();
	//去重
    	List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
    	for(OrdersEntity o1 : orders) {
    		boolean addFlag = true;
    		for(OrdersEntity o2 : ordersDist) {
    			if(o1.getGoodid()==o2.getGoodid() || o1.getGoodtype().equals(o2.getGoodtype())) {
    				addFlag = false;
    				break;
    			}
    		}
    		if(addFlag) ordersDist.add(o1);
    	}
        if(ordersDist!=null && ordersDist.size()>0) {
                for(OrdersEntity o : ordersDist) {
                        nongchanpinList.addAll(nongchanpinService.selectList(new EntityWrapper<NongchanpinEntity>().eq(goodtypeColumn, o.getGoodtype())));
                }
        }
        EntityWrapper<NongchanpinEntity> ew = new EntityWrapper<NongchanpinEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = nongchanpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpin), params), params));
        List<NongchanpinEntity> pageList = (List<NongchanpinEntity>)page.getList();
        if(nongchanpinList.size()<limit) {
                int toAddNum = (limit-nongchanpinList.size())<=pageList.size()?(limit-nongchanpinList.size()):pageList.size();
                for(NongchanpinEntity o1 : pageList) {
                    boolean addFlag = true;
                    for(NongchanpinEntity o2 : nongchanpinList) {
                        if(o1.getId().intValue()==o2.getId().intValue()) {
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag) {
                        nongchanpinList.add(o1);
                        if(--toAddNum==0) break;
                    }   
                }
        } else if(nongchanpinList.size()>limit) {
            nongchanpinList = nongchanpinList.subList(0, limit);
        }
        page.setList(nongchanpinList);
        return R.ok().put("data", page);
    }










}
