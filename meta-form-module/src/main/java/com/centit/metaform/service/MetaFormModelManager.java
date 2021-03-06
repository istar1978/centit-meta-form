package com.centit.metaform.service;

import com.alibaba.fastjson.JSONArray;
import com.centit.framework.jdbc.service.BaseEntityManager;
import com.centit.metaform.po.MetaFormModel;
import com.centit.metaform.po.ModelDataField;
import com.centit.support.database.utils.PageDesc;

import java.util.List;
import java.util.Map;

/**
 * MetaFormModel  Service.
 * create by scaffold 2016-06-02 
 
 * 通用模块管理null   
*/

public interface MetaFormModelManager extends BaseEntityManager<MetaFormModel,java.lang.String> 
{

    JSONArray listMetaFormModelsAsJson(
            String[] fields,
            Map<String, Object> filterMap, PageDesc pageDesc);

    void updateMetaFormModel(MetaFormModel mtaFormModel);

    JSONArray addTableNameToList(JSONArray listObjects);

    List<ModelDataField> listModelDataFields(String modelCode);

}
