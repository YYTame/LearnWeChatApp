package com.imooc.demo.dao;

import com.imooc.demo.DemoApplication;
import com.imooc.demo.entity.Area;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class} )
public class AreaDaoTest extends TestCase {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }

    @Test
    public void testQueryAreaById() {
        Area area = areaDao.queryAreaById(1);
        assertEquals("东苑",area.getAreaName());
    }

    @Test
    public void testInsertArea() {
        Area area = new Area();
        area.setAreaName("测试区域");
        area.setPriority(1);
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        int id = areaDao.insertArea(area);
        assertNotEquals(id,0);
    }

    @Test
    public void testUpdateArea() {
        Area area = new Area();
        area.setAreaId(3);
        area.setAreaName("南苑1");
        area.setLastEditTime(new Date());
        int i = areaDao.updateArea(area);
        assertEquals(1,i);
    }

    @Test
    public void testDeleteArea() {
        int i = areaDao.deleteArea(3);
        assertEquals(1,i);
    }
}
