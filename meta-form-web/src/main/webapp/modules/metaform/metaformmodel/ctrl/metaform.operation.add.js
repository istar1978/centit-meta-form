define(function(require) {
	var Page = require('core/page');
	
	// 新增角色信息
	var OperationAdd = Page.extend(function() {
		
		this.submit = function(table, data) {
			if (!table.cdatagrid('endEdit')) {
				return;
			}
			table.datagrid('appendRow', $.extend({tableId:this.parent.data.tableId}, this.object));
			var index = table.datagrid('getRows').length - 1;
			table.datagrid('selectRow', index);
			table.cdatagrid('beginEdit', index, 'dataCode');
			var rows=table.datagrid('getRows');
			console.log(rows);
		};
		
       
	});
	return OperationAdd;
});