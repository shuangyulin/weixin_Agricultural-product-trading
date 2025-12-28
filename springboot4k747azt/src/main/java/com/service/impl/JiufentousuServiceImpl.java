package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.JiufentousuDao;
import com.entity.JiufentousuEntity;
import com.service.JiufentousuService;
import com.entity.vo.JiufentousuVO;
import com.entity.view.JiufentousuView;

@Service("jiufentousuService")
public class JiufentousuServiceImpl extends ServiceImpl<JiufentousuDao, JiufentousuEntity> implements JiufentousuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiufentousuEntity> page = this.selectPage(
                new Query<JiufentousuEntity>(params).getPage(),
                new EntityWrapper<JiufentousuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiufentousuEntity> wrapper) {
		  Page<JiufentousuView> page =new Query<JiufentousuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JiufentousuVO> selectListVO(Wrapper<JiufentousuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiufentousuVO selectVO(Wrapper<JiufentousuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiufentousuView> selectListView(Wrapper<JiufentousuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiufentousuView selectView(Wrapper<JiufentousuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
