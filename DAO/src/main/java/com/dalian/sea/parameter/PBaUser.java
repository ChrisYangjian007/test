package com.dalian.sea.parameter;

import com.dalian.sea.model.BaResource;
import com.dalian.sea.model.BaRoles;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.BaUserRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PBaUser
 *
 * @author xintao
 * @date 2018/1/22
 */
@Data
public class PBaUser extends BaUser implements Serializable {

    /**
     * 角色id、名字
     */
    private Long roleId;
    private String roleName;
    /**
     * 角色名字(PC)、id
     */
    private Long rolePCId;
    private String rolePCName;

    /**
     *角色名称（手机）、id
     */
    private Long rolePhoneId;
    private String rolePhoneName;

    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 创建者名称
     */
    private String createUserName;
    /**
     * 更新者名称
     */
    private String updateUserName;
    /**
     * 角色信息
     */
    private List<BaRoles> rolesList;

    /**
     * 角色信息关联
     */
    private List<PBaUserRole> userRoleList;

    /**
     * 用户对应资源
     */
    private List<PBaResource> userResource;

}
