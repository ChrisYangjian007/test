package com.dalian.sea.parameter;

import com.dalian.sea.model.BaRoles;
import com.dalian.sea.model.BaUserRole;
import lombok.Data;

/**
 * Created by Administrator on 2018/3/9.
 */
@Data
public class PBaUserRole extends BaUserRole{

    /**
     * 角色
     */
    private BaRoles baRoles;
}
