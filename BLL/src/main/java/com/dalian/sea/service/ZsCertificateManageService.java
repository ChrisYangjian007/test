package com.dalian.sea.service;

import com.dalian.sea.model.ZsCertificateManage;
import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.parameter.PZsCertificateManage;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
public interface ZsCertificateManageService {

    /**
     * 证书管理
     * @param page
     * @param rows
     * @return
     */
    List<PZsCertificateManage> getCertificateManageForGrid(Integer page,Integer rows);

    /**
     * 添加证书
     * @param zsCertificateManage
     * @return
     */
    Boolean addCertificateManage(ZsCertificateManage zsCertificateManage);

    /**
     * 删除证书
     * @param zsCertificateManage
     * @return
     */
    Boolean deleteCertificateManageById(ZsCertificateManage zsCertificateManage);

    /**
     * 通过smallProductTypeID和productionLicense获取
     * @param zsCertificateManage
     * @return
     */
    ZsCertificateManage getCertificateManageBySProductTypeAndName(ZsCertificateManage zsCertificateManage);

    /**
     * 通过certificateManageId获取
     * @param certificateManageId
     * @return
     */
    PZsCertificateManage getCertificateManageByCertificateManageId(Long certificateManageId);

    /**
     * 修改证书
     * @param zsCertificateManage
     * @return
     */
    Boolean updateCertificateManage(ZsCertificateManage zsCertificateManage);

    /**
     * 增加内容
     * @param zsCertificateManage
     * @return
     */
    Boolean addCertificateContent(ZsCertificateManage zsCertificateManage);

    /**
     * 通过smallProductTypeId获取
     * @param smallProductTypeId
     * @return
     */
    PZsCertificateManage getCertificateManageBySmallProductTypeId(Long smallProductTypeId);
}
