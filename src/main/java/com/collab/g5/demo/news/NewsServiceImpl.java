package com.collab.g5.demo.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

}
