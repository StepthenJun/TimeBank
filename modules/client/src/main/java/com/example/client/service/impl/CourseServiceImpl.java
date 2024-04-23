package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.client.domain.Course;

import com.example.client.mapper.CourseMapper;

import com.example.client.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * @program: TimeBank
 * @ClassName: CourseServiceImpl
 * @description:
 * @author: kai
 * @create: 2024-04-07 17:03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService
{

}