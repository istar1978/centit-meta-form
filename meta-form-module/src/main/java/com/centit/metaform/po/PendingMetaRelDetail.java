package com.centit.metaform.po;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * create by scaffold 2016-06-01
 * <p>
 * <p>
 * 未落实表关联细节表null
 */
@Entity
@Table(name = "F_PENDING_META_REL_DETIAL")
public class PendingMetaRelDetail implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

//    @EmbeddedId
//    private PendingMetaRelDetailId cid;

    @Column(name = "RELATION_ID")
    private Long relationId;

    @Column(name = "PARENT_COLUMN_NAME")
    @NotBlank(message = "字段不能为空")
    private String parentColumnName;

    /**
     * C字段代码 null
     */
    @Column(name = "CHILD_COLUMN_NAME")
    @NotBlank(message = "字段不能为空")
    @Length(max = 32, message = "字段长度不能大于{max}")
    private String childColumnName;

    // Constructors

    /**
     * default constructor
     */
    public PendingMetaRelDetail() {
    }

    /**
     * minimal constructor
     */
//    public PendingMetaRelDetail(PendingMetaRelDetailId id
//
//        ,String  childColumnName) {
//        this.cid = id;
//
//
//        this.childColumnName= childColumnName;
//    }


//    public PendingMetaRelDetailId getCid() {
//        if(null==this.cid)
//            this.cid=new PendingMetaRelDetailId();
//        return this.cid;
//    }

//    public void setCid(PendingMetaRelDetailId id) {
//        if(null==id.getRelationId())
//            this.cid=null;
//        else
//            this.cid = id;
//    }
    public Long getRelationId() {
//        if(this.cid==null)
//            return null;
//        return this.cid.getRelationId();
        return this.relationId;
    }

    public void setRelationId(Long relationId) {
//        if(this.cid==null)
//            this.cid = new PendingMetaRelDetailId();
//        this.cid.setRelationId(relationId);
        this.relationId = relationId;
    }

    public String getParentColumnName() {
//        if(this.cid==null)
//            return null;
//        return this.cid.getParentColumnName();
        return this.parentColumnName;
    }

    public void setParentColumnName(String parentColumnName) {
//        if(this.cid==null)
//            this.cid = new PendingMetaRelDetailId();
//        this.cid.setParentColumnName(parentColumnName);
        this.parentColumnName = parentColumnName;
    }


    // Property accessors

    public String getChildColumnName() {
        return this.childColumnName;
    }

    public void setChildColumnName(String childColumnName) {
        this.childColumnName = childColumnName;
    }


    public PendingMetaRelDetail copy(PendingMetaRelDetail other) {

        this.setRelationId(other.getRelationId());
        this.setParentColumnName(other.getParentColumnName());

        this.childColumnName = other.getChildColumnName();

        return this;
    }

    public PendingMetaRelDetail copyNotNullProperty(PendingMetaRelDetail other) {

        if (other.getRelationId() != null)
            this.setRelationId(other.getRelationId());
        if (other.getParentColumnName() != null)
            this.setParentColumnName(other.getParentColumnName());

        if (other.getChildColumnName() != null)
            this.childColumnName = other.getChildColumnName();

        return this;
    }

    public PendingMetaRelDetail clearProperties() {

        this.childColumnName = null;

        return this;
    }
}
