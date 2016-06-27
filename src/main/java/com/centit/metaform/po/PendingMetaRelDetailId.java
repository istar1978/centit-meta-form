package com.centit.metaform.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * PendingMdRelDetialId  entity.
 * create by scaffold 2016-06-01 
 
 * 未落实表关联细节表null   
*/
//未落实表关联细节表 的主键
@Embeddable
public class PendingMetaRelDetailId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	/**
	 * 关联代码 null 
	 */
	@JoinColumn(name = "RELATION_ID")
	@ManyToOne
	@JSONField(serialize=false)
	private PendingMetaRelation relation;

	/**
	 * p字段代码 null 
	 */
	@Column(name = "PARENT_COLUMN_NAME")
	@NotBlank(message = "字段不能为空")
	private String parentColumnName;

	// Constructors
	/** default constructor */
	public PendingMetaRelDetailId() {
	}
	/** full constructor */
	public PendingMetaRelDetailId(Long relationId, String parentColumnName) {

		this.setRelationId(relationId);
		this.parentColumnName = parentColumnName;	
	}

	
	
  
	public PendingMetaRelation getRelation() {
		return relation;
	}
	public void setRelation(PendingMetaRelation relation) {
		this.relation = relation;
	}
	public Long getRelationId() {
		if(null==this.relation)
			return null;
		return this.relation.getRelationId();
	}

	public void setRelationId(Long relationId) {
			this.relation=new PendingMetaRelation();
			this.relation.setRelationId(relationId);
	}
  
	public String getParentColumnName() {
		return this.parentColumnName;
	}

	public void setParentColumnName(String parentColumnName) {
		this.parentColumnName = parentColumnName;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PendingMetaRelDetailId))
			return false;
		
		PendingMetaRelDetailId castOther = (PendingMetaRelDetailId) other;
		boolean ret = true;
  
		ret = ret && ( this.getRelationId() == castOther.getRelationId() ||
					   (this.getRelationId() != null && castOther.getRelationId() != null
							   && this.getRelationId().equals(castOther.getRelationId())));
  
		ret = ret && ( this.getParentColumnName() == castOther.getParentColumnName() ||
					   (this.getParentColumnName() != null && castOther.getParentColumnName() != null
							   && this.getParentColumnName().equals(castOther.getParentColumnName())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getRelationId() == null ? 0 :this.getRelationId().hashCode());
  
		result = 37 * result +
		 	(this.getParentColumnName() == null ? 0 :this.getParentColumnName().hashCode());
	
		return result;
	}
}