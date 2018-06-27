package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsWarehouseUser;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/27.
 */
@Data
public class PWarehouseUser extends ZsWarehouseUser implements Serializable{

    String userName;
}
