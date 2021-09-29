package com.collab.g5.demo.news;

import com.collab.g5.demo.exceptions.news.NewsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("" +
            "")
    public List<News> getNews() {
        return newsService.getAllNews();
    }

    //retrieves news by nid
    @GetMapping("/com/collab/g5/demo/news/{nid}")
    public News getNewsById(@PathVariable int nid) {
        return newsService.getNewsById(nid);
    }

    //add new news
    @PostMapping("/com/collab/g5/demo/news/addNewBook")
    @ResponseStatus(HttpStatus.CREATED)
    public News addBook(@RequestBody News news) {
        return newsService.addNews(news);
    }

    //update news
    @PutMapping("/com/collab/g5/demo/news/updateNews{nid}")
    public News updateNews(@PathVariable int nid, @RequestBody News newNewsInfo) {
        News news = newsService.updateNews(nid, newNewsInfo);
        if (news == null) throw new NewsNotFoundException(nid);
        return news;
    }

    //delete news
    @DeleteMapping("/com/collab/g5/demo/news/deleteNews{nid}")
    public void deleteNews(@PathVariable int nid, @RequestBody News newNewsInfo) {
        try {
            newsService.deleteNewsById(nid);
        } catch (EmptyResultDataAccessException e) {
            throw new NewsNotFoundException(nid);
        }
    }

}

