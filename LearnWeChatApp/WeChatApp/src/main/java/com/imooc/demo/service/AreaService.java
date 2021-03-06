package com.imooc.demo.service;

import com.imooc.demo.entity.Area;

import java.util.List;

public interface AreaService {

    /**
     * 列出区域列表
     *
     * @return areaList
     */
    List<Area> queryArea();

    /**
     * 根据id列出具体区域
     *
     * @param areaId
     * @return area
     */
    Area queryAreaById(int areaId);

    /**
     * 插入区域信息
     *
     * @param area
     * @return
     */
    boolean insertArea(Area area);

    /**
     * 更新区域信息
     *
     * @param area
     * @return
     */
    boolean updateArea(Area area);

    /**
     *删除区域信息
     *
     * @param areaId
     * @return
     */
    boolean deleteArea(int areaId);

}
