package com.hoperun.pesystem.dto;

import lombok.Data;

import java.util.Date;
@Data
/**
 * @class: StudyDto
 * @author: ljd
 * @create: 2021/2/7 15:15
 **/
public class StudyDto {
    private Integer stuId;
    private String stuTitle;
    private String stuIntroduce;
//    private Integer stuTypeId;
    private Integer stuBrowseNumber;
    private Integer stuPraiseNumber;
//    private String stuPhotoIndexBigUrl;
//    private String stuPhotoIndexSmUrl;
    private Date stuStartTime;
    private Date stuEndTime;
//    private Integer stuFlag;
//    private String stuInformation;
    private Integer praise;

}
