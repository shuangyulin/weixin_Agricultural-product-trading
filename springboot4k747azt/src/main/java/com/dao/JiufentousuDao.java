package com.dao;

import com.entity.JiufentousuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JiufentousuVO;
import com.entity.view.JiufentousuView;


/**
 * 纠纷投诉
 * 
 * @author 
 * @email 
 * @date 2025-01-08 10:58:38
 */
public interface JiufentousuDao extends BaseMapper<JiufentousuEntity> {
	
	List<JiufentousuVO> selectListVO(@Param("ew") Wrapper<JiufentousuEntity> wrapper);
	
	JiufentousuVO selectVO(@Param("ew") Wrapper<JiufentousuEntity> wrapper);
	
	List<JiufentousuView> selectListView(@Param("ew") Wrapper<JiufentousuEntity> wrapper);

	List<JiufentousuView> selectListView(Pagination page,@Param("ew") Wrapper<JiufentousuEntity> wrapper);

	
	JiufentousuView selectView(@Param("ew") Wrapper<JiufentousuEntity> wrapper);
	

}
