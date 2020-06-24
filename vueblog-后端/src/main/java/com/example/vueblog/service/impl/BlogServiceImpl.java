package com.example.vueblog.service.impl;

import com.example.vueblog.entity.Blog;
import com.example.vueblog.mapper.BlogMapper;
import com.example.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 月落乌啼
 * @since 2020-06-14
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
