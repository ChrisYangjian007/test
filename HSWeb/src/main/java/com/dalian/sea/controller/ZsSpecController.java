package com.dalian.sea.controller;

import com.dalian.sea.service.ZsSpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ZsSpecController
 *
 * @author xintao
 * @date 2018/1/25
 */
@Slf4j
@Controller
@RequestMapping(value = "/zsSpec")
public class ZsSpecController {
    @Autowired
    private ZsSpecService zsSpecService;






}
