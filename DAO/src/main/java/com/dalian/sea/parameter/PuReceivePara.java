package com.dalian.sea.parameter;

import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import lombok.Data;

import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/2/27
 */
@Data
public class PuReceivePara extends PuReceive{
    private List<PuReceiveDetail> receiveDetails;
}
