package com.collab.g5.demo.news;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRole;
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

@ContextConfiguration(classes = {NewsController.class})
@ExtendWith(SpringExtension.class)
class NewsControllerTest {
    @Autowired
    private NewsController newsController;

    @MockBean
    private NewsServiceImpl newsServiceImpl;

    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     NewsController.newsServiceImpl

        new NewsController(new NewsServiceImpl());
    }

    @Test
    void testGetNewsById() throws Exception {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company1);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        News news = new News();
        news.setDate(LocalDate.ofEpochDay(1L));
        news.setCompany(company);
        news.setUser(user);
        news.setNid(1);
        news.setTitle("Dr");
        news.setContent("Not all who wander are lost");
        when(this.newsServiceImpl.getNewsById(anyInt())).thenReturn(news);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/news/emp/get/{nid}", 1);
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"nid\":1,\"date\":[1970,1,2],\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"company\":{\"cid\":1,"
                                        + "\"name\":\"Name\",\"size\":3,\"users\":[]}}"));
    }

    @Test
    void testAddBook() {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company1);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        News news = new News();
        news.setDate(LocalDate.ofEpochDay(1L));
        news.setCompany(company);
        news.setUser(user);
        news.setNid(1);
        news.setTitle("Dr");
        news.setContent("Not all who wander are lost");
        NewsServiceImpl newsServiceImpl = mock(NewsServiceImpl.class);
        when(newsServiceImpl.addNews((News) any())).thenReturn(news);
        NewsController newsController = new NewsController(newsServiceImpl);

        Company company2 = new Company();
        company2.setUsers(new ArrayList<User>());
        company2.setName("Name");
        company2.setSize(3L);
        company2.setRegulationLimit(new ArrayList<RegulationLimit>());
        company2.setCid(1);

        Company company3 = new Company();
        company3.setUsers(new ArrayList<User>());
        company3.setName("Name");
        company3.setSize(3L);
        company3.setRegulationLimit(new ArrayList<RegulationLimit>());
        company3.setCid(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setCompany(company3);
        user1.setBookingVetting(new ArrayList<BookingVetting>());
        user1.setNewsList(new ArrayList<News>());
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setEnabled(true);
        user1.setBookings(new ArrayList<Bookings>());
        user1.setLocked(true);
        user1.setUserRole(UserRole.EMPLOYEE);

        News news1 = new News();
        news1.setDate(LocalDate.ofEpochDay(1L));
        news1.setCompany(company2);
        news1.setUser(user1);
        news1.setNid(1);
        news1.setTitle("Dr");
        news1.setContent("Not all who wander are lost");
        assertSame(news, newsController.addBook(news1));
        verify(newsServiceImpl, atLeast(1)).addNews((News) any());
    }

    @Test
    void testUpdateNews() throws Exception {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company1);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        News news = new News();
        news.setDate(LocalDate.ofEpochDay(1L));
        news.setCompany(company);
        news.setUser(user);
        news.setNid(1);
        news.setTitle("Dr");
        news.setContent("Not all who wander are lost");
        when(this.newsServiceImpl.updateNews(anyInt(), (News) any())).thenReturn(news);

        Company company2 = new Company();
        company2.setUsers(new ArrayList<User>());
        company2.setName("Name");
        company2.setSize(3L);
        company2.setRegulationLimit(new ArrayList<RegulationLimit>());
        company2.setCid(1);

        Company company3 = new Company();
        company3.setUsers(new ArrayList<User>());
        company3.setName("Name");
        company3.setSize(3L);
        company3.setRegulationLimit(new ArrayList<RegulationLimit>());
        company3.setCid(1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setCompany(company3);
        user1.setBookingVetting(new ArrayList<BookingVetting>());
        user1.setNewsList(new ArrayList<News>());
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setEnabled(true);
        user1.setBookings(new ArrayList<Bookings>());
        user1.setLocked(true);
        user1.setUserRole(UserRole.EMPLOYEE);

        News news1 = new News();
        news1.setDate(null);
        news1.setCompany(company2);
        news1.setUser(user1);
        news1.setNid(1);
        news1.setTitle("Dr");
        news1.setContent("Not all who wander are lost");
        String content = (new ObjectMapper()).writeValueAsString(news1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/news/hr/update/news/{nid}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"nid\":1,\"date\":[1970,1,2],\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"company\":{\"cid\":1,"
                                        + "\"name\":\"Name\",\"size\":3,\"users\":[]}}"));
    }

    @Test
    void testDeleteNews() throws Exception {
        Company company = new Company();
        company.setUsers(new ArrayList<User>());
        company.setName("Name");
        company.setSize(3L);
        company.setRegulationLimit(new ArrayList<RegulationLimit>());
        company.setCid(1);

        Company company1 = new Company();
        company1.setUsers(new ArrayList<User>());
        company1.setName("Name");
        company1.setSize(3L);
        company1.setRegulationLimit(new ArrayList<RegulationLimit>());
        company1.setCid(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCompany(company1);
        user.setBookingVetting(new ArrayList<BookingVetting>());
        user.setNewsList(new ArrayList<News>());
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEnabled(true);
        user.setBookings(new ArrayList<Bookings>());
        user.setLocked(true);
        user.setUserRole(UserRole.EMPLOYEE);

        News news = new News();
        news.setDate(LocalDate.ofEpochDay(1L));
        news.setCompany(company);
        news.setUser(user);
        news.setNid(1);
        news.setTitle("Dr");
        news.setContent("Not all who wander are lost");
        doNothing().when(this.newsServiceImpl).delete((News) any());
        when(this.newsServiceImpl.getNewsById(anyInt())).thenReturn(news);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/news/hr/del/{nid}", 1);
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetNews() throws Exception {
        when(this.newsServiceImpl.getAllNews()).thenReturn(new ArrayList<News>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/news/hr/getAll");
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetNews2() throws Exception {
        when(this.newsServiceImpl.getAllNews()).thenReturn(new ArrayList<News>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/news/hr/getAll");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

