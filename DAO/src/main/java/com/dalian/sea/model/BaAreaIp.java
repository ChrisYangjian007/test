package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ba_area_ip")
@Data
public class BaAreaIp implements Serializable {
    @Column(name = "ip_start")
    private String ipStart;

    @Column(name = "ip_end")
    private String ipEnd;

    @Column(name = "ip_start_num")
    private Long ipStartNum;

    @Column(name = "ip_end_num")
    private Long ipEndNum;

    private String continent;

    private String country;

    private String province;

    private String city;

    private String district;

    private String isp;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "country_english")
    private String countryEnglish;

    @Column(name = "country_code")
    private String countryCode;

    private String longitude;

    private String latitude;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ipStart=").append(ipStart);
        sb.append(", ipEnd=").append(ipEnd);
        sb.append(", ipStartNum=").append(ipStartNum);
        sb.append(", ipEndNum=").append(ipEndNum);
        sb.append(", continent=").append(continent);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", isp=").append(isp);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", countryEnglish=").append(countryEnglish);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}