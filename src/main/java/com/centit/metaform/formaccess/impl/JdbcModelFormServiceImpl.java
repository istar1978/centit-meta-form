package com.centit.metaform.formaccess.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.dde.dao.DatabaseInfoDao;
import com.centit.dde.po.DatabaseInfo;
import com.centit.framework.core.dao.PageDesc;
import com.centit.metaform.dao.MetaColumnDao;
import com.centit.metaform.dao.MetaFormModelDao;
import com.centit.metaform.dao.MetaTableDao;
import com.centit.metaform.dao.ModelDataFieldDao;
import com.centit.metaform.formaccess.FieldTemplateOptions;
import com.centit.metaform.formaccess.FieldValidator;
import com.centit.metaform.formaccess.FormField;
import com.centit.metaform.formaccess.ModelFormService;
import com.centit.metaform.formaccess.ModelRuntimeContext;
import com.centit.metaform.po.MetaColumn;
import com.centit.metaform.po.MetaFormModel;
import com.centit.metaform.po.MetaTable;
import com.centit.metaform.po.ModelDataField;
import com.centit.support.database.DataSourceDescription;
import com.centit.support.database.jsonmaptable.JsonObjectDao;

@Service(value="jdbcModelFormService")
public class JdbcModelFormServiceImpl implements ModelFormService {

    @Resource(name = "databaseInfoDao") 
    private DatabaseInfoDao databaseInfoDao;

    @Resource
    private MetaTableDao tableDao;

    @Resource
    private MetaColumnDao columnDao;
    
    @Resource
    private MetaFormModelDao formModelDao;
    
    @Resource
    private ModelDataFieldDao formFieldDao;
    
	@Override
	public JdbcModelRuntimeContext createRuntimeContext(String modelCode) {
		
		JdbcModelRuntimeContext rc = new JdbcModelRuntimeContext(modelCode);		
		DataSourceDescription dbc = new DataSourceDescription();	  
		dbc.setConnUrl("jdbc:oracle:thin:@192.168.131.81:1521:orcl");
		dbc.setUsername("metaform");
		dbc.setPassword("metaform");
		rc.setDataSource(dbc);
		
		MetaTable tableInfo = new MetaTable();
		tableInfo.setTableName("TEST_TABLE");
		tableInfo.setTableLabelName("通讯录");
		MetaColumn field = new MetaColumn();
		field.setColumnName("ID");
		field.setColumnType("Number(10)");
		field.setMaxLength(10);
		field.setScale(0);
		field.setMandatory("T");
		tableInfo.getColumns().add(field);
		
		field = new MetaColumn();
		field.setColumnName("USER_NAME");
		field.setColumnType("varchar2");
		field.setMaxLength(50);
		tableInfo.getColumns().add(field);
		
		field = new MetaColumn();
		field.setColumnName("USER_PHONE");
		field.setColumnType("varchar2");
		field.setMaxLength(20);
		field.setAutoCreateRule("C");
		field.setAutoCreateParam("'110'");
		tableInfo.getColumns().add(field);			
		
		tableInfo.getPkColumns().add("ID");
		
		rc.setTableInfo(tableInfo);
		
		
		FormField ff = new FormField();
		ff.setKey("id");
		ff.setType("input");
		FieldTemplateOptions templateOptions = new FieldTemplateOptions();
		templateOptions.setLabel("编号：");
		templateOptions.setPlaceholder("请输入数字。");
		ff.setTemplateOptions(templateOptions);
		rc.addFormField(ff);
		
		ff = new FormField();
		ff.setKey("userName");
		ff.setType("input");
		templateOptions = new FieldTemplateOptions();
		templateOptions.setLabel("姓名：");
		templateOptions.setPlaceholder("请输入完整的姓名。");
		ff.setTemplateOptions(templateOptions);
		rc.addFormField(ff);
		

		ff = new FormField();
		ff.setKey("userPhone");
		ff.setType("input");
		templateOptions = new FieldTemplateOptions();
		templateOptions.setLabel("电话：");
		templateOptions.setPlaceholder("请输入电话号码。");
		ff.setTemplateOptions(templateOptions);
		rc.addFormField(ff);
		
		rc.setMetaFormModel(new MetaFormModel());
		
		return rc;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public JdbcModelRuntimeContext createRuntimeContextFromDB(String modelCode) {
		
		JdbcModelRuntimeContext rc = new JdbcModelRuntimeContext(modelCode);		
		
		MetaFormModel mfm = formModelDao.getObjectById(modelCode);
		MetaTable mtab = mfm.getMdTable();
		DatabaseInfo mdb = databaseInfoDao.getObjectById( mtab.getDatabaseCode());
		
		
		Set<ModelDataField> fields = mfm.getModelDataFields();
		for(ModelDataField field :fields){
			FormField ff = new FormField();
			MetaColumn column = mtab.findFieldByColumn( field.getColumnName() );
			ff.setKey(column.getPropertyName());
			switch(column.getJavaType()){
			case "Date":
				ff.setType("input");
				break;
			default:
				ff.setType("input");
				break;
			
			}			
			ff.setDefaultValue(column.getDefaultValue());
			FieldTemplateOptions templateOptions = new FieldTemplateOptions();
			templateOptions.setLabel(column.getColumnName());
			templateOptions.setPlaceholder(field.getInputHint());
			templateOptions.setPattern(column.getValidateRegex());
			ff.setTemplateOptions(templateOptions);
			
			FieldValidator  valid = new FieldValidator();
			valid.setMessage(column.getValidateInfo());
			ff.addValidator(column.getPropertyName(),valid);
			rc.addFormField(ff);
		}
		
		
		rc.setTableInfo(mtab);
		
		
		DataSourceDescription dbc = new DataSourceDescription();
		dbc.setDatabaseCode(mdb.getDatabaseCode());
		dbc.setConnUrl(mdb.getDatabaseUrl());
		dbc.setUsername(mdb.getUsername());
		dbc.setPassword(mdb.getPassword());
		
		rc.setDataSource(dbc);
		
		return rc;
	}

	@Override
	public JSONArray listObjectsByFilter(ModelRuntimeContext rc, Map<String, Object> filters) {
		JsonObjectDao dao = rc.getJsonObjectDao();
		try {
			return dao.listObjectsByProperties(filters);
		} catch (SQLException | IOException e) {
			return null;
		}
	}
	
	@Override
	public JSONArray listObjectsByFilter(ModelRuntimeContext rc, Map<String, Object> filters, PageDesc pageDesc) {
		JsonObjectDao dao = rc.getJsonObjectDao();
		try {
			JSONArray ja = dao.listObjectsByProperties(filters,
						(pageDesc.getPageNo()-1)>0? (pageDesc.getPageNo()-1)*pageDesc.getPageSize():0,
						pageDesc.getPageSize());
			Long ts = dao.fetchObjectsCount(filters);
			if(ts!=null)
				pageDesc.setTotalRows(ts.intValue());
			return ja;
		} catch (SQLException | IOException e) {
			return null;
		}
	}
	
	
	@Override
	public JSONObject getObjectByProperties(ModelRuntimeContext rc,Map<String, Object> properties){
		JsonObjectDao dao = rc.getJsonObjectDao();
		try {
			return dao.getObjectByProperties(properties);
		} catch (SQLException | IOException e) {
			return null;
		}
	}

	@Override
	public void saveNewObject(ModelRuntimeContext rc, Map<String, Object> object) throws SQLException {
		JsonObjectDao dao = rc.getJsonObjectDao();
		dao.saveNewObject(object);
	}

	@Override
	public void updateObject(ModelRuntimeContext rc, Map<String, Object> object) throws SQLException {
		JsonObjectDao dao = rc.getJsonObjectDao();
		dao.updateObject(object);
	}

	@Override
	public void deleteObjectById(ModelRuntimeContext rc, Map<String, Object> keyValue) throws SQLException {
		JsonObjectDao dao = rc.getJsonObjectDao();
		dao.deleteObjectById(keyValue);
	}

	@Override
	public Map<String, Object> createNewPk(ModelRuntimeContext rc) throws SQLException {
		//rc.getTableinfo().getPkColumns()
		//TableField findFieldByColumn(String name)
		return null;
	}

	@Override
	public JSONObject createInitialObject(ModelRuntimeContext rc) throws SQLException {
		// 查找所有 有自动生成策略的字段，并生成对应的值
		return null;
	}

}
