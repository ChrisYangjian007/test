package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
public class Ireport implements Serializable {
    /**
     * title
     */
    private String title;

    /**
     * titleOne
     */
    @Column(name = "title_one")
    private String titleOne;

    /**
     * titleTwo
     */
    @Column(name = "title_two")
    private String titleTwo;

    /**
     * titleThree
     */
    @Column(name = "title_three")
    private String titleThree;

    /**
     * titleFour
     */
    @Column(name = "title_four")
    private String titleFour;

    /**
     * listIndex
     */
    @Column(name = "list_index")
    private String listIndex;

    /**
     * listOne
     */
    @Column(name = "list_one")
    private String listOne;

    /**
     * listTwo
     */
    @Column(name = "list_two")
    private String listTwo;

    /**
     * listThree
     */
    @Column(name = "list_three")
    private String listThree;

    /**
     * listFour
     */
    @Column(name = "list_four")
    private String listFour;

    /**
     * listFive
     */
    @Column(name = "list_five")
    private String listFive;

    /**
     * listSix
     */
    @Column(name = "list_six")
    private String listSix;

    /**
     * listSeven
     */
    @Column(name = "list_seven")
    private String listSeven;

    /**
     * listEight
     */
    @Column(name = "list_eight")
    private String listEight;

    /**
     * listNine
     */
    @Column(name = "list_nine")
    private String listNine;

    /**
     * listTen
     */
    @Column(name = "list_ten")
    private String listTen;

    /**
     * listSize
     */
    @Column(name = "list_size")
    private String listSize;

    /**
     * footerOne
     */
    @Column(name = "footer_one")
    private String footerOne;

    /**
     * footerTwo
     */
    @Column(name = "footer_two")
    private String footerTwo;

    /**
     * footerThree
     */
    @Column(name = "footer_three")
    private String footerThree;

    /**
     * footerFour
     */
    @Column(name = "footer_four")
    private String footerFour;

    /**
     * footerFive
     */
    @Column(name = "footer_five")
    private String footerFive;

    /**
     * footerSix
     */
    @Column(name = "footer_six")
    private String footerSix;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", title=").append(title);
        sb.append(", titleOne=").append(titleOne);
        sb.append(", titleTwo=").append(titleTwo);
        sb.append(", titleThree=").append(titleThree);
        sb.append(", titleFour=").append(titleFour);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", listOne=").append(listOne);
        sb.append(", listTwo=").append(listTwo);
        sb.append(", listThree=").append(listThree);
        sb.append(", listFour=").append(listFour);
        sb.append(", listFive=").append(listFive);
        sb.append(", listSix=").append(listSix);
        sb.append(", listSeven=").append(listSeven);
        sb.append(", listEight=").append(listEight);
        sb.append(", listNine=").append(listNine);
        sb.append(", listTen=").append(listTen);
        sb.append(", listSize=").append(listSize);
        sb.append(", footerOne=").append(footerOne);
        sb.append(", footerTwo=").append(footerTwo);
        sb.append(", footerThree=").append(footerThree);
        sb.append(", footerFour=").append(footerFour);
        sb.append(", footerFive=").append(footerFive);
        sb.append(", footerSix=").append(footerSix);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}