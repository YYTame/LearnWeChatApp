// pages/list/list.js
// const util = require('../../utils/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;
    wx.request({
      url: "http://127.0.0.1:8080/demo/area/getAreas",
      method:'GET',
      data:{},
      success(res) {
        if (res.data.code == 0) {
          that.setData({
            list: res.data.data
          })
        } else {
          var toastText = res.data.message;
          that.showCusForm(toastText, '', 1000);
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 跳转到添加区域界面
   */
  addArea:function(){
    wx.navigateTo({
      url: '../operation/operation'
    })
  },

  deleteArea: function(e) {
    var that = this;
    var dataset = e.target.dataset;
    var areaName = dataset.areaname;
    var areaid = dataset.areaid;
    var index = dataset.index;
    var msg = "删除成功";
    wx.showModal({
      title: '提示',
      content: '您确定要删除[' + areaName + ']吗?',
      success(res) {
        if (res.confirm) {
          wx.request({
            url: "http://127.0.0.1:8080/demo/area/deleteArea/" + areaid,
            method: 'DELETE',
            data:{},
            success(res) {
              var toastText='删除成功';
              if (res.data.code == 0) {
                that.data.list.splice(index, 1);
                that.setData({
                  list: that.data.list
                });
              } else {
                toastText = '删除失败';
              }
              wx.showToast({
                title: toastText,
                icon:'',
                duration:1000
              })
            }
          })
        }
      }
    })
  }

})