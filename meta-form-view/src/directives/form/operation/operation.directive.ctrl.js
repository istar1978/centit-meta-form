(function() {
  'use strict';

  angular.module('metaForm')
    .controller('FormOperationController', FormOperationController);

  /* @ngInject */
  function FormOperationController($scope, $q, $state, $uibModal,$http, $confirm, toastr) {

    $scope.operate = operate;

    /////////////////////////////

    /**
     * 增强具体操作，
     * 操作前弹出确认框，
     * 操作后提示信息
     */
    function operate() {
      _beforeOperate()
        .then(_operating)
        .then(_afterOperate);
    }

    /**
     * 根据按钮操作配置，在点击按钮后弹出确认窗口
     * @returns {*}
     * @private
     */
    function _beforeOperate() {
      var optHintInfo = $scope.operation.optHintInfo,
        item = $scope.item,
        label = $scope.operation.label;

      if (optHintInfo) {
        // 将“是否确认删除 {{name}} 吗？”转换成“是否确认删除 丁丁 吗？”
        optHintInfo = Mustache.render(optHintInfo, item);

        return $confirm({
          text: optHintInfo,
          title: label || '请确认'
        });
      }

      return $q.resolve();
    }


    /**
     * 根据按钮配置，添加事后提示
     * @param data
     * @returns {*}
     * @private
     */
    function _afterOperate(data) {
      // 事后提示信息
      var optMessage = $scope.operation.optMessage,
        data = data || {};

      // 转换事后提示信息
      if (optMessage) {
        optMessage = Mustache.render(optMessage, data);

        toastr.success(optMessage);
      }

      return $q.resolve();
    }

    /**
     * 处理按钮操作：
     *
     * D 弹出窗口打开
     * P 新页面打开
     * C 确认框
     * @returns {*}
     * @private
     */
    function _operating() {
      var openType = $scope.operation.openType;

      if ('D' == openType) {
        return _openInDialog();
      }

      else if ('P' == openType) {
        return  _openInPage();
      }

      else if ('C' == openType) {
        if($scope.operation.method=='delete'){
          _delete();
        }
        return _openInConfirm();
      }
    }

    function _openInConfirm() {
      return $q.resolve($scope.item);
    }

    function _openInDialog() {
      var item = $scope.item,
        operation = $scope.operation,
        formModel = $scope.formModel;

      var primaryKey = formModel.primaryKey || [],
        primaryValue = primaryKey.map(function(key) {
          return item[key]
        });
      $uibModal.open({
        templateUrl : 'pages/access/access.infoDialog.html',
        controller : 'test',
        resolve : {
          primaryKey :function(){return primaryKey[0]},
          primaryValue:function () {return primaryValue[0]},
          modelCode:function () {return operation.optModelCode},
          operation:function () {return operation.method}
        }
      });


    }

    /**
     * 在新页面打开
     * @private
     */
    function _openInPage() {
      var item = $scope.item,
        operation = $scope.operation,
        formModel = $scope.formModel;

      var primaryKey = formModel.primaryKey || [],
        primaryValue = primaryKey.map(function(key) {
          return item[key]
        });

      $state.go('form.access.' + operation.method, {
        primaryKey: primaryKey,
        primaryValue: primaryValue,
        modelCode: operation.modelCode
      });
    }

    /**
     * 删除某一行数据
     * @private
     */
    function _delete(){
      var item = $scope.item,
          operation = $scope.operation,
          formModel = $scope.formModel,
          items = $scope.items;


      var primaryKey = formModel.primaryKey || [],
          primaryValue = primaryKey.map(function(key) {
            return item[key]
          });

      var url = '/api/service/metaform/formaccess/'+operation.modelCode+'/delete';

      $http.post(url,{},{params:{primaryKey: primaryKey, primaryValue: primaryValue}}).
      then(function success(data){

               //响应成功时调用
            var index=items.indexOf(item);//获取被删除行在列表items中的index值
            if(index > -1) $scope.items.splice(index,1);//删除数组中的数据（index）
                console.log(data);
          },function error(mesage){

              // 响应失败时调用
              console.log(mesage)

          }
        );

    }

  }

})();
