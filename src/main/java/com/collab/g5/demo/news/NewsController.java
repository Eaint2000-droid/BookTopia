package com.collab.g5.demo.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/News")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/com/collab/g5/demo/news")
    public List<News> getNews(){
        return newsService.getAllNews();
    }

    //retrieves news by nID
    @GetMapping("/com/collab/g5/demo/news/{nID}")
    public News getNewsById(@PathVariable int nID)
    {
        return newsService.getNewsById(nID);
    }

    //add new news
    @PostMapping("/com/collab/g5/demo/news/addNewBook")
    @ResponseStatus(HttpStatus.CREATED)
    public News addBook(@RequestBody News news){
        return newsService.addNews(news);
    }

    //update news
    @PutMapping("/com/collab/g5/demo/news/updateNews{nID}")
    public News updateNews(@PathVariable int nID, @RequestBody News newNewsInfo){
        News news = newsService.updateNews(nID, newNewsInfo);
        if(news == null) throw new NewsNotFoundException(nID);
        return news;
    }

    //delete news
    @DeleteMapping("/com/collab/g5/demo/news/deleteNews{nID}")
    public void deleteNews(@PathVariable int nID, @RequestBody News newNewsInfo){
        try{
            newsService.deleteNews(nID);
        }catch(EmptyResultDataAccessException e) {
            throw new NewsNotFoundException(nID);
        }
    }

}

