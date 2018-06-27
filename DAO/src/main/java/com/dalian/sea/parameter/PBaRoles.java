package com.dalian.sea.parameter;

import com.dalian.sea.model.BaRoles;
import lombok.Data;

import java.io.Serializable;

/**
 * PBaRoles
 *
 * @author xintao
 * @date 2018/1/15
 */
@Data
public class PBaRoles extends BaRoles implements Serializable{

    private Integer roleNumber;

    private String companyName;



}
