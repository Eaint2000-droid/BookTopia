package com.collab.g5.demo.news;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();

    News getNewsById(int nid);

    News addNews(News news); //save

    News updateNews(int nid, News news);

    void deleteNewsById(int nid);

    void delete(News news);
}
