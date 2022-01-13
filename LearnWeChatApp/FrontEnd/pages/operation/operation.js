// pages/operation.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    areaName: "",
    priority: "",
    areaId: undefined,
    createAreaUrl: 'http://localhost:8080/demo/area/createArea',
    changeAreaUrl: "http://localhost:8080/demo/area/updateArea",
    getAreabyIdUrl: 'http://localhost:8080/demo/area/getAreabyAreaId/',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    if (options.areaId == undefined) {
      return;
    }
    wx.request({
      url: this.data.getAreabyIdUrl + options.areaId,
      success: function(res) {
        var result = res.data;
        if (result.code == 0) {
          var areaName = result.data.areaName;
          var priority = result.data.priority;
          that.setData({
            areaId: options.areaId,
            areaName: areaName,
            priority: priority
          })
        } else {
          wx.showToast({
            title: '请求失败',
            icon: 'error',
            duration:1000
          })        }
      }
    })
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

  formSubmit: function(e) {
    var formData= e.detail.value;
    var method ="POST";
    var url = this.data.createAreaUrl;
    var that = this;
    if (this.data.areaId != undefined) {
      url = this.data.changeAreaUrl;
      method="PUT";
      formData.areaId=this.data.areaId;
    }
    wx.request({
      url: url,
      method:method,
      contentType:"application/json",
      data: JSON.stringify(formData),
      success: function(res) {
        var result = res.data;
        var toastText = "操作成功";
        var sta = "success";
        if (result.code != 0) {
          toastText = "操作失败";
          sta = "error";
        } 
        if(result.code == 0) {
          wx.navigateBack({
            delta: 1  // 返回上一级页面。
          })
        }
        wx.showToast({
          title: toastText,
          icon:sta,
          duration:1000
        })
      }
    })
  }

})