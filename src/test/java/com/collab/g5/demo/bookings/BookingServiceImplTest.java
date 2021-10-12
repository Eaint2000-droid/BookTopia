package com.collab.g5.demo.bookings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {
    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private BookingsRepository bookingsRepository;

    @Test
    void testGetAllBookings() {
        ArrayList<Bookings> bookingsList = new ArrayList<Bookings>();
        when(this.bookingsRepository.findAll()).thenReturn(bookingsList);
        List<Bookings> actualAllBookings = this.bookingServiceImpl.getAllBookings();
        assertSame(bookingsList, actualAllBookings);
        assertTrue(actualAllBookings.isEmpty());
        verify(this.bookingsRepository).findAll();
    }

    @Test
    void testGetBookingsById() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);
        Optional<Bookings> ofResult = Optional.<Bookings>of(bookings);
        when(this.bookingsRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(bookings, this.bookingServiceImpl.getBookingsById(1));
        verify(this.bookingsRepository).findById((Integer) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testUpdateBookings() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);
        Optional<Bookings> ofResult = Optional.<Bookings>of(bookings);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setCompany(company1);
        user1.setBookingVetting(new ArrayList<BookingVetting>());
        user1.setNewsList(new ArrayList<News>());
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setEnabled(true);
        user1.setBookings(new ArrayList<Bookings>());
        user1.setLocked(true);
        user1.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings1 = new Bookings();
        bookings1.setStatus("Status");
        bookings1.setUser(user1);
        bookings1.setBDate(LocalDate.ofEpochDay(1L));
        bookings1.setBookingVettings(new ArrayList<BookingVetting>());
        bookings1.setBid(1);
        when(this.bookingsRepository.save((Bookings) any())).thenReturn(bookings1);
        when(this.bookingsRepository.findById((Integer) any())).thenReturn(ofResult);

        Company company2 = new Company();
        company2.setUsers(new ArrayList<User>());
        company2.setName("Name");
        company2.setSize(3L);
        company2.setRegulationLimit(new ArrayList<RegulationLimit>());
        company2.setCid(1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setCompany(company2);
        user2.setBookingVetting(new ArrayList<BookingVetting>());
        user2.setNewsList(new ArrayList<News>());
        user2.setFname("Fname");
        user2.setLname("Lname");
        user2.setEnabled(true);
        user2.setBookings(new ArrayList<Bookings>());
        user2.setLocked(true);
        user2.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings2 = new Bookings();
        bookings2.setStatus("Status");
        bookings2.setUser(user2);
        bookings2.setBDate(LocalDate.ofEpochDay(1L));
        bookings2.setBookingVettings(new ArrayList<BookingVetting>());
        bookings2.setBid(1);
        assertSame(bookings1, this.bookingServiceImpl.updateBookings(1, bookings2));
        verify(this.bookingsRepository).findById((Integer) any());
        verify(this.bookingsRepository).save((Bookings) any());
    }

    @Test
    void testUpdateBookings2() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);
        when(this.bookingsRepository.save((Bookings) any())).thenReturn(bookings);
        when(this.bookingsRepository.findById((Integer) any())).thenReturn(Optional.<Bookings>empty());

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setCompany(company1);
        user1.setBookingVetting(new ArrayList<BookingVetting>());
        user1.setNewsList(new ArrayList<News>());
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setEnabled(true);
        user1.setBookings(new ArrayList<Bookings>());
        user1.setLocked(true);
        user1.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings1 = new Bookings();
        bookings1.setStatus("Status");
        bookings1.setUser(user1);
        bookings1.setBDate(LocalDate.ofEpochDay(1L));
        bookings1.setBookingVettings(new ArrayList<BookingVetting>());
        bookings1.setBid(1);
        assertNull(this.bookingServiceImpl.updateBookings(1, bookings1));
        verify(this.bookingsRepository).findById((Integer) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.bookingsRepository).delete((Bookings) any());

        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);
        this.bookingServiceImpl.delete(bookings);
        verify(this.bookingsRepository).delete((Bookings) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testDeleteById() {
        doNothing().when(this.bookingsRepository).deleteById((Integer) any());
        this.bookingServiceImpl.deleteById(1);
        verify(this.bookingsRepository).deleteById((Integer) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testBookingExists() {
        when(this.bookingsRepository.existsById((Integer) any())).thenReturn(true);
        assertTrue(this.bookingServiceImpl.bookingExists(1));
        verify(this.bookingsRepository).existsById((Integer) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testBookingExists2() {
        when(this.bookingsRepository.existsById((Integer) any())).thenReturn(false);
        assertFalse(this.bookingServiceImpl.bookingExists(1));
        verify(this.bookingsRepository).existsById((Integer) any());
        assertTrue(this.bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testSave() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);
        when(this.bookingsRepository.save((Bookings) any())).thenReturn(bookings);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setCompany(company1);
        user1.setBookingVetting(new ArrayList<BookingVetting>());
        user1.setNewsList(new ArrayList<News>());
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setEnabled(true);
        user1.setBookings(new ArrayList<Bookings>());
        user1.setLocked(true);
        user1.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings1 = new Bookings();
        bookings1.setStatus("Status");
        bookings1.setUser(user1);
        bookings1.setBDate(LocalDate.ofEpochDay(1L));
        bookings1.setBookingVettings(new ArrayList<BookingVetting>());
        bookings1.setBid(1);
        assertSame(bookings, this.bookingServiceImpl.save(bookings1));
        verify(this.bookingsRepository).save((Bookings) any());
    }
}

