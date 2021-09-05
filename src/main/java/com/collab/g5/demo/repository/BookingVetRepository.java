package com.collab.g5.demo.repository;

import com.collab.g5.demo.entity.BookingVetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingVetRepository extends JpaRepository<BookingVetting, Integer> {
}
