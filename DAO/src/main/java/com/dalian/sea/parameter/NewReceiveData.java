package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsEnterprise;
import lombok.Data;

import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/3/11
 */
@Data
public class NewReceiveData {
    private List<ZsEnterprise> zsEnterprises;
    private String receiveNode;
}
