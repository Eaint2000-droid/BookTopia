package com.collab.g5.demo.news;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();

    News getNewsById(int id);

    News addNews(News news); //save

    News updateNews(int nID, News news);

    void deleteNewsById(int id);

    void delete(News news);
}
