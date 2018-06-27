package com.dalian.sea.controller;

import com.dalian.sea.model.BaAreaIp;
import com.dalian.sea.service.BaAreaIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BaAreaIpController
 *
 * @author TONE
 * @date 2018/4/8.
 */
@Controller
@RequestMapping(value = "/areaIp")
public class BaAreaIpController {
    @Autowired
    private BaAreaIpService baAreaIpService;



}
