package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsCertificateManage;
import com.dalian.sea.parameter.PZsCertificateManage;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsCertificateManageMapper extends Mapper<ZsCertificateManage> {

    /**
     * 获取所有的证书管理
     * @return
     */
    List<PZsCertificateManage> getCertificateManageForGrid();

    /**
     *添加证书
     * @param zsCertificateManage
     * @return
     */
    Integer addCertificateManage(ZsCertificateManage zsCertificateManage);

    /**
     * 删除证书
     * @param zsCertificateManage
     * @return
     */
    Integer deleteCertificateManageById(ZsCertificateManage zsCertificateManage);

    /**
     *通过smallProductTypeID和productionLicense获取
     * @param zsCertificateManage
     * @return
     */
    ZsCertificateManage getCertificateManageBySProductTypeAndName(ZsCertificateManage zsCertificateManage);

    /**
     *通过certificateManageId获取
     * @param certificateManageId
     * @return
     */
    PZsCertificateManage getCertificateManageByCertificateManageId(Long certificateManageId);

    /**
     * 修改证书
     * @param zsCertificateManage
     * @return
     */
    Integer updateCertificateManage(ZsCertificateManage zsCertificateManage);

    /**
     * 增加内容
     * @param zsCertificateManage
     * @return
     */
    Integer addCertificateContent(ZsCertificateManage zsCertificateManage);

    /**
     * 通过smallProductTypeId获取
     * @param smallProductTypeId
     * @return
     */
    PZsCertificateManage getCertificateManageBySmallProductTypeId(Long smallProductTypeId);
}