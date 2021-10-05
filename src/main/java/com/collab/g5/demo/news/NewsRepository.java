package com.collab.g5.demo.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    // derived query to find news by title
    List<News> findByTitle(String title);

}
