package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyService {
    @Autowired
    private StudyMapper studyMapper;
}
