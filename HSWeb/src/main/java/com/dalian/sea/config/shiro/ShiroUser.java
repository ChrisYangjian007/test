package com.dalian.sea.config.shiro;

import com.dalian.sea.model.BaResource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author 杨文波
 * @date 2017/12/11
 * @see com.dalian.sea.config.shiro.MyShiroRealm
 */
@Data
public class ShiroUser implements Serializable {
    private Long id;
    private final String loginName;
    private String name;
    private Set<String> urlSet;
    private Set<String> roles;
    private List<BaResource> list;
    private Long companyId;

    public ShiroUser(String loginName) {
        this.loginName = loginName;
    }

    public ShiroUser(Long id, String loginName, String name) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

}