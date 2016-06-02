define(function(require) {
	var Config = require('config');
	var Core = require('core/core');
	var Page = require('core/page');
	
	var MdRelDetialView = Page.extend(function() {
		
		var _self = this;
		
		// @override
		this.load = function(panel, data) {
			var form = panel.find('form');
			
			Core.ajax(Config.ContextPath+'service/metaform/mdreldetial/'+data.cid, {
				type: 'json',
				method: 'get' 
			}).then(function(data) {
				_self.data = data;
				
				form.form('load', data)
					.form('disableValidation')
					.form('focus');
			});
		};
	});
	
	return MdRelDetialView;
});