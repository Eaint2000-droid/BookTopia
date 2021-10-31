package com.collab.g5.demo.news;

import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.news.NewsExistsException;
import com.collab.g5.demo.exceptions.news.NewsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/news")
public class NewsController {

    private NewsServiceImpl newsServiceImpl;

    @Autowired
    public NewsController(NewsServiceImpl newsServiceImpl){
        this.newsServiceImpl = newsServiceImpl;
    }

    @GetMapping("/hr/getAll")
    public List<News> getNews() {
        return newsServiceImpl.getAllNews();
    }

    //retrieves news by nid
    @GetMapping("/emp/get/{nid}")
    public News getNewsById(@PathVariable int nid) throws NewsNotFoundException {
        News news = newsServiceImpl.getNewsById(nid);
        if(news == null) throw new NewsNotFoundException(nid);
        return newsServiceImpl.getNewsById(nid);
    }

    //add new news
    @PostMapping("/hr/create/newNews")
    public News addBook(@RequestBody News news) throws NewsExistsException {
        News freshNews = newsServiceImpl.addNews(news);
        if(freshNews == null) throw new NewsExistsException("News " + freshNews.toString() + " exists");
        return newsServiceImpl.addNews(freshNews);
    }

    //update news
    @PutMapping("/hr/update/news/{nid}")
    public News updateNews(@PathVariable int nid, @RequestBody News newNewsInfo) throws NewsNotFoundException {
        News news = newsServiceImpl.updateNews(nid, newNewsInfo);
        if (news == null) throw new NewsNotFoundException(nid);
        return news;
    }

    //delete news
    @DeleteMapping("/hr/del/{nid}")
    public void deleteNews(@PathVariable int nid) throws BookingNotFoundException {
        News news = newsServiceImpl.getNewsById(nid);
        if (news == null) {
            throw new BookingNotFoundException(nid);
        }
        newsServiceImpl.delete(getNewsById(nid));
    }

}

