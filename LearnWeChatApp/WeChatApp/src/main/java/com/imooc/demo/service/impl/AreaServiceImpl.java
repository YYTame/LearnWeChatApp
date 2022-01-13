package com.imooc.demo.service.impl;

import com.imooc.demo.dao.AreaDao;
import com.imooc.demo.entity.Area;
import com.imooc.demo.service.AreaService;
import com.imooc.demo.util.enums.ResultEnum;
import com.imooc.demo.util.exception.dao.AreaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    /**
     * 列出区域列表
     *
     * @return areaList
     */
    @Override
    public List<Area> queryArea() {
        return areaDao.queryArea();
    }

    /**
     * 根据id列出具体区域
     *
     * @param areaId
     * @return area
     */
    @Override
    public Area queryAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    /**
     * 插入区域信息
     *
     * @param area
     * @return
     */
    @Override
    public boolean insertArea(Area area) {
        int i;
        //判断地区名称是否为空
        if (area.getAreaName() == null) {
            throw new AreaException(ResultEnum.AREA_INSERT_AREANAME_EMPTY);
        }
        //判断权重是否为空。
        if (area.getPriority() == null) {
            throw new AreaException(ResultEnum.AREA_INSERT_PRIORITY_EMPTY);
        }
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        try {
            i = areaDao.insertArea(area);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (i == 1) {
            return true;
        } else {
            throw new AreaException(ResultEnum.AREA_INSERT_AREAID_INVALID);
        }
    }

    /**
     * 更新区域信息
     *
     * @param area
     * @return
     */
    @Override
    public boolean updateArea(Area area) {
        int i;
        if (area != null && area.getAreaName() != null) {
            area.setLastEditTime(new Date());
            try {
                i = areaDao.updateArea(area);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            if (i == 0) {
                throw new AreaException(ResultEnum.AREA_UPDATE_ERROR);
            } else {
                return true;
            }
        } else {
            throw new AreaException(ResultEnum.AREA_UPDATE_AREANAME_EMPTY);
        }
    }

    /**
     *删除区域信息
     *
     * @param areaId
     * @return
     */
    @Override
    public boolean deleteArea(int areaId) {
        int i;
        if (areaId > 0) {
            try {
                i = areaDao.deleteArea(areaId);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            if (i == 0) {
                throw new AreaException(ResultEnum.AREA_DELETE);
            } else {
                return true;
            }
        } else {
            throw new AreaException(ResultEnum.AREA_DELETE_AREAID_EMPTY);
        }
    }

}
