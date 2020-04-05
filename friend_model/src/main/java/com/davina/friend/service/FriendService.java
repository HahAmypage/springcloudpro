package com.davina.friend.service;

import com.davina.friend.client.UserClient;
import com.davina.friend.dao.FriendMapper;
import com.davina.friend.dao.NoFriendMapper;
import com.davina.friend.pojo.Friend;
import com.davina.friend.pojo.FriendExample;
import com.davina.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FriendService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/4/4 15:10
 * @Version 1.0
 */
@Service
public class FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private NoFriendMapper noFriendMapper;

    @Autowired
    private UserClient userClient;
    /**
    *  添加好友
    * @author chenyingxin
    * @date 2020/4/4 15:18
    * @param id:
    * @param friendId:
    * @return: int
    **/
    @Transactional
    public int addFriend(String id, String friendId) {
        // 判断如果用户已经添加了这个好友，则不进行任何操作返回0
        FriendExample friendExample = new FriendExample();
        FriendExample.Criteria criteria = friendExample.createCriteria();
        criteria.andFriendidEqualTo(friendId).andUseridEqualTo(id);
        if (friendMapper.countByExample(friendExample) > 0){
            return 0;
        }
        // 向friend表中添加记录
        Friend friend = new Friend();
        friend.setUserid(id);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        friendMapper.insert(friend);
        // 判断对方是否like你，如果是把islike设为1
        FriendExample friendExample1 = new FriendExample();
        FriendExample.Criteria criteria1 = friendExample1.createCriteria();
        criteria1.andUseridEqualTo(friendId).andFriendidEqualTo(id);
        if (friendMapper.countByExample(friendExample1) > 0){
            Friend friend1 = new Friend();
            friend1.setIslike("1");
            friendMapper.updateByExampleSelective(friend1,friendExample);
            friendMapper.updateByExampleSelective(friend1,friendExample1);
        }
        userClient.incFollowcount(id,1);
        userClient.incFanscount(friendId,1);
        return 1;
    }

    /**
    *  向不喜欢表添加记录
    * @author chenyingxin
    * @date 2020/4/4 17:19
    * @param id:
    * @param friendId:
    * @return: void
    **/
    @Transactional
    public void addNoFriend(String id, String friendId) {

        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(id);
        noFriend.setFriendid(friendId);
        noFriendMapper.insert(noFriend);

    }

    /**
    *  删除好友
    * @author chenyingxin
    * @date 2020/4/4 17:20
    * @param id:
    * @param friendId:
    * @return: void
    **/
    @Transactional
    public void deleteFriend(String id, String friendId) {

        Friend friend = new Friend();
        friend.setUserid(id);
        friend.setFriendid(friendId);
        friendMapper.deleteByPrimaryKey(friend);
        Friend friend1 = new Friend();
        friend1.setIslike("0");
        FriendExample friendExample = new FriendExample();
        FriendExample.Criteria criteria = friendExample.createCriteria();
        criteria.andUseridEqualTo(friendId).andFriendidEqualTo(id);
        friendMapper.updateByExampleSelective(friend1,friendExample);
        addNoFriend(id, friendId);
        userClient.incFanscount(friendId,-1);
        userClient.incFollowcount(id,-1);
    }
}
