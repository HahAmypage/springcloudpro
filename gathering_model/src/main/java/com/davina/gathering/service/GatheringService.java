package com.davina.gathering.service;

import com.davina.gathering.dao.GatheringMapper;
import com.davina.gathering.pojo.GatheringExample;
import com.davina.gathering.pojo.GatheringWithBLOBs;
import com.davina.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName GatheringService
 * @Description 活动service
 * @Author Davina Chan
 * @Date 2020/3/15 16:54
 * @Version 1.0
 */
@Service
public class GatheringService {

    @Autowired
    private GatheringMapper gatheringMapper;

    @Autowired
    private IdWorker idWorker;

    /**
    *  条件查询
    * @author chenyingxin
    * @date 2020/3/15 17:02
    * @param gatheringWithBLOBs: 查询条件
    * @return: java.util.List<com.davina.gathering.pojo.Gathering>
    **/
    public List<GatheringWithBLOBs> findAll(GatheringWithBLOBs gatheringWithBLOBs){
        GatheringExample gatheringExample = new GatheringExample();
        GatheringExample.Criteria criteria = gatheringExample.createCriteria();

        if (gatheringWithBLOBs.getName() != null && !"".equals(gatheringWithBLOBs.getName().trim())){
            criteria.andNameLike("%"+gatheringWithBLOBs.getName().trim()+"%");
        }

        if (gatheringWithBLOBs.getSummary() != null && !"".equals(gatheringWithBLOBs.getSummary().trim())){
            criteria.andSummaryLike("%"+gatheringWithBLOBs.getSummary().trim()+"%");
        }

        if (gatheringWithBLOBs.getDetail() != null && !"".equals(gatheringWithBLOBs.getDetail().trim())){
            criteria.andSummaryLike("%"+gatheringWithBLOBs.getDetail().trim()+"%");
        }

        if (gatheringWithBLOBs.getSponsor() != null && !"".equals(gatheringWithBLOBs.getSponsor())){
            criteria.andSponsorLike("%"+gatheringWithBLOBs.getSponsor().trim()+"%");
        }

        if (gatheringWithBLOBs.getEnrolltime() != null && !"".equals(gatheringWithBLOBs.getEnrolltime()) ){
            criteria.andEnrolltimeBetween(gatheringWithBLOBs.getStarttime(),gatheringWithBLOBs.getEndtime());
        }

        if (gatheringWithBLOBs.getAddress() != null && !"".equals(gatheringWithBLOBs.getAddress().trim())){
            criteria.andAddressLike("%"+gatheringWithBLOBs.getAddress().trim()+"%");
        }

        if (gatheringWithBLOBs.getCity() != null && !"".equals(gatheringWithBLOBs.getCity())){
            criteria.andCityEqualTo(gatheringWithBLOBs.getCity());
        }

        return gatheringMapper.selectByExampleWithBLOBs(gatheringExample);
    }

    /**
    *  根据id查询活动
    * @author chenyingxin
    * @date 2020/3/15 17:50
    * @param id: 活动id
    * @return: com.davina.gathering.pojo.Gathering
    **/
    @Cacheable(value="gathering",key="#id")
    public GatheringWithBLOBs findById(String id){
        return gatheringMapper.selectByPrimaryKey(id);
    }

    /**
    *  添加活动
    * @author chenyingxin
    * @date 2020/3/15 18:03
    * @param gatheringWithBLOBs: 活动对象
    * @return: void
    **/
    public void insert(GatheringWithBLOBs gatheringWithBLOBs){
        gatheringWithBLOBs.setId(idWorker.nextId()+"");
        gatheringMapper.insert(gatheringWithBLOBs);
    }

    /**
    *  更新活动信息
    * @author chenyingxin
    * @date 2020/3/15 18:04
    * @param gatheringWithBLOBs:
    * @return: void
    **/
    @CacheEvict(value="gathering",key="#gatheringWithBLOBs.id")
    public void update(GatheringWithBLOBs gatheringWithBLOBs){
        gatheringMapper.updateByPrimaryKeyWithBLOBs(gatheringWithBLOBs);
    }

    /**
    *  根据id删除活动
    * @author chenyingxin
    * @date 2020/3/15 18:05
    * @param id: 活动id
    * @return: void
    **/
    @CacheEvict(value="gathering",key="#id")
    public void delete(String id){
        gatheringMapper.deleteByPrimaryKey(id);
    }
}
