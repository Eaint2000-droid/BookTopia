package com.collab.g5.demo.news;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(int id);
    News addNews(News news);
    News updateNews(int nID, News news);
    void deleteNews(int id);
}
