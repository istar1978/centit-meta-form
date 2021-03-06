package com.centit.metaform.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.centit.framework.core.po.EntityWithTimestamp;
import com.centit.metaform.formaccess.FieldType;
import com.centit.support.database.metadata.SimpleTableField;
import com.centit.support.database.metadata.TableField;
import com.centit.support.database.utils.DBType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


/**
 * create by scaffold 2016-06-01 
 
 
  未落实字段元数据表null   
*/
@Entity
@Table(name = "F_PENDING_META_COLUMN")
public class PendingMetaColumn implements TableField,EntityWithTimestamp, java.io.Serializable {
    private static final long serialVersionUID =  1L;

//    @EmbeddedId
//    private PendingMetaColumnId cid;

    /**
     * 字段名称 null
     */
    @Column(name = "TABLE_ID")
    @NotBlank(message = "字段不能为空")
    private Long  tableId;

    /**
     * 字段名称 null
     */
    @Column(name = "COLUMN_NAME")
    @NotBlank(message = "字段不能为空")
    private String  columnName;

    /**
     * 字段名称 null
     */
    @Column(name = "FIELD_LABEL_NAME")
    @NotBlank(message = "字段不能为空")
    @Length(max = 64, message = "字段长度不能大于{max}")
    private String  fieldLabelName;
    /**
     * 字段描述 null
     */
    @Column(name = "COLUMN_COMMENT")
    @Length(max = 256, message = "字段长度不能大于{max}")
    private String  columnComment;
    /**
     * 显示次序 null
     */
    @Column(name = "COLUMN_ORDER")
    private Long  columnOrder;
    /**
     * 字段类型 null
     */
    @Column(name = "COLUMN_TYPE")
    @NotBlank(message = "字段不能为空")
    @Length(max = 32, message = "字段长度不能大于{max}")
    private String  columnFieldType;
    /**
     * 字段长度 precision
     */
    @Column(name = "MAX_LENGTH")
    private Integer  maxLengthM;
    /**
     * 字段精度 null
     */
    @Column(name = "SCALE")
    private Integer  scaleM;
    /**
     * 字段类别 null
     */
    @Column(name = "ACCESS_TYPE")
    @NotBlank(message = "字段不能为空")
    @Length(message = "字段长度不能大于{max}")
    private String  accessType;
    /**
     * 是否必填 null
     */
    @Column(name = "MANDATORY")
    @Length( message = "字段长度不能大于{max}")
    private String  mandatory;
    /**
     * 是否为主键 null
     */
    @Column(name = "PRIMARYKEY")
    @Length( message = "字段长度不能大于{max}")
    private String  primarykey;
    /**
     * 状态 null
     */
    @Column(name = "COLUMN_STATE")
    //@NotBlank(message = "字段不能为空")
    @Length( message = "字段长度不能大于{max}")
    private String  columnState;
    /**
     * 引用类型 0：没有：1： 数据字典 2：JSON表达式 3：sql语句  Y：年份 M：月份
     */
    @Column(name = "REFERENCE_TYPE")
    @Length(message = "字段长度不能大于{max}")
    private String  referenceType;
    /**
     * 引用数据 根据paramReferenceType类型（1,2,3）填写对应值
     */
    @Column(name = "REFERENCE_DATA")
    @Length(max = 1000, message = "字段长度不能大于{max}")
    private String  referenceData;
    /**
     * 约束表达式 regex表达式
     */
    @Column(name = "VALIDATE_REGEX")
    @Length(max = 200, message = "字段长度不能大于{max}")
    private String  validateRegex;
    /**
     * 约束提示 约束不通过提示信息
     */
    @Column(name = "VALIDATE_INFO")
    @Length(max = 200, message = "字段长度不能大于{max}")
    private String  validateInfo;

    /**
     * 自动生成规则   C 常量  U uuid S sequence
     */
    @Column(name = "AUTO_CREATE_RULE")
    @Length(max = 200, message = "字段长度不能大于{max}")
    private String  autoCreateRule;

    /**
     * 自动生成参数
     */
    @Column(name = "AUTO_CREATE_PARAM")
    @Length(max = 200, message = "字段长度不能大于{max}")
    private String  autoCreateParam;

    /**
     * 更改时间 null
     */
    @Column(name = "LAST_MODIFY_DATE")
    private Date  lastModifyDate;
    /**
     * 更改人员 null
     */
    @Column(name = "RECORDER")
    @Length(max = 8, message = "字段长度不能大于{max}")
    private String  recorder;

    @Transient
    private DBType databaseType;

    public void setDatabaseType(DBType databaseType) {
        this.databaseType = databaseType;
    }
    // Constructors
    /** default constructor */
    public PendingMetaColumn() {
        this.columnState="0";
    }

    public PendingMetaColumn(PendingMetaTable mdTable, String columnName) {
//        this.cid= new PendingMetaColumnId(mdTable,columnName);
        this.tableId = mdTable.getTableId();
        this.columnName = columnName;
        this.columnState="0";
    }

    /** minimal constructor */
//    public PendingMetaColumn(
//        PendingMetaColumnId cid,String  fieldLabelName,String  columnType,String  accessType,String  columnState) {
//        this.cid=cid;
//        this.fieldLabelName= fieldLabelName;
//        this.columnFieldType= columnType;
//        this.accessType= accessType;
//        this.columnState= columnState;
//    }

/** full constructor */
    public PendingMetaColumn(
            PendingMetaColumnId cid,String  fieldLabelName,String  columnComment,Long  columnOrder,String  columnType,
            Integer  maxLength,Integer  scale,String  accessType,String  mandatory,String  primarykey,String  columnState,String  referenceType,String  referenceData,String  validateRegex,String  validateInfo,String  defaultValue,Date  lastModifyDate,String  recorder) {

//        this.cid=cid;
        this.fieldLabelName= fieldLabelName;
        this.columnComment= columnComment;
        this.columnOrder= columnOrder;
        this.columnFieldType= columnType;
        this.maxLengthM= maxLength;
        this.scaleM= scale;
        this.accessType= accessType;
        this.mandatory= mandatory;
        this.primarykey= primarykey;
        this.columnState= columnState;
        this.referenceType= referenceType;
        this.referenceData= referenceData;
        this.validateRegex= validateRegex;
        this.validateInfo= validateInfo;
        this.lastModifyDate= lastModifyDate;
        this.recorder= recorder;
    }


  
    public Long getTableId() {
//        return this.cid.getTableId();
        return this.tableId;
    }



    public void setTableId(Long tableId) {
//        if(null==this.cid)
//            this.cid=new PendingMetaColumnId();
//        this.cid.setTableId(tableId);
        this.tableId = tableId;
    }
    // Property accessors
  
    public String getColumnName() {
//        return this.getCid().getColumnName();
        return this.columnName;
    }

    public void setColumnName(String columnName) {
//        this.getCid().setColumnName(columnName);
        this.columnName = columnName;
    }
  
    public String getFieldLabelName() {
        return this.fieldLabelName;
    }

    public void setFieldLabelName(String fieldLabelName) {
        this.fieldLabelName = fieldLabelName;
    }
  
    public String getColumnComment() {
        return this.columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
  
    public Long getColumnOrder() {
        return this.columnOrder;
    }

    public void setColumnOrder(Long columnOrder) {
        this.columnOrder = columnOrder;
    }
  
    public String getColumnFieldType() {
        return this.columnFieldType;
    }

    public void setColumnFieldType(String columnType) {
        this.columnFieldType = columnType;
    }

    public String getAccessType() {
        return this.accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
  
    public String getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }
  
    public String getPrimarykey() {
        return this.primarykey;
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }
  
    public String getColumnState() {
        return this.columnState;
    }

    public void setColumnState(String columnState) {
        this.columnState = columnState;
    }
  
    public String getReferenceType() {
        return this.referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }
  
    public String getReferenceData() {
        return this.referenceData;
    }

    public void setReferenceData(String referenceData) {
        this.referenceData = referenceData;
    }
  
    public String getValidateRegex() {
        return this.validateRegex;
    }

    public void setValidateRegex(String validateRegex) {
        this.validateRegex = validateRegex;
    }
  
    public String getValidateInfo() {
        return this.validateInfo;
    }

    public void setValidateInfo(String validateInfo) {
        this.validateInfo = validateInfo;
    }
  
  
    public Date getLastModifyDate() {
        return this.lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
  
    public String getRecorder() {
        return this.recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getAutoCreateRule() {
        return autoCreateRule;
    }

    public void setAutoCreateRule(String autoCreateRule) {
        this.autoCreateRule = autoCreateRule;
    }

    public String getAutoCreateParam() {
        return autoCreateParam;
    }

    public void setAutoCreateParam(String autoCreateParam) {
        this.autoCreateParam = autoCreateParam;
    }
    public void setMaxLengthM(Integer maxLength) {
        this.maxLengthM = maxLength;
    }
    public void setScaleM(Integer scale) {
        this.scaleM = scale;
    }

    public Integer getMaxLengthM() {
        return this.maxLengthM;
    }

    public Integer getScaleM() {
        return this.scaleM;
    }

//    public PendingMetaColumnId getCid() {
//        if(null==this.cid)
//            this.cid=new PendingMetaColumnId();
//        return cid;
//    }
//    public void setCid(PendingMetaColumnId cid1) {
//        if(null==cid1.getTableId())
//            this.cid=null;
//        else
//            this.cid = cid1;
//    }
    public PendingMetaColumn copy(PendingMetaColumn other){
  
//        this.setCid(other.getCid());
        this.fieldLabelName= other.getFieldLabelName();
        this.columnComment= other.getColumnComment();
        this.columnOrder= other.getColumnOrder();
        this.columnFieldType= other.getColumnFieldType();
        this.maxLengthM= other.getMaxLengthM();
        this.scaleM= other.getScaleM();
        this.accessType= other.getAccessType();
        this.mandatory= other.isMandatory()?"T":"F";
        this.primarykey= other.getPrimarykey();
        this.columnState= other.getColumnState();
        this.referenceType= other.getReferenceType();
        this.referenceData= other.getReferenceData();
        this.validateRegex= other.getValidateRegex();
        this.validateInfo= other.getValidateInfo();
        this.lastModifyDate= other.getLastModifyDate();
        this.recorder= other.getRecorder();

        return this;
    }

    public PendingMetaColumn copyNotNullProperty(PendingMetaColumn other){
  
  
//        if( other.getCid() != null)
//            this.setCid(other.getCid());
        if( other.getFieldLabelName() != null)
            this.fieldLabelName= other.getFieldLabelName();
        if( other.getColumnComment() != null)
            this.columnComment= other.getColumnComment();
        if( other.getColumnOrder() != null)
            this.columnOrder= other.getColumnOrder();
        if( other.getColumnType() != null)
            this.columnFieldType= other.getColumnFieldType();
        if( other.getMaxLengthM() != null)
            this.maxLengthM= other.getMaxLengthM();
        if( other.getScaleM() != null)
            this.scaleM= other.getScaleM();
        if( other.getAccessType() != null)
            this.accessType= other.getAccessType();
        if( other.getMandatory() != null)
            this.mandatory= other.isMandatory()?"T":"F";
        if( other.getPrimarykey() != null)
            this.primarykey= other.getPrimarykey();
        if( other.getColumnState() != null)
            this.columnState= other.getColumnState();
        if( other.getReferenceType() != null)
            this.referenceType= other.getReferenceType();
        if( other.getReferenceData() != null)
            this.referenceData= other.getReferenceData();
        if( other.getValidateRegex() != null)
            this.validateRegex= other.getValidateRegex();
        if( other.getValidateInfo() != null)
            this.validateInfo= other.getValidateInfo();
        if( other.getLastModifyDate() != null)
            this.lastModifyDate= other.getLastModifyDate();
        if( other.getRecorder() != null)
            this.recorder= other.getRecorder();

        return this;
    }

    public PendingMetaColumn clearProperties(){
//        this.setCid(null);
        this.fieldLabelName= null;
        this.columnComment= null;
        this.columnOrder= null;
        this.columnFieldType= null;
        this.accessType= null;
        this.mandatory= null;
        this.primarykey= null;
        this.columnState= null;
        this.referenceType= null;
        this.referenceData= null;
        this.validateRegex= null;
        this.validateInfo= null;
        this.lastModifyDate= null;
        this.recorder= null;

        return this;
    }
    @Override
    public String getPropertyName() {
        return SimpleTableField.mapPropName(getColumnName());
    }
    @Override
    public String getJavaType() {
        return MetaColumn.mapToFieldType(this.columnFieldType, this.scaleM==null?0:this.scaleM);
    }
    @Override
    public boolean isMandatory() {
        return "T".equals(mandatory) ||  "Y".equals(mandatory) || "1".equals(mandatory);
    }

    public boolean isPrimaryKey() {
        return "T".equals(primarykey) ||  "Y".equals(primarykey) || "1".equals(primarykey);
    }


    @Override
    public int getMaxLength() {
        if("string".equalsIgnoreCase(this.columnFieldType) ||
                "integer".equalsIgnoreCase(this.columnFieldType)||
                "float".equalsIgnoreCase(this.columnFieldType) ||
                "varchar".equalsIgnoreCase(this.columnFieldType)||
                "number".equalsIgnoreCase(this.columnFieldType))
            return maxLengthM==null?0:maxLengthM.intValue();
        return 0;
    }
    @Override
    public int getPrecision() {
        return getMaxLength();
    }
    @Override
    public int getScale() {
        if("float".equalsIgnoreCase(this.columnFieldType) ||
                "number".equalsIgnoreCase(this.columnFieldType))
            return scaleM==null?0:scaleM.intValue();
        return 0;
    }

    @Override
    public String getDefaultValue() {
        return "C".equals(autoCreateRule)?autoCreateParam:null;
    }
    @Override
    @JSONField(serialize=false)
    public String getColumnType() {
        return FieldType.mapToDBColumnType(this.databaseType, this.columnFieldType);
    }
}
