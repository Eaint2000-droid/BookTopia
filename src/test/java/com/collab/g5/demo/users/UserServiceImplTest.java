package com.collab.g5.demo.users;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.collab.g5.demo.bookings.BookingVetting;
import com.collab.g5.demo.bookings.Bookings;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.news.News;
import com.collab.g5.demo.regulations.RegulationLimit;
import com.collab.g5.demo.security.JwtAuthenticationEntryPoint;
import com.collab.g5.demo.security.JwtRequestFilter;
import com.collab.g5.demo.security.JwtUserDetailsService;
import com.collab.g5.demo.security.WebSecurityConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class UserServiceImplTest {
    @Test
    void testAddNewUser() {
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
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save((User) any())).thenReturn(user);
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository,
                new WebSecurityConfig(jwtAuthenticationEntryPoint, jwtUserDetailsService, new JwtRequestFilter()));

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
        userServiceImpl.addNewUser(user1);
        verify(userRepository).save((User) any());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testGetAllUsers() {
        UserRepository userRepository = mock(UserRepository.class);
        ArrayList<User> userList = new ArrayList<User>();
        when(userRepository.findAll()).thenReturn(userList);
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        List<User> actualAllUsers = (new UserServiceImpl(userRepository,
                new WebSecurityConfig(jwtAuthenticationEntryPoint, jwtUserDetailsService, new JwtRequestFilter())))
                .getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    void testGetUserByEmail() {
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
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.<User>of(user));
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository,
                new WebSecurityConfig(jwtAuthenticationEntryPoint, jwtUserDetailsService, new JwtRequestFilter()));
        assertSame(user, userServiceImpl.getUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testGetUserByEmail2() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.<User>empty());
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository,
                new WebSecurityConfig(jwtAuthenticationEntryPoint, jwtUserDetailsService, new JwtRequestFilter()));
        assertNull(userServiceImpl.getUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
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
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save((User) any())).thenReturn(user);
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository,
                new WebSecurityConfig(jwtAuthenticationEntryPoint, jwtUserDetailsService, new JwtRequestFilter()));

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
        assertSame(user, userServiceImpl.save(user1));
        verify(userRepository).save((User) any());
    }

    @Test
    void testDelete() {
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).delete((User) any());
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository,
                new WebSecurityConfig(jwtAuthenticationEntryPoint, jwtUserDetailsService, new JwtRequestFilter()));

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
        userServiceImpl.delete(user);
        verify(userRepository).delete((User) any());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDeleteById() {
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((String) any());
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository,
                new WebSecurityConfig(jwtAuthenticationEntryPoint, jwtUserDetailsService, new JwtRequestFilter()));
        userServiceImpl.deleteById("jane.doe@example.org");
        verify(userRepository).deleteById((String) any());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }
}

