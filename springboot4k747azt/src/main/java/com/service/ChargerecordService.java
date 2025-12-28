package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChargerecordEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChargerecordVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChargerecordView;


/**
 * 充值记录表
 *
 * @author 
 * @email 
 * @date 2025-01-08 10:58:38
 */
public interface ChargerecordService extends IService<ChargerecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChargerecordVO> selectListVO(Wrapper<ChargerecordEntity> wrapper);
   	
   	ChargerecordVO selectVO(@Param("ew") Wrapper<ChargerecordEntity> wrapper);
   	
   	List<ChargerecordView> selectListView(Wrapper<ChargerecordEntity> wrapper);
   	
   	ChargerecordView selectView(@Param("ew") Wrapper<ChargerecordEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChargerecordEntity> wrapper);

   	

}

