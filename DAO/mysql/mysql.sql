--------------------------------
---- 2018/4/26 添加仓库字段仓库类型
--------------------------------
ALTER TABLE `zs_warehouse`
ADD COLUMN `warehouse_type`  int(255) NULL DEFAULT 0 COMMENT '仓库类型 0 无 1 活参库' AFTER `c_name`;

--------------------------------
---- 2018/4/26 新建仓库负责人表
--------------------------------
CREATE TABLE `zs_warehouse_user` (
  `warehouse_user_id`  bigint(19) NOT NULL AUTO_INCREMENT COMMENT '仓库负责人表id' ,
  `user_id`  bigint(19) NULL DEFAULT NULL COMMENT '用户id' ,
  `warehouse_id`  bigint(19) NULL DEFAULT NULL COMMENT '仓库id' ,
  `create_date`  timestamp NULL DEFAULT NULL COMMENT '创建时间' ,
  `create_user_id`  bigint(19) NULL DEFAULT NULL COMMENT '创建人id' ,
  `update_date`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  `update_user_id`  bigint(19) NULL DEFAULT NULL COMMENT '修改人id' ,
  `status`  tinyint(4) NULL DEFAULT 1 COMMENT '状态（1-正常 2-禁用 6-删除）' ,
  PRIMARY KEY (`warehouse_user_id`)
)
  COMMENT='仓库负责人表'
;

--------------------------------
---- 2018/5/7 修改表单设置明细键值表的更新时间按照当前时间戳自动更新
--------------------------------
ALTER TABLE `ba_form_attribute_value`
  MODIFY COLUMN `update_date`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' AFTER `create_date`;

ALTER TABLE `pu_receive_detail`
ADD COLUMN `qrcode`  varchar(255) NULL DEFAULT NULL COMMENT '箱码二维码编码' AFTER `is_print`;

--------------------------------
---- 2018/5/21 添加入库详情表字段入库箱码
--------------------------------
ALTER TABLE `pu_enter_stock_detail`
ADD COLUMN `box_code`  varchar(255) NULL COMMENT '入库箱码' AFTER `is_sea_cucumber`;

--------------------------------
---- 2018/5/29 修改供应商表格注释
--------------------------------
ALTER TABLE `zs_enterprise`
  COMMENT='供应商表';

--------------------------------
---- 2018/5/29 供应商表格添加供应商类型、营业执照、营业执照有效期、生产许可证、生产许可证有效期、其他图片、其他有效期字段
--------------------------------
ALTER TABLE `zs_enterprise`
  ADD COLUMN `enterprise_type`  tinyint(4) NULL DEFAULT 1 COMMENT '供应商类型（1-原料供应商 2-其他供应商）' AFTER `category`,
  ADD COLUMN `business_license_image`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照' AFTER `enterprise_type`,
  ADD COLUMN `business_license_date`  timestamp NULL DEFAULT NULL COMMENT '营业执照有效期' AFTER `business_license_image`,
  ADD COLUMN `production_license_image`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产许可证' AFTER `business_license_date`,
  ADD COLUMN `production_license_date`  timestamp NULL DEFAULT NULL COMMENT '生产许可证有效期' AFTER `production_license_image`,
  ADD COLUMN `other_license_image`  json NULL COMMENT '其他图片' AFTER `production_license_date`,
  ADD COLUMN `other_license_date`  timestamp NULL DEFAULT NULL COMMENT '其他有效期' AFTER `other_license_image`;

--------------------------------
---- 2018/5/31 证书管理表添加产品标准号字段
--------------------------------
ALTER TABLE `zs_certificate_manage`
  ADD COLUMN `product_standards`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品标准号' AFTER `production_process_id`;

--------------------------------
---- 以上全部执行
--------------------------------