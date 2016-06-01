define(function(require) {
	var Config = require('config');
	var Core = require('core/core');
	var Page = require('core/page');
	
	// 删除数据字典
	var PendingMdRelDetialRemove = Page.extend(function() {
		
		// @override
		this.submit = function(table, data) {
			Core.ajax(Config.ContextPath+'service/metaform/pendingmdreldetial/'+data.cid, {
            	type: 'json',
                method: 'post',
                data: {
                    _method: 'delete'
                }
			}).then(function() {
				table.datagrid('reload');
            });
		}
	});
	
	return PendingMdRelDetialRemove;
});