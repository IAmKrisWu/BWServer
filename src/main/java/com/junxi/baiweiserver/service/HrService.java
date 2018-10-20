package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.HrMapper;
import com.junxi.baiweiserver.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
public class HrService implements UserDetailsService {
    @Autowired
    HrMapper hrMapper;

    @Autowired
    RoleService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if(hr==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
//        查询用户对应的角色
        hr.setRoles(service.queryRolesByHrId(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHrWithRoles(){
        return hrMapper.getAllHrWithRoles();
    }

    public int updateEnabled(Hr hr) {
        return hrMapper.updateEnabled(hr);
    }

    public int updateRoles(Integer[] rids,Integer hrid){
        if(rids==null||rids.length==0){
            return -1;
        }
        //        先删除中间表中的数据
        hrMapper.deleteRoles(hrid);
//        向中间表插入数据
        hrMapper.addRoles(rids,hrid);
        return 1;
    }

    public int delHr(Integer hrid) {
//        先删除中间表
        hrMapper.deleteRoles(hrid);
        hrMapper.delMsg(hrid);
        return hrMapper.delHr(hrid);
    }
}
