package com.collab.g5.demo.bookings;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

@ContextConfiguration(classes = {BookingVetServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingVetServiceImplTest {
    @MockBean
    private BookingVetRepository bookingVetRepository;

    @Autowired
    private BookingVetServiceImpl bookingVetServiceImpl;

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

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user1);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);

        BookingVetting bookingVetting = new BookingVetting();
        bookingVetting.setUser(user);
        bookingVetting.setBookingResult("Booking Result");
        bookingVetting.setBooking(bookings);
        bookingVetting.setBookingVettingKey(bookingVettingKey);
        bookingVetting.setComment("Comment");
        when(this.bookingVetRepository.save((BookingVetting) any())).thenReturn(bookingVetting);

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

        Company company3 = new Company();
        company3.setUsers(new ArrayList<User>());
        company3.setName("Name");
        company3.setSize(3L);
        company3.setRegulationLimit(new ArrayList<RegulationLimit>());
        company3.setCid(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setCompany(company3);
        user3.setBookingVetting(new ArrayList<BookingVetting>());
        user3.setNewsList(new ArrayList<News>());
        user3.setFname("Fname");
        user3.setLname("Lname");
        user3.setEnabled(true);
        user3.setBookings(new ArrayList<Bookings>());
        user3.setLocked(true);
        user3.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings1 = new Bookings();
        bookings1.setStatus("Status");
        bookings1.setUser(user3);
        bookings1.setBDate(LocalDate.ofEpochDay(1L));
        bookings1.setBookingVettings(new ArrayList<BookingVetting>());
        bookings1.setBid(1);

        BookingVettingKey bookingVettingKey1 = new BookingVettingKey();
        bookingVettingKey1.setEmail("jane.doe@example.org");
        bookingVettingKey1.setBid(1);

        BookingVetting bookingVetting1 = new BookingVetting();
        bookingVetting1.setUser(user2);
        bookingVetting1.setBookingResult("Booking Result");
        bookingVetting1.setBooking(bookings1);
        bookingVetting1.setBookingVettingKey(bookingVettingKey1);
        bookingVetting1.setComment("Comment");
        assertSame(bookingVetting, this.bookingVetServiceImpl.save(bookingVetting1));
        verify(this.bookingVetRepository).save((BookingVetting) any());
    }

    @Test
    void testGetAllBookingVetting() {
        ArrayList<BookingVetting> bookingVettingList = new ArrayList<BookingVetting>();
        when(this.bookingVetRepository.findAll()).thenReturn(bookingVettingList);
        List<BookingVetting> actualAllBookingVetting = this.bookingVetServiceImpl.getAllBookingVetting();
        assertSame(bookingVettingList, actualAllBookingVetting);
        assertTrue(actualAllBookingVetting.isEmpty());
        verify(this.bookingVetRepository).findAll();
    }

    @Test
    void testGetById() {
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

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user1);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);

        BookingVetting bookingVetting = new BookingVetting();
        bookingVetting.setUser(user);
        bookingVetting.setBookingResult("Booking Result");
        bookingVetting.setBooking(bookings);
        bookingVetting.setBookingVettingKey(bookingVettingKey);
        bookingVetting.setComment("Comment");
        when(this.bookingVetRepository.getById((BookingVettingKey) any())).thenReturn(bookingVetting);

        BookingVettingKey bookingVettingKey1 = new BookingVettingKey();
        bookingVettingKey1.setEmail("jane.doe@example.org");
        bookingVettingKey1.setBid(1);
        assertSame(bookingVetting, this.bookingVetServiceImpl.getById(bookingVettingKey1));
        verify(this.bookingVetRepository).getById((BookingVettingKey) any());
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

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user1);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);

        BookingVetting bookingVetting = new BookingVetting();
        bookingVetting.setUser(user);
        bookingVetting.setBookingResult("Booking Result");
        bookingVetting.setBooking(bookings);
        bookingVetting.setBookingVettingKey(bookingVettingKey);
        bookingVetting.setComment("Comment");
        Optional<BookingVetting> ofResult = Optional.<BookingVetting>of(bookingVetting);

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

        Company company3 = new Company();
        company3.setUsers(new ArrayList<User>());
        company3.setName("Name");
        company3.setSize(3L);
        company3.setRegulationLimit(new ArrayList<RegulationLimit>());
        company3.setCid(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setCompany(company3);
        user3.setBookingVetting(new ArrayList<BookingVetting>());
        user3.setNewsList(new ArrayList<News>());
        user3.setFname("Fname");
        user3.setLname("Lname");
        user3.setEnabled(true);
        user3.setBookings(new ArrayList<Bookings>());
        user3.setLocked(true);
        user3.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings1 = new Bookings();
        bookings1.setStatus("Status");
        bookings1.setUser(user3);
        bookings1.setBDate(LocalDate.ofEpochDay(1L));
        bookings1.setBookingVettings(new ArrayList<BookingVetting>());
        bookings1.setBid(1);

        BookingVettingKey bookingVettingKey1 = new BookingVettingKey();
        bookingVettingKey1.setEmail("jane.doe@example.org");
        bookingVettingKey1.setBid(1);

        BookingVetting bookingVetting1 = new BookingVetting();
        bookingVetting1.setUser(user2);
        bookingVetting1.setBookingResult("Booking Result");
        bookingVetting1.setBooking(bookings1);
        bookingVetting1.setBookingVettingKey(bookingVettingKey1);
        bookingVetting1.setComment("Comment");
        when(this.bookingVetRepository.save((BookingVetting) any())).thenReturn(bookingVetting1);
        when(this.bookingVetRepository.findById((BookingVettingKey) any())).thenReturn(ofResult);

        BookingVettingKey bookingVettingKey2 = new BookingVettingKey();
        bookingVettingKey2.setEmail("jane.doe@example.org");
        bookingVettingKey2.setBid(1);

        Company company4 = new Company();
        company4.setUsers(new ArrayList<User>());
        company4.setName("Name");
        company4.setSize(3L);
        company4.setRegulationLimit(new ArrayList<RegulationLimit>());
        company4.setCid(1);

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setCompany(company4);
        user4.setBookingVetting(new ArrayList<BookingVetting>());
        user4.setNewsList(new ArrayList<News>());
        user4.setFname("Fname");
        user4.setLname("Lname");
        user4.setEnabled(true);
        user4.setBookings(new ArrayList<Bookings>());
        user4.setLocked(true);
        user4.setUserRole(UserRole.EMPLOYEE);

        Company company5 = new Company();
        company5.setUsers(new ArrayList<User>());
        company5.setName("Name");
        company5.setSize(3L);
        company5.setRegulationLimit(new ArrayList<RegulationLimit>());
        company5.setCid(1);

        User user5 = new User();
        user5.setEmail("jane.doe@example.org");
        user5.setPassword("iloveyou");
        user5.setCompany(company5);
        user5.setBookingVetting(new ArrayList<BookingVetting>());
        user5.setNewsList(new ArrayList<News>());
        user5.setFname("Fname");
        user5.setLname("Lname");
        user5.setEnabled(true);
        user5.setBookings(new ArrayList<Bookings>());
        user5.setLocked(true);
        user5.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings2 = new Bookings();
        bookings2.setStatus("Status");
        bookings2.setUser(user5);
        bookings2.setBDate(LocalDate.ofEpochDay(1L));
        bookings2.setBookingVettings(new ArrayList<BookingVetting>());
        bookings2.setBid(1);

        BookingVettingKey bookingVettingKey3 = new BookingVettingKey();
        bookingVettingKey3.setEmail("jane.doe@example.org");
        bookingVettingKey3.setBid(1);

        BookingVetting bookingVetting2 = new BookingVetting();
        bookingVetting2.setUser(user4);
        bookingVetting2.setBookingResult("Booking Result");
        bookingVetting2.setBooking(bookings2);
        bookingVetting2.setBookingVettingKey(bookingVettingKey3);
        bookingVetting2.setComment("Comment");
        assertSame(bookingVetting1, this.bookingVetServiceImpl.updateBookings(bookingVettingKey2, bookingVetting2));
        verify(this.bookingVetRepository).findById((BookingVettingKey) any());
        verify(this.bookingVetRepository).save((BookingVetting) any());
    }

    @Test
    void testDelete() {
        doNothing().when(this.bookingVetRepository).delete((BookingVetting) any());

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

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user1);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);

        BookingVetting bookingVetting = new BookingVetting();
        bookingVetting.setUser(user);
        bookingVetting.setBookingResult("Booking Result");
        bookingVetting.setBooking(bookings);
        bookingVetting.setBookingVettingKey(bookingVettingKey);
        bookingVetting.setComment("Comment");
        this.bookingVetServiceImpl.delete(bookingVetting);
        verify(this.bookingVetRepository).delete((BookingVetting) any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(this.bookingVetRepository).deleteById((BookingVettingKey) any());

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);
        this.bookingVetServiceImpl.deleteById(bookingVettingKey);
        verify(this.bookingVetRepository).deleteById((BookingVettingKey) any());
        assertTrue(this.bookingVetServiceImpl.getAllBookingVetting().isEmpty());
    }

    @Test
    void testBookingVettingExists() {
        when(this.bookingVetRepository.existsById((BookingVettingKey) any())).thenReturn(true);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);
        assertTrue(this.bookingVetServiceImpl.bookingVettingExists(bookingVettingKey));
        verify(this.bookingVetRepository).existsById((BookingVettingKey) any());
        assertTrue(this.bookingVetServiceImpl.getAllBookingVetting().isEmpty());
    }

    @Test
    void testBookingVettingExists2() {
        when(this.bookingVetRepository.existsById((BookingVettingKey) any())).thenReturn(false);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);
        assertFalse(this.bookingVetServiceImpl.bookingVettingExists(bookingVettingKey));
        verify(this.bookingVetRepository).existsById((BookingVettingKey) any());
        assertTrue(this.bookingVetServiceImpl.getAllBookingVetting().isEmpty());
    }
}

