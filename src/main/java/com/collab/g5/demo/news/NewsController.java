package com.collab.g5.demo.news;

import com.collab.g5.demo.news.News;
import com.collab.g5.demo.news.NewsService;
import com.collab.g5.demo.news.NewsServiceImpl;
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

    @GetMapping("/com/collab/g5/demo/news")
    public List<News> getNews(){
        return newsService.getAllNews();
    }
}
