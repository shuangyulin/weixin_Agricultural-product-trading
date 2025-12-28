package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 纠纷投诉
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-01-08 10:58:38
 */
@TableName("jiufentousu")
public class JiufentousuEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JiufentousuEntity() {
		
	}
	
	public JiufentousuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 产品名称
	 */
					
	private String chanpinmingcheng;
	
	/**
	 * 产品类型
	 */
					
	private String chanpinleixing;
	
	/**
	 * 图片
	 */
					
	private String tupian;
	
	/**
	 * 商家姓名
	 */
					
	private String shangjiaxingming;
	
	/**
	 * 投诉内容
	 */
					
	private String tousuneirong;
	
	/**
	 * 投诉日期
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date tousuriqi;
	
	/**
	 * 用户账号
	 */
					
	private String yonghuzhanghao;
	
	/**
	 * 用户姓名
	 */
					
	private String yonghuxingming;
	
	/**
	 * 回复内容
	 */
					
	private String shhf;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：产品名称
	 */
	public void setChanpinmingcheng(String chanpinmingcheng) {
		this.chanpinmingcheng = chanpinmingcheng;
	}
	/**
	 * 获取：产品名称
	 */
	public String getChanpinmingcheng() {
		return chanpinmingcheng;
	}
	/**
	 * 设置：产品类型
	 */
	public void setChanpinleixing(String chanpinleixing) {
		this.chanpinleixing = chanpinleixing;
	}
	/**
	 * 获取：产品类型
	 */
	public String getChanpinleixing() {
		return chanpinleixing;
	}
	/**
	 * 设置：图片
	 */
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
	/**
	 * 设置：商家姓名
	 */
	public void setShangjiaxingming(String shangjiaxingming) {
		this.shangjiaxingming = shangjiaxingming;
	}
	/**
	 * 获取：商家姓名
	 */
	public String getShangjiaxingming() {
		return shangjiaxingming;
	}
	/**
	 * 设置：投诉内容
	 */
	public void setTousuneirong(String tousuneirong) {
		this.tousuneirong = tousuneirong;
	}
	/**
	 * 获取：投诉内容
	 */
	public String getTousuneirong() {
		return tousuneirong;
	}
	/**
	 * 设置：投诉日期
	 */
	public void setTousuriqi(Date tousuriqi) {
		this.tousuriqi = tousuriqi;
	}
	/**
	 * 获取：投诉日期
	 */
	public Date getTousuriqi() {
		return tousuriqi;
	}
	/**
	 * 设置：用户账号
	 */
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}
	/**
	 * 设置：回复内容
	 */
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	/**
	 * 获取：回复内容
	 */
	public String getShhf() {
		return shhf;
	}

}
