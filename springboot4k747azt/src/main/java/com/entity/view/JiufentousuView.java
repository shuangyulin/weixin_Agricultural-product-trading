package com.entity.view;

import com.entity.JiufentousuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 纠纷投诉
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-08 10:58:38
 */
@TableName("jiufentousu")
public class JiufentousuView  extends JiufentousuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiufentousuView(){
	}
 
 	public JiufentousuView(JiufentousuEntity jiufentousuEntity){
 	try {
			BeanUtils.copyProperties(this, jiufentousuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
