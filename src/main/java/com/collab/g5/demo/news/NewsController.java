package com.collab.g5.demo.news;

import com.collab.g5.demo.exceptions.bookings.BookingNotFoundException;
import com.collab.g5.demo.exceptions.news.NewsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService injectNewsService){
        this.newsService = injectNewsService;
    }

    @GetMapping("/hr/getAll")
    public List<News> getNews() {
        return newsService.getAllNews();
    }

    //retrieves news by nid
    @GetMapping("/emp/get/{nid}")
    public News getNewsById(@PathVariable int nid) {
        return newsService.getNewsById(nid);
    }

    //add new news
    @PostMapping("/hr/create/newNews")
    public News addBook(@RequestBody News news) {
        return newsService.addNews(news);
    }

    //update news
    @PutMapping("/hr/update/news/{nid}")
    public News updateNews(@PathVariable int nid, @RequestBody News newNewsInfo) {
        News news = newsService.updateNews(nid, newNewsInfo);
        if (news == null) throw new NewsNotFoundException(nid);
        return news;
    }

    //delete news
    @DeleteMapping("/hr/del/{nid}")
    public void deleteNews(@PathVariable int nid) {
        News news = newsService.getNewsById(nid);
        if (news == null) {
            throw new BookingNotFoundException(nid);
        }
        newsService.delete(getNewsById(nid));
    }

}

