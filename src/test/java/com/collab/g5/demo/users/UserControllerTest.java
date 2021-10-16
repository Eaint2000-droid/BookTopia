package com.collab.g5.demo.users;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.security.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    void testConstructor() {
        UserRepository userRepository = mock(UserRepository.class);
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, new PasswordEncoder());

        new UserController(userServiceImpl);
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testGetUserByEmail() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/get/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\",\"password\":\"iloveyou\",\"userRole\":"
                                        + "\"EMPLOYEE\",\"locked\":true,\"enabled\":true,\"company\":{\"cid\":1,\"users\":[]},\"bookingVetting\":[],"
                                        + "\"accountNonLocked\":false,\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"username\":\"jane.doe"
                                        + "@example.org\"}"));
    }

    @Test
    void testDeleteUser() throws Exception {
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
        doNothing().when(this.userServiceImpl).delete((User) any());
        when(this.userServiceImpl.getUserByEmail((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/user/del/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetUsers() throws Exception {
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

        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);
        when(this.userServiceImpl.getAllUsers()).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/hr/getAll/");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"email\":\"jane.doe@example.org\",\"fname\":\"?\",\"lname\":\"?\",\"password\":\"iloveyou\",\"userRole\":\"EMPLOYEE\""
                                        + ",\"locked\":true,\"enabled\":true,\"company\":{\"cid\":1,\"users\":[]},\"bookingVetting\":[],\"accountNonLocked\""
                                        + ":false,\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"username\":\"jane.doe@example.org\"}]"));
    }

    @Test
    void testNewUser() throws Exception {
        doNothing().when(this.userServiceImpl).addNewUser((User) any());
        when(this.userServiceImpl.getUserByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));

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
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/hr/create/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

