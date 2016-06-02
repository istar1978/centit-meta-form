package com.centit.metaform.po;

import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.Column;


import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * create by scaffold 2016-06-01 
 * @author codefan@sina.com
 
  未落实表关联关系表null   
*/
@Entity
@Table(name = "F_PENDING_MD_RELATION")
public class PendingMdRelation implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



	/**
	 * 关联代码 关联关系，类似与外键，但不创建外键 
	 */
	@Id
	@Column(name = "relation_ID")
	@GeneratedValue(generator = "assignedGenerator")
	@GenericGenerator(name = "assignedGenerator", strategy = "assigned")
	private Long relationId;

	/**
	 * 主表表ID 表单主键 
	 */
	@Column(name = "Parent_Table_ID")
	private Long  parentTableId;
	/**
	 * 从表表ID 表单主键 
	 */
	@Column(name = "Child_Table_ID")
	private Long  childTableId;
	/**
	 * 关联名称 null 
	 */
	@Column(name = "relation_name")
	@NotBlank(message = "字段不能为空")
	@Length(min = 0, max = 64, message = "字段长度不能小于{min}大于{max}")
	private String  relationName;
	/**
	 * 状态 null 
	 */
	@Column(name = "relation_state")
	@NotBlank(message = "字段不能为空")
	@Length(min = 0,  message = "字段长度不能小于{min}大于{max}")
	private String  relationState;
	/**
	 * 关联说明 null 
	 */
	@Column(name = "relation_comment")
	@Length(min = 0, max = 256, message = "字段长度不能小于{min}大于{max}")
	private String  relationComment;
	/**
	 * 更改时间 null 
	 */
	@Column(name = "last_modify_Date")
	private Date  lastModifyDate;
	/**
	 * 更改人员 null 
	 */
	@Column(name = "Recorder")
	@Length(min = 0, max = 8, message = "字段长度不能小于{min}大于{max}")
	private String  recorder;

	// Constructors
	/** default constructor */
	public PendingMdRelation() {
	}
	/** minimal constructor */
	public PendingMdRelation(
		Long relationId		
		,String  relationName,String  relationState) {
	
	
		this.relationId = relationId;		
	
		this.relationName= relationName; 
		this.relationState= relationState; 		
	}

/** full constructor */
	public PendingMdRelation(
	 Long relationId		
	,Long  parentTableId,Long  childTableId,String  relationName,String  relationState,String  relationComment,Date  lastModifyDate,String  recorder) {
	
	
		this.relationId = relationId;		
	
		this.parentTableId= parentTableId;
		this.childTableId= childTableId;
		this.relationName= relationName;
		this.relationState= relationState;
		this.relationComment= relationComment;
		this.lastModifyDate= lastModifyDate;
		this.recorder= recorder;		
	}
	

  
	public Long getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
	// Property accessors
  
	public Long getParentTableId() {
		return this.parentTableId;
	}
	
	public void setParentTableId(Long parentTableId) {
		this.parentTableId = parentTableId;
	}
  
	public Long getChildTableId() {
		return this.childTableId;
	}
	
	public void setChildTableId(Long childTableId) {
		this.childTableId = childTableId;
	}
  
	public String getRelationName() {
		return this.relationName;
	}
	
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
  
	public String getRelationState() {
		return this.relationState;
	}
	
	public void setRelationState(String relationState) {
		this.relationState = relationState;
	}
  
	public String getRelationComment() {
		return this.relationComment;
	}
	
	public void setRelationComment(String relationComment) {
		this.relationComment = relationComment;
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



	public PendingMdRelation copy(PendingMdRelation other){
  
		this.setRelationId(other.getRelationId());
  
		this.parentTableId= other.getParentTableId();  
		this.childTableId= other.getChildTableId();  
		this.relationName= other.getRelationName();  
		this.relationState= other.getRelationState();  
		this.relationComment= other.getRelationComment();  
		this.lastModifyDate= other.getLastModifyDate();  
		this.recorder= other.getRecorder();

		return this;
	}
	
	public PendingMdRelation copyNotNullProperty(PendingMdRelation other){
  
	if( other.getRelationId() != null)
		this.setRelationId(other.getRelationId());
  
		if( other.getParentTableId() != null)
			this.parentTableId= other.getParentTableId();  
		if( other.getChildTableId() != null)
			this.childTableId= other.getChildTableId();  
		if( other.getRelationName() != null)
			this.relationName= other.getRelationName();  
		if( other.getRelationState() != null)
			this.relationState= other.getRelationState();  
		if( other.getRelationComment() != null)
			this.relationComment= other.getRelationComment();  
		if( other.getLastModifyDate() != null)
			this.lastModifyDate= other.getLastModifyDate();  
		if( other.getRecorder() != null)
			this.recorder= other.getRecorder();		

		return this;
	}

	public PendingMdRelation clearProperties(){
  
		this.parentTableId= null;  
		this.childTableId= null;  
		this.relationName= null;  
		this.relationState= null;  
		this.relationComment= null;  
		this.lastModifyDate= null;  
		this.recorder= null;

		return this;
	}
}