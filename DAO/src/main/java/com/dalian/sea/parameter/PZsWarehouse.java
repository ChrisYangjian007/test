package com.dalian.sea.parameter;

import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.model.ZsWarehouseUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */
@Data
public class PZsWarehouse extends ZsWarehouse implements Serializable{

    /**
     *创建人名称
     */
    private String createUserName;

    /**
     *修改人名称
     */
    private String updateUserName;

    /**
     * 主要负责人权限
     */
    private String permissions;

    /**
     *公司名称
     */
    private String companyName;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     *
     */
    List<PWarehouseUser> zsWarehouseUserList;

    String warehouseUserName;

    List<BaUser> warehouseUser;

    String warehouseName;
    Long userWarehouseId;

    /**
     * 图片需要字段
     */
    private Integer warehouseImageSize;
    private String warehouseImageCreateName;
    private String warehouseImageUpdateName;
    private String warehouseImageCreateDate;
    private String warehouseImageUpdateDate;


}
