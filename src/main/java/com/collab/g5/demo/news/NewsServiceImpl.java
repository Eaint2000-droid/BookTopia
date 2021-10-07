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
            throw new IllegalStateException("News " + news.toString() + " exists");
        }
        return newsRepository.save(news);
    }

//    @Override
//    public News addNews(News news) {
//        List<News> newsExists = newsRepository.findByTitle(news.getTitle());
//        if(newsExists.size() == 0)
//            return newsRepository.save(news);
//        else
//            return null;
//    }


    @Override
    public News updateNews(int nid, News freshNews) {
        News tempNews = newsRepository.findById(nid)
                .orElseThrow(() -> new IllegalStateException("News with id " + nid + " does not exist"));
        tempNews.setTitle(freshNews.getTitle());
        return tempNews;
    }

    @Override
    public void deleteNewsById(int nid) {
        newsRepository.deleteById(nid);
    }

    @Override
    public void delete(News news) {
        newsRepository.delete(news);
    }
}
