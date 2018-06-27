package com.dalian.sea.parameter;

import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import lombok.Data;

import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/3/11
 */
@Data
public class PuReceiveApi extends PuReceive{
    private List<PuReceiveDetail> receiveDetails;
    private Long userId;
}
