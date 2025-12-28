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


import com.dao.ChargerecordDao;
import com.entity.ChargerecordEntity;
import com.service.ChargerecordService;
import com.entity.vo.ChargerecordVO;
import com.entity.view.ChargerecordView;

@Service("chargerecordService")
public class ChargerecordServiceImpl extends ServiceImpl<ChargerecordDao, ChargerecordEntity> implements ChargerecordService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChargerecordEntity> page = this.selectPage(
                new Query<ChargerecordEntity>(params).getPage(),
                new EntityWrapper<ChargerecordEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChargerecordEntity> wrapper) {
		  Page<ChargerecordView> page =new Query<ChargerecordView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ChargerecordVO> selectListVO(Wrapper<ChargerecordEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChargerecordVO selectVO(Wrapper<ChargerecordEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChargerecordView> selectListView(Wrapper<ChargerecordEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChargerecordView selectView(Wrapper<ChargerecordEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
