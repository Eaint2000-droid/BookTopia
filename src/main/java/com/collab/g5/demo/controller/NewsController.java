package com.collab.g5.demo.controller;

import com.collab.g5.demo.entity.News;
import com.collab.g5.demo.service.NewsService;
import com.collab.g5.demo.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/News")
public class NewsController {
    @Autowired
    NewsService newsService = new NewsServiceImpl();

    @GetMapping("/news")
    public List<News> getNews(){
        return newsService.getAllNews();
    }
}
