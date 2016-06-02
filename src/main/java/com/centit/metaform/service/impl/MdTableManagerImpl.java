package com.centit.metaform.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.core.dao.PageDesc;
import com.centit.framework.hibernate.dao.SysDaoOptUtils;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.metaform.dao.MdTableDao;
import com.centit.metaform.po.MdTable;
import com.centit.metaform.service.MdTableManager;

/**
 * MdTable  Service.
 * create by scaffold 2016-06-02 
 
 * 表元数据表状态分为 系统/查询/更新
系统，不可以做任何操作
查询，仅用于通用查询模块，不可以更新
更新，可以更新
   
*/
@Service
public class MdTableManagerImpl 
		extends BaseEntityManagerImpl<MdTable,java.lang.Long,MdTableDao>
	implements MdTableManager{

	public static final Log log = LogFactory.getLog(MdTableManager.class);

	
	private MdTableDao mdTableDao ;
	
	@Resource(name = "mdTableDao")
    @NotNull
	public void setMdTableDao(MdTableDao baseDao)
	{
		this.mdTableDao = baseDao;
		setBaseDao(this.mdTableDao);
	}
	
/*
 	@PostConstruct
    public void init() {
        
    }
 	
 */
	@Override
    @Transactional(propagation=Propagation.REQUIRED) 
	public JSONArray listMdTablesAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc){
			
		return SysDaoOptUtils.listObjectsAsJson(baseDao, fields, MdTable.class,
    			filterMap, pageDesc);
	}
	
}
