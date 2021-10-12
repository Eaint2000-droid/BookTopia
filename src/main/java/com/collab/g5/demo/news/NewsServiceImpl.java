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
        Optional<News> newsExists = newsRepository.findById(news.getNid());
        if(newsExists.isPresent()){
            return null;
        }
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(int nid, News freshNews) {
        return newsRepository.findById(nid).map(news -> {
            news.setDate(freshNews.getDate());
            news.setTitle(freshNews.getTitle());
            news.setContent(freshNews.getContent());
            return newsRepository.save(news);
        }).orElse(null);
    }

    @Override
    public void delete(News news) {
        newsRepository.delete(news);
    }

    @Override
    public void deleteNewsById(int nid) {
        newsRepository.deleteById(nid);
    }


}
