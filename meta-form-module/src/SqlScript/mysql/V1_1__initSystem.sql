drop table if exists D_DataBase_Info;

drop table if exists D_OS_INFO;

drop table if exists F_META_CHANG_LOG;

drop table if exists F_META_COLUMN;

drop table if exists F_META_RELATION;

drop table if exists F_META_REL_DETIAL;

drop table if exists F_META_TABLE;

drop table if exists F_PENDING_META_COLUMN;

drop table if exists F_PENDING_META_RELATION;

drop table if exists F_PENDING_META_REL_DETIAL;

drop table if exists F_PENDING_META_TABLE;

drop table if exists M_Meta_Form_Model;

drop table if exists M_Model_Data_Field;

drop table if exists M_Model_OPERATION;

create table D_DataBase_Info
(
   Database_Code        varchar(32) not null,
   database_name        varchar(100),
   OS_ID                varchar(20),
   database_url         varchar(1000),
   username             varchar(100),
   password             varchar(100) comment '����',
   database_desc        varchar(500),
   last_Modify_DATE     datetime,
   create_time          datetime,
   created              varchar(8),
   primary key (Database_Code)
);

create table D_OS_INFO
(
   OS_ID                varchar(20) not null,
   OS_NAME              varchar(200) not null,
   OS_URL               char(10),
   DDE_SYNC_URL         char(10) comment '�������DDEʹ��',
   SYS_DATA_PUSH_OPTION char(10),
   last_Modify_DATE     datetime,
   create_time          datetime,
   created              varchar(8),
   primary key (OS_ID)
);

create table F_META_CHANG_LOG
(
   change_ID            numeric(12,0) not null,
   Table_ID             numeric(12,0) comment '������',
   change_Date          datetime not null default NOW(),
   changer              varchar(6) not null,
   change_Script        text,
   change_comment       varchar(2048),
   primary key (change_ID)
);

create table F_META_COLUMN
(
   Table_ID             numeric(12,0) not null comment '������',
   column_Name          varchar(32) not null,
   field_Label_Name     varchar(64) not null,
   column_Comment       varchar(256),
   column_Order         numeric(3,0) default 99,
   column_Type          varchar(32) not null,
   max_Length           numeric(6,0) comment 'precision',
   scale                numeric(3,0),
   access_type          char(1) not null,
   mandatory            char(1),
   primarykey           char(1),
   column_state         char(1) not null,
   reference_Type       char(1) comment ' 0��û�У�1�� �����ֵ�(�б�)   2�� �����ֵ�(����)   3��JSON���ʽ 4��sql���   5��SQL������
            	   9 :��������ֵ䣨�û�����������ɫ�ȵȣ�  Y����� M���·�   F:�ļ���column_Type ����Ϊ varchar��64����',
   reference_Data       varchar(1000) comment '����paramReferenceType���ͣ�1,2,3����д��Ӧֵ',
   Validate_Regex       varchar(200) comment 'regex���ʽ',
   Validate_Info        varchar(200) comment 'Լ����ͨ����ʾ��Ϣ',
   auto_create_Rule     char(1) comment 'C ����  U uuid S sequence',
   auto_create_Param    varchar(1000) comment '������Ĭ��ֵ������ sequence����',
   last_modify_Date     datetime,
   Recorder             varchar(8),
   primary key (Table_ID, column_Name)
);

create table F_META_RELATION
(
   relation_ID          numeric(12,0) not null comment '������ϵ������������������������',
   Parent_Table_ID      numeric(12,0) comment '������',
   Child_Table_ID       numeric(12,0) comment '������',
   relation_name        varchar(64) not null,
   relation_state       char(1) not null,
   relation_comment     varchar(256),
   last_modify_Date     datetime,
   Recorder             varchar(8),
   primary key (relation_ID)
);

create table F_META_REL_DETIAL
(
   relation_ID          varchar(32) not null,
   parent_column_Name   varchar(32) not null,
   child_column_Name    varchar(32) not null,
   primary key (relation_ID, parent_column_Name)
);

create table F_META_TABLE
(
   Table_ID             numeric(12,0) not null comment '����',
   Database_Code        varchar(32),
   table_type           char(1) not null comment '��/��ͼ/�Ѵ��ڱ����չ�ֶ�    Ŀǰֻ���Ǳ�',
   Table_Name           varchar(64) not null,
   Table_Label_Name     varchar(100) not null,
   EXT_COLUMN_NAME      varchar(64) comment '��չ�ֶ�����(�ֶ����� ������ CLOB ���� TEXT )',
   EXT_COLUMN_FROMAT    varchar(10) comment 'XML\JSON',
   Recorder             varchar(8),
   table_state          char(1) not null comment 'ϵͳ S / R ��ѯ(ֻ��)/ N �½�(��д)',
   table_Comment        varchar(256),
   Workflow_OPT_TYPE    char(1) not null default '0' comment '0: ������������ 1��������ҵ����� 2�� �����̹��̹���
            1, ���  WFINSTID ����ʵ��ID
            2, ��� NODEINSTID WFINSTID	�ڵ�ʵ����� ����ʵ��ID
            
            
            Name	Code	Comment	Data Type	Length	Precision	Primary	Foreign Key	Mandatory
            �ڵ�ʵ�����	NODEINSTID		NUMBER(12)	12		TRUE	FALSE	TRUE
            ����ʵ��ID	WFINSTID		NUMBER(12)	12		FALSE	TRUE	FALSE',
   update_check_timestamp char(1) comment 'Y/N ����ʱ�Ƿ�У��ʱ��� ��� Last_modify_time datetime',
   last_modify_Date     datetime,
   primary key (Table_ID)
);

alter table F_META_TABLE comment '״̬��Ϊ ϵͳ/��ѯ/����
ϵͳ�����������κβ���
��ѯ��������ͨ�ò�ѯģ�飬�����Ը���
                                 -&';

create table F_PENDING_META_COLUMN
(
   Table_ID             numeric(12,0) not null comment '������',
   column_Name          varchar(32) not null,
   field_Label_Name     varchar(64) not null,
   column_Comment       varchar(256),
   column_Order         numeric(3,0) default 99,
   column_Type          varchar(32) not null,
   max_Length           numeric(6,0) comment 'precision',
   scale                numeric(3,0),
   access_type          char(1) not null,
   mandatory            char(1),
   primarykey           char(1),
   column_state         char(1) not null,
   reference_Type       char(1) comment ' 0��û�У�1�� �����ֵ�(�б�)   2�� �����ֵ�(����)   3��JSON���ʽ 4��sql���   5��SQL������
            	   9 :��������ֵ䣨�û�����������ɫ�ȵȣ�  Y����� M���·�   F:�ļ���column_Type ����Ϊ varchar��64����',
   reference_Data       varchar(1000) comment '����paramReferenceType���ͣ�1,2,3����д��Ӧֵ',
   Validate_Regex       varchar(200) comment 'regex���ʽ',
   Validate_Info        varchar(200) comment 'Լ����ͨ����ʾ��Ϣ',
   auto_create_Rule     char(1),
   auto_create_Param    varchar(1000),
   last_modify_Date     datetime,
   Recorder             varchar(8),
   primary key (Table_ID)
);

create table F_PENDING_META_RELATION
(
   relation_ID          numeric(12,0) not null comment '������ϵ������������������������',
   Parent_Table_ID      numeric(12,0) comment '������',
   Child_Table_ID       numeric(12,0) comment '������',
   relation_name        varchar(64) not null,
   relation_state       char(1) not null,
   relation_comment     varchar(256),
   last_modify_Date     datetime,
   Recorder             varchar(8),
   primary key (relation_ID)
);

create table F_PENDING_META_REL_DETIAL
(
   relation_ID          varchar(32) not null,
   parent_column_Name   varchar(32) not null,
   child_column_Name    varchar(32) not null,
   primary key (relation_ID, parent_column_Name)
);

create table F_PENDING_META_TABLE
(
   Table_ID             numeric(12,0) not null comment '������',
   Database_Code        varchar(32),
   table_type           char(1) not null comment '��/��ͼ Ŀǰֻ���Ǳ�',
   Table_Name           varchar(64) not null,
   Table_Label_Name     varchar(100) not null,
   EXT_COLUMN_NAME      varchar(64) comment '��չ�ֶ�����(�ֶ����� ������ CLOB ���� TEXT )',
   EXT_COLUMN_FROMAT    varchar(10) comment 'XML\JSON',
   table_state          char(1) not null comment 'ϵͳ S / R ��ѯ(ֻ��)/ N �½�(��д)',
   table_Comment        varchar(256),
   Workflow_OPT_TYPE    char(1) not null default '0' comment '0: ������������ 1��������ҵ����� 2�� �����̹��̹���',
   update_check_timestamp char(1) comment 'Y/N ����ʱ�Ƿ�У��ʱ���',
   last_modify_Date     datetime,
   Recorder             varchar(8),
   primary key (Table_ID)
);

create table M_Meta_Form_Model
(
   Model_Code           varchar(16) not null,
   Table_ID             numeric(12,0) comment '������',
   Model_Comment        varchar(256),
   Model_Name           varchar(64) not null,
   Access_Type          char(1) comment 'R ֻ������ͼ����ѯ����A  ������ֻ������һ������W �޸� ��L �༭�б���ɾ�ģ�',
   form_template        varchar(128),
   list_as_tree         char(1),
   Relation_type        char(1) comment '0 û�и�ģ��  1  һ��һ��2 ���һ',
   Parent_Model_Code    varchar(16) comment '��ģ������Ӧ��ģ���Ӧ���ӱ�',
   Display_Order        numeric(4,0),
   last_modify_Date     datetime,
   Recorder             varchar(8),
   extend_Options       varchar(800),
   extend_opt_bean      varchar(64) comment 'ʵ���ض��ӿڵ�bean�����������ҵ�񱣴桢�ύ���޸ġ�ɾ����ʱ����ö�Ӧ��ҵ������',
   extend_opt_bean_param varchar(800) comment 'json String ��ʽ�Ĳ���',
   Data_filter_Sql      varchar(800) comment '�������',
   REL_WFCODE           varchar(32),
   primary key (Model_Code)
);

create table M_Model_Data_Field
(
   Model_Code           varchar(16) not null,
   column_Name          varchar(32) not null,
   column_type          char(1) not null comment '���ֶΣ�����ֻ���ֶΣ�reference_Data ��Ϊ����SQL��䣩',
   Access_Type          char(1) default 'W' comment 'H ����  R ֻ�� C �½��ǿ��Ա༭ F �ǿ�ʱ���Ա༭ N �����༭',
   Display_Order        numeric(4,0),
   input_TYPE           varchar(32),
   input_hint           varchar(256) comment '��ϵͳ�����������Զ����¼�û����������ѡ��ϵͳ�û���ѡ��ϵͳ�������ȵ�',
   reference_Type       char(1) comment ' 0��û�У�1�� �����ֵ�(�б�)   2�� �����ֵ�(����)   3��JSON���ʽ 4��sql���   5��SQL������
            	   9 :��������ֵ䣨�û�����������ɫ�ȵȣ�  Y����� M���·�   F:�ļ���column_Type ����Ϊ varchar��64����',
   reference_Data       varchar(500) comment '����paramReferenceType���ͣ�1,2,3����д��Ӧֵ',
   Validate_Regex       varchar(200) comment 'regex���ʽ',
   Validate_Info        varchar(200) comment 'Լ����ͨ����ʾ��Ϣ',
   default_Value        varchar(200) comment '����Ĭ��ֵ',
   Validate_hint        varchar(256),
   filter_type          char(2) comment 'HI ��ѯʱ����ֶ����� NO û�У� MC ��match)  LT С�� GT ���� EQ ���� BT ����  LE С�ڵ��� GE ���ڵ��� ',
   mandatory            char(1),
   focus                char(1),
   url                  varchar(256),
   extend_Options       varchar(1000),
   field_height         numeric(4,0) default 1 comment '���񲼾�Ĭ��Ϊ 1',
   field_width          numeric(4,0) default 1 comment '���񲼾�Ĭ��Ϊ 1',
   view_format          varchar(50) comment '��ʾʱ��ʽ�������number��datetime���ͣ�Ŀǰ��ʵ����������',
   primary key (Model_Code, column_Name)
);

create table M_Model_OPERATION
(
   Model_Code           varchar(16) not null comment '������������',
   OPERATION            varchar(32) not null,
   OPT_Model_Code       varchar(16) comment 'һ��ģ���еĲ����������������ģ���',
   method               varchar(16),
   label              varchar(32),
   DATA_RELATION_TYPE   varchar(1) comment 'L: list �б�   N ������������   S����ѡ��  M��ѡ',
   Display_Order        numeric(4,0),
   open_type            varchar(1) comment '0��û�У�1�� ��ʾ��Ϣ  2��ֻ����  3����д��  ',
   return_operation     varchar(1) comment '0�������� 1�� ˢ��ҳ��  2��ɾ����ǰ�� 3�����µ�ǰ��',
   opt_hint_title       varchar(100),
   opt_hint_info        varchar(500) comment '����ǰ��ʾ��Ϣ',
   extend_Options       varchar(1000),
   OPT_MESSAGE          varchar(500),
   primary key (Model_Code, OPERATION)
);
