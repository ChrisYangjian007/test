package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsProductionProcess;
import lombok.Data;

/**
 * Created by Administrator on 2018/4/10.
 */
@Data
public class PZsProductionProcess extends ZsProductionProcess {

    private String createUserName;

    private String updateUserName;
}
