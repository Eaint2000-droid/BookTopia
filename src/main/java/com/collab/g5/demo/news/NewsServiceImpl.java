package com.collab.g5.demo.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(int id){
        return newsRepository.findById(id).map(news -> {
            return news;
        }).orElse(null);
    }

    @Override
    public News addNews(News news) {
        Optional<News> newsExists = newsRepository.findById(news.getNID());
        if(newsExists.isPresent()){
            throw new IllegalStateException("News " + news.toString() + " exists");
        }
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(int nID, News freshNews) {
        News tempNews = newsRepository.findById(nID)
                .orElseThrow(() -> new IllegalStateException("News with id " + nID + " does not exist"));
        tempNews.setTitle(freshNews.getTitle());
        return tempNews;
    }

    @Override
    public void deleteNews(int id) {
        newsRepository.deleteById(id);
    }

}
