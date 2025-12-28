package com.entity.model;

import com.entity.ChargerecordEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 充值记录表
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-01-08 10:58:38
 */
public class ChargerecordModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用户名
	 */
	
	private String username;
		
	/**
	 * 角色
	 */
	
	private String role;
		
	/**
	 * 金额
	 */
	
	private Double amount;
				
	
	/**
	 * 设置：用户名
	 */
	 
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
				
	
	/**
	 * 设置：角色
	 */
	 
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * 获取：角色
	 */
	public String getRole() {
		return role;
	}
				
	
	/**
	 * 设置：金额
	 */
	 
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	/**
	 * 获取：金额
	 */
	public Double getAmount() {
		return amount;
	}
			
}
