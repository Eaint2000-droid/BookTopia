package com.collab.g5.demo.bookings;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.exceptions.bookings.BookingExistsException;
import com.collab.g5.demo.news.News;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookingsController.class})
@ExtendWith(SpringExtension.class)
class BookingsControllerTest {
    @MockBean
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private BookingsController bookingsController;

    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BookingsController.bookingServiceImpl

        new BookingsController(new BookingServiceImpl());
    }

    @Test
    void testGetBookingsById() throws Exception {
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
        when(this.bookingServiceImpl.getBookingsById(anyInt())).thenReturn(bookings);
        when(this.bookingServiceImpl.bookingExists(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/bookings/emp/getAll/*");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("bid", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.bookingsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"bid\":1,\"user\":{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\",\"password\":\"iloveyou"
//                                        + "\",\"userRole\":\"EMPLOYEE\",\"locked\":true,\"enabled\":true,\"company\":{\"cid\":1,\"users\":[]},\"bookingVetting\""
//                                        + ":[],\"accountNonExpired\":true,\"accountNonLocked\":false,\"credentialsNonExpired\":true,\"username\":\"jane"
//                                        + ".doe@example.org\"},\"status\":\"Status\",\"bdate\":[1970,1,2]}"));
    }

    @Test
    void testAddBooking() {
        BookingServiceImpl bookingServiceImpl = mock(BookingServiceImpl.class);
        when(bookingServiceImpl.bookingExists(anyInt())).thenReturn(true);
        BookingsController bookingsController = new BookingsController(bookingServiceImpl);

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
        assertThrows(BookingExistsException.class, () -> bookingsController.addBooking(bookings));
        verify(bookingServiceImpl).bookingExists(anyInt());
    }

    @Test
    void testAddBooking2() {
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
        BookingServiceImpl bookingServiceImpl = mock(BookingServiceImpl.class);
        when(bookingServiceImpl.save((Bookings) any())).thenReturn(bookings);
        when(bookingServiceImpl.bookingExists(anyInt())).thenReturn(false);
        BookingsController bookingsController = new BookingsController(bookingServiceImpl);

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
        assertSame(bookings, bookingsController.addBooking(bookings1));
        verify(bookingServiceImpl).bookingExists(anyInt());
        verify(bookingServiceImpl).save((Bookings) any());
    }

    @Test
    void testUpdateBookings() throws Exception {
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
        when(this.bookingServiceImpl.updateBookings(anyInt(), (Bookings) any())).thenReturn(bookings);

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
        bookings1.setBDate(null);
        bookings1.setBookingVettings(new ArrayList<BookingVetting>());
        bookings1.setBid(1);
        String content = (new ObjectMapper()).writeValueAsString(bookings1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/bookings/hr/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookingsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"bid\":1,\"user\":{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\",\"password\":\"iloveyou"
//                                        + "\",\"userRole\":\"EMPLOYEE\",\"locked\":true,\"enabled\":true,\"company\":{\"cid\":1,\"users\":[]},\"bookingVetting\""
//                                        + ":[],\"accountNonExpired\":true,\"accountNonLocked\":false,\"credentialsNonExpired\":true,\"username\":\"jane"
//                                        + ".doe@example.org\"},\"status\":\"Status\",\"bdate\":[1970,1,2]}"));
    }

    @Test
    void testDeleteBooking() throws Exception {
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
        doNothing().when(this.bookingServiceImpl).delete((Bookings) any());
        when(this.bookingServiceImpl.getBookingsById(anyInt())).thenReturn(bookings);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/bookings/hr/del/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.bookingsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetBookings() throws Exception {
        when(this.bookingServiceImpl.getAllBookings()).thenReturn(new ArrayList<Bookings>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookings/hr/getAll");
        MockMvcBuilders.standaloneSetup(this.bookingsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetBookings2() throws Exception {
        when(this.bookingServiceImpl.getAllBookings()).thenReturn(new ArrayList<Bookings>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/bookings/hr/getAll");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.bookingsController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

