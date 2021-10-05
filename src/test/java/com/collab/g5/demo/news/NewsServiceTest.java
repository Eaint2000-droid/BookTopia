package com.collab.g5.demo.news;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    @Mock
    private NewsRepository news;

    @InjectMocks
    private NewsServiceImpl newsService;

    @Test
    public void getAllNewsTest()
    {
        List<News> list = new ArrayList<News>();
        News newsOne = new News(1, LocalDate.now(), "Title1", "content1");
        News newsTwo = new News(2, LocalDate.now(), "Title2", "content2");
        News newsThree = new News(3, LocalDate.now(), "Title3", "content3");

        list.add(newsOne);
        list.add(newsTwo);
        list.add(newsThree);

        when(news.findAll()).thenReturn(list);

        //test
        List<News> newsList = newsService.getAllNews();

        assertEquals(3, newsList.size());
        verify(news, times(1)).findAll();
    }

    @Test
    public void getNewsByIdTest()
    {
        when(news.findById(1)).thenReturn(java.util.Optional.of(new News(5, LocalDate.now(), "Title5", "content5")));

        News news = newsService.getNewsById(1);

        assertEquals("5", news.getNid());
        assertEquals(LocalDate.now(), news.getDate());
        assertEquals("Title5", news.getTitle());
        assertEquals("content5", news.getContent());
    }

    @Test
    public void createNewsTest()
    {
        News news1 = new News(5,LocalDate.now(),"Title5","content5");

        newsService.addNews(news1);

        verify(news, times(1)).findById(news1.getNid());
    }

    @Test
    void addNews_NewID_ReturnSavedNews(){
        // arrange ***
        News new1 = new News(7, LocalDate.now(), "797097", "kjhkjh");
        // mock the "findbytitle" operation
//        when(news.findById(any(Integer.class))).thenReturn(java.util.Optional.of(new1));
        // mock the "save" operation
        when(news.save(any(News.class))).thenReturn(new1);

        // act ***
        News savedNews = newsService.addNews(new1);

        // assert ***
        assertNotNull(savedNews);
        verify(news).findByTitle(new1.getTitle());
        verify(news).save(new1);
    }


}
