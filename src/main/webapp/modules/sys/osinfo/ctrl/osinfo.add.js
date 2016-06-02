define(function(require) {
	var Core = require('core/core');
	var Page = require('core/page');
	var Config = require('config');
	
	// 新增角色信息
	var OsInfoAdd = Page.extend(function() {
		
		
		// @override
		this.load = function(panel) {
			form = panel.find('form');
			this.object.hasInterface='T';
			form.form('load', this.object)
				.form('addValidation', 'osId', {
					required: true,
				    validType: {
				    	remote: Config.ContextPath+'service/sys/osinfo/noexists/'
				    }
				})
				.form('disableValidation')
				.form('focus');
		};
		
		// @override
		this.submit = function(panel, data, ClosecallBack) {
			var form = panel.find('form');
			
			var isValid = form.form('enableValidation').form('validate');
			
			if (isValid) {
				form.form('ajax', {
					url: Config.ContextPath + 'service/sys/osinfo',
					method: 'post'
					
				}).then(ClosecallBack);
			}
			
			return false;
		};
		
		// @override
		this.onClose = function(table, data) {
			table.datagrid('reload');
		};
	});
	
	return OsInfoAdd;
});