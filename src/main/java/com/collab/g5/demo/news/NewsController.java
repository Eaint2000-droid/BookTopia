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

    /**
     * List all news in the system
     * @return list of all news
     */
    @GetMapping("/hr")
    public List<News> getNews() {
        return newsServiceImpl.getAllNews();
    }

    /**
     * Search for news with the given nid
     * If there is no news with the given "nid", throw a NewsNotFoundException
     * @param nid
     * @return news with the given nid
     */
    @GetMapping("/emp/{nid}")
    public News getNewsById(@PathVariable int nid) throws NewsNotFoundException {
        News news = newsServiceImpl.getNewsById(nid);
        if(news == null) throw new NewsNotFoundException(nid);
        return newsServiceImpl.getNewsById(nid);
    }

    /**
     * Add a new news with POST request to "/hr"
     * @param news
     * @return the newly added news
     */
    @PostMapping("/hr")
    public News addBook(@RequestBody News news) throws NewsExistsException {
        News freshNews = newsServiceImpl.addNews(news);
        if(freshNews == null) throw new NewsExistsException("News " + freshNews.toString() + " exists");
        return newsServiceImpl.addNews(freshNews);
    }

    /**
     * If there is no news with the given "nid", throw a NewsNotFoundException
     * @param nid an int value
     * @param newNewsInfo a News object containing the new news info to be updated
     * @return the updated, or newly added news
     */
    @PutMapping("/hr/{nid}")
    public News updateNews(@PathVariable int nid, @RequestBody News newNewsInfo) throws NewsNotFoundException {
        News news = newsServiceImpl.updateNews(nid, newNewsInfo);
        if (news == null) throw new NewsNotFoundException(nid);
        return news;
    }

    /**
     * Remove a news with the DELETE request to "/hr/{nid}"
     * If there is no news with the given "nid", throw a NewsNotFoundException
     * @param nid
     */
    @DeleteMapping("/hr/{nid}")
    public void deleteNews(@PathVariable int nid) throws NewsNotFoundException{
        News news = newsServiceImpl.getNewsById(nid);
        if (news == null) {
            throw new BookingNotFoundException(nid);
        }
        newsServiceImpl.delete(getNewsById(nid));
    }

}

