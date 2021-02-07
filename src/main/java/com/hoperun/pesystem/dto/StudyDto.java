package com.hoperun.pesystem.dto;

import com.hoperun.pesystem.model.Study;
import lombok.Data;

import java.util.Date;

/**
 * @class: StudyDto
 * @author: ljd
 * @create: 2021/2/7 15:15
 **/
@Data
public class StudyDto {
    private  Study study;
    private Integer praise;

}
