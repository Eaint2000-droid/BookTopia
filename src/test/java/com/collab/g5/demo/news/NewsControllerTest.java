package com.collab.g5.demo.news;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.users.User;
import com.collab.g5.demo.users.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {NewsController.class})
@ExtendWith(SpringExtension.class)
public class NewsControllerTest {
    @Autowired
    private NewsController newsController;

    @MockBean
    private NewsService newsService;

    @Test
    public void testDeleteNews() throws Exception {
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
        doNothing().when(this.newsService).delete((News) any());
        when(this.newsService.getNewsById(anyInt())).thenReturn(news);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/news/hr/del/{nid}", 1);
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetNewsById() throws Exception {
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
        when(this.newsService.getNewsById(anyInt())).thenReturn(news);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/news/emp/get/{nid}", 1);
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"nid\":1,\"date\":[1970,1,2],\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"company\":{\"cid\":1,"
                                        + "\"name\":\"Name\",\"size\":3,\"regulationLimit\":[]},\"user\":{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\","
                                        + "\"lname\":\"Lname\",\"password\":\"iloveyou\",\"userRole\":\"EMPLOYEE\",\"locked\":true,\"enabled\":true,\"company\":{"
                                        + "\"cid\":1,\"regulationLimit\":[]},\"bookingVetting\":[],\"bookings\":[],\"newsList\":[],\"username\":\"jane.doe"
                                        + "@example.org\",\"accountNonLocked\":false,\"authorities\":[{\"authority\":\"EMPLOYEE\"}],\"credentialsNonExpired"
                                        + "\":true,\"accountNonExpired\":true}}"));
    }

    @Test
    public void testGetNews() throws Exception {
        when(this.newsService.getAllNews()).thenReturn(new ArrayList<News>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/news/hr/getAll");
        MockMvcBuilders.standaloneSetup(this.newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetNews2() throws Exception {
        when(this.newsService.getAllNews()).thenReturn(new ArrayList<News>());
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

