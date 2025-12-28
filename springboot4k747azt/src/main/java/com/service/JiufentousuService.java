package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiufentousuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JiufentousuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JiufentousuView;


/**
 * 纠纷投诉
 *
 * @author 
 * @email 
 * @date 2025-01-08 10:58:38
 */
public interface JiufentousuService extends IService<JiufentousuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiufentousuVO> selectListVO(Wrapper<JiufentousuEntity> wrapper);
   	
   	JiufentousuVO selectVO(@Param("ew") Wrapper<JiufentousuEntity> wrapper);
   	
   	List<JiufentousuView> selectListView(Wrapper<JiufentousuEntity> wrapper);
   	
   	JiufentousuView selectView(@Param("ew") Wrapper<JiufentousuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiufentousuEntity> wrapper);

   	

}

