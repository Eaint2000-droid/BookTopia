package com.collab.g5.demo.bookings;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.security.PasswordEncoder;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRepository;
import com.collab.g5.demo.users.UserRole;
import com.collab.g5.demo.users.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

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

@ContextConfiguration(classes = {BookingVetController.class})
@ExtendWith(SpringExtension.class)
class BookingVetControllerTest {
    @MockBean
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private BookingVetController bookingVetController;

    @MockBean
    private BookingVetServiceImpl bookingVetServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    void testAddBookingVetting() throws Exception {
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
        when(this.userServiceImpl.getUserByEmail((String) any())).thenReturn(user);

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

        Bookings bookings = new Bookings();
        bookings.setStatus("Status");
        bookings.setUser(user2);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);

        BookingVetting bookingVetting = new BookingVetting();
        bookingVetting.setUser(user1);
        bookingVetting.setBookingResult("Booking Result");
        bookingVetting.setBooking(bookings);
        bookingVetting.setBookingVettingKey(bookingVettingKey);
        bookingVetting.setComment("Comment");
        when(this.bookingVetServiceImpl.save((BookingVetting) any())).thenReturn(bookingVetting);
        when(this.bookingVetServiceImpl.getAllBookingVetting()).thenReturn(new ArrayList<BookingVetting>());

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
        when(this.bookingServiceImpl.getBookingsById(anyInt())).thenReturn(bookings1);

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

        BookingVettingKey bookingVettingKey1 = new BookingVettingKey();
        bookingVettingKey1.setEmail("jane.doe@example.org");
        bookingVettingKey1.setBid(1);

        BookingVetting bookingVetting1 = new BookingVetting();
        bookingVetting1.setUser(user4);
        bookingVetting1.setBookingResult("Booking Result");
        bookingVetting1.setBooking(bookings2);
        bookingVetting1.setBookingVettingKey(bookingVettingKey1);
        bookingVetting1.setComment("Comment");
        String content = (new ObjectMapper()).writeValueAsString(bookingVetting1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/bVetting/hr/add/*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookingVetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"bookingVettingKey\":{\"bid\":1,\"email\":\"jane.doe@example.org\"},\"bookingResult\":\"Booking Result\",\"comment"
                                        + "\":\"Comment\"}"));
    }

    @Test
    void testConstructor() {
        BookingVetServiceImpl bookingVetServiceImpl = new BookingVetServiceImpl();
        BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, new PasswordEncoder());

        new BookingVetController(bookingVetServiceImpl, bookingServiceImpl, userServiceImpl);

        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDelBookingVetting() throws Exception {
        doNothing().when(this.bookingVetServiceImpl).delete((BookingVetting) any());

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
        String content = (new ObjectMapper()).writeValueAsString(bookingVetting);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/bVetting/hr/del/*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookingVetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetBookingVetting() throws Exception {
        when(this.bookingVetServiceImpl.getAllBookingVetting()).thenReturn(new ArrayList<BookingVetting>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bVetting/hr");
        MockMvcBuilders.standaloneSetup(this.bookingVetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetBookingVetting2() throws Exception {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("?");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("?");
        user.setLname("?");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("?");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setCompany(company1);
        user1.setBookingVetting(new ArrayList<BookingVetting>());
        user1.setNewsList(new ArrayList<News>());
        user1.setFname("?");
        user1.setLname("?");
        user1.setEnabled(true);
        user1.setBookings(new ArrayList<Bookings>());
        user1.setLocked(true);
        user1.setUserRole(UserRole.EMPLOYEE);

        Bookings bookings = new Bookings();
        bookings.setStatus("?");
        bookings.setUser(user1);
        bookings.setBDate(LocalDate.ofEpochDay(1L));
        bookings.setBookingVettings(new ArrayList<BookingVetting>());
        bookings.setBid(1);

        BookingVettingKey bookingVettingKey = new BookingVettingKey();
        bookingVettingKey.setEmail("jane.doe@example.org");
        bookingVettingKey.setBid(1);

        BookingVetting bookingVetting = new BookingVetting();
        bookingVetting.setUser(user);
        bookingVetting.setBookingResult("?");
        bookingVetting.setBooking(bookings);
        bookingVetting.setBookingVettingKey(bookingVettingKey);
        bookingVetting.setComment("?");

        ArrayList<BookingVetting> bookingVettingList = new ArrayList<BookingVetting>();
        bookingVettingList.add(bookingVetting);
        when(this.bookingVetServiceImpl.getAllBookingVetting()).thenReturn(bookingVettingList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bVetting/hr");
        MockMvcBuilders.standaloneSetup(this.bookingVetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"bookingVettingKey\":{\"bid\":1,\"email\":\"jane.doe@example.org\"},\"bookingResult\":\"?\",\"comment\":\"?\"}]"));
    }

    @Test
    void testGetBookingVettingById() throws Exception {
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
        when(this.bookingVetServiceImpl.getById((BookingVettingKey) any())).thenReturn(bookingVetting);

        BookingVettingKey bookingVettingKey1 = new BookingVettingKey();
        bookingVettingKey1.setEmail("jane.doe@example.org");
        bookingVettingKey1.setBid(1);
        String content = (new ObjectMapper()).writeValueAsString(bookingVettingKey1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bVetting/hr/get/*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookingVetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"bookingVettingKey\":{\"bid\":1,\"email\":\"jane.doe@example.org\"},\"bookingResult\":\"Booking Result\",\"comment"
                                        + "\":\"Comment\"}"));
    }

    @Test
    void testUpdateBookingVetting() throws Exception {
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
        when(this.bookingVetServiceImpl.updateBookings((BookingVettingKey) any(), (BookingVetting) any()))
                .thenReturn(bookingVetting);

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
        String content = (new ObjectMapper()).writeValueAsString(bookingVetting1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/bVetting/hr/updateBooking/{bid}/{email}", 1, "jane.doe@example.org")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookingVetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"bookingVettingKey\":{\"bid\":1,\"email\":\"jane.doe@example.org\"},\"bookingResult\":\"Booking Result\",\"comment"
                                        + "\":\"Comment\"}"));
    }
}

