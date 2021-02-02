package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

}
