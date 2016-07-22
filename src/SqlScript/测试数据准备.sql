--再测试
insert into M_Model_Data_Field (Model_Code,column_Name,Column_Type,Access_Type,Input_Type,
Reference_Type,Reference_Data,Input_Hint)
select 'NEW_OPPORTUNITIE', column_Name , 'T', 'N','input',
Reference_Type,Reference_Data,column_comment
from F_META_COLUMN where table_id=105;


insert into M_Model_Data_Field (Model_Code,column_Name,Column_Type,Access_Type,Input_Type,
Reference_Type,Reference_Data,Input_Hint,FIELD_HEIGHT,Field_Width)
select 'OPPORT_CONTRACTS', column_Name , 'T', 'N','input',
Reference_Type,Reference_Data,column_comment,1,6
from F_META_COLUMN where table_id=62;


insert into M_Model_Data_Field (Model_Code,column_Name,Column_Type,Access_Type,Input_Type,
Reference_Type,Reference_Data,Input_Hint,FIELD_HEIGHT,Field_Width)
select 'OPPORT_EXPENSES', column_Name , 'T', 'N','input',
Reference_Type,Reference_Data,column_comment,1,6
from F_META_COLUMN where table_id=69;


select * from F_META_TABLE;

update M_Model_Data_Field
set input_TYPE='select',
  reference_Type='3',
  reference_Data='[{value:1,name:"行业固定前期事务"},{value:2,name:"一般前期事务"}]'
where model_code= 'NEW_OPPORTUNITIE' and column_name='L_OPP_TYPE'
/

update M_Model_Data_Field
set input_TYPE='select',
  reference_Type='3',
  reference_Data='[{value:1,name:"进行中事务"},{value:2,name:"关闭的事务"}]'
where model_code= 'NEW_OPPORTUNITIE' and column_name='L_OPP_STATE'
/

update M_Model_Data_Field
set Access_Type='R'
where model_code= 'NEW_OPPORTUNITIE' and column_name='L_OPP_ID'
/


--create sequence fedmo2.seq_OPPORT_ID start with 100;
update F_META_COLUMN
set auto_create_rule='S' , auto_create_param = 'SEQ_OPPORT_ID'
where table_id= 105 and column_Name='L_OPP_ID'
/

update F_META_COLUMN
set auto_create_rule='C' , auto_create_param = '1'
where table_id= 105 and column_Name='L_OPP_STATE'
/

update M_Model_Data_Field
set input_TYPE='select',
  reference_Type='9',
  reference_Data='unitCode'
where model_code= 'NEW_OPPORTUNITIE' and column_name='L_OWNER_DEP_ID'
/

update F_META_COLUMN
set reference_Type='9',
  reference_Data='unitCode'
where table_id= 105 and column_Name='L_OWNER_DEP_ID'
/

update F_META_COLUMN
set reference_Type='3',
  reference_Data='[{value:1,name:"行业固定前期事务"},{value:2,name:"一般前期事务"}]'
where table_id= 105 and column_Name='L_OPP_TYPE'
/

update F_META_COLUMN
set reference_Type='3',
  reference_Data='[{value:1,name:"进行中事务"},{value:2,name:"关闭的事务"}]'
where table_id= 105 and  column_name='L_OPP_STATE'
/


update F_PENDING_META_COLUMN
set reference_Type='9',
  reference_Data='unitCode'
where table_id= 105 and column_Name='L_OWNER_DEP_ID'
/

update F_PENDING_META_COLUMN
set reference_Type='3',
  reference_Data='[{value:1,name:"行业固定前期事务"},{value:2,name:"一般前期事务"}]'
where table_id= 105 and column_Name='L_OPP_TYPE'
/

update F_PENDING_META_COLUMN
set reference_Type='3',
  reference_Data='[{value:1,name:"进行中事务"},{value:2,name:"关闭的事务"}]'
where table_id= 105 and  column_name='L_OPP_STATE'
/


