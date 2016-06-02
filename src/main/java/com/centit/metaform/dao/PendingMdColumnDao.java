package com.centit.metaform.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.metaform.po.PendingMdColumn;



/**
 * PendingMdColumnDao  Repository.
 * create by scaffold 2016-06-01 
 
 * 未落实字段元数据表null   
*/

@Repository
public class PendingMdColumnDao extends BaseDaoImpl<PendingMdColumn,java.lang.Long>
	{

	public static final Log log = LogFactory.getLog(PendingMdColumnDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("tableId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("columnName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("fieldLabelName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("columnComment" , CodeBook.EQUAL_HQL_ID);

			filterField.put("columnOrder" , CodeBook.EQUAL_HQL_ID);

			filterField.put("columnType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("maxLength" , CodeBook.EQUAL_HQL_ID);

			filterField.put("scale" , CodeBook.EQUAL_HQL_ID);

			filterField.put("accessType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("mandatory" , CodeBook.EQUAL_HQL_ID);

			filterField.put("primarykey" , CodeBook.EQUAL_HQL_ID);

			filterField.put("columnState" , CodeBook.EQUAL_HQL_ID);

			filterField.put("referenceType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("referenceData" , CodeBook.EQUAL_HQL_ID);

			filterField.put("validateRegex" , CodeBook.EQUAL_HQL_ID);

			filterField.put("validateInfo" , CodeBook.EQUAL_HQL_ID);

			filterField.put("defaultValue" , CodeBook.EQUAL_HQL_ID);

			filterField.put("lastModifyDate" , CodeBook.EQUAL_HQL_ID);

			filterField.put("recorder" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	} 
}
