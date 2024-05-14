package vla.kyr.restfullapi.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import vla.kyr.restfullapi.dto.UserRequest;
import vla.kyr.restfullapi.dto.UserResponse;
import vla.kyr.restfullapi.model.User;
import vla.kyr.restfullapi.repository.UserRepository;
import vla.kyr.restfullapi.utils.UserUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserUtils userUtils;

    @InjectMocks
    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createUser_ValidUserRequest_Success() {
        // given
        UserRequest userRequest = new UserRequest(
                "Michael",
                "Johnson",
                "michael.johnson@example.com",
                LocalDate.of(1988, 2, 10),
                "321 Cedar St",
                "789456123"
        );

        // when
        when(userRepository.save(any(User.class))).thenReturn(new User());

        UserResponse result = underTest.createUser(userRequest);

        // then
        assertNotNull(result);
    }

    @Test
    void updateUser_ValidUserIdAndUserRequest_Success() {
        // given
        Long userId = 1L;
        UserRequest updatedUser = new UserRequest(
                "Michael",
                "Johnson",
                "michael.johnson@example.com",
                LocalDate.of(1988, 2, 10),
                "321 Cedar St",
                "789456123"
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // when
        UserResponse result = underTest.updateUser(userId, updatedUser);

        // then
        assertNotNull(result);
    }

    @Test
    void getUserById_ValidUserId_Success() {
        //given
        Long userId = 1L;
        User user = new User("Michael", "Johnson", "michael.johnson@example.com",
                LocalDate.of(1988, 2, 10), "321 Cedar St", "789456123");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        //when
        UserResponse result = underTest.getUserById(userId);

        //then
        assertNotNull(result);
    }

    @Test
    void getUserByBirthDateRange_ValidDates_Success() {
        //given
        LocalDate fromDate = LocalDate.now().minusYears(1);
        LocalDate toDate = LocalDate.now();
        List<User> users = List.of(
        new User("Michael", "Johnson", "michael.johnson@example.com",
                LocalDate.of(1988, 2, 10), "321 Cedar St", "789456123"),
        new User("Emma", "Williams", "emma.williams@example.com",
                LocalDate.of(1998, 7, 25), "654 Maple St", "654123789"),
        new User("Sophia", "Brown", "sophia.brown@example.com",
                LocalDate.of(1989, 11, 15), "987 Birch St", "321789456"));
        when(userRepository.findByBirthDateBetween(fromDate, toDate)).thenReturn(users);

        //when
        List<UserResponse> result = underTest.getUserByBirthDateRange(fromDate, toDate);

        //then
        assertNotNull(result);
    }

    @Test
    void deleteUser_ValidUserId_Success() {
        //given
        Long id = 1L;
        User user = new User(id,"Michael", "Johnson", "michael.johnson@example.com",
                LocalDate.of(1988, 2, 10), "321 Cedar St", "789456123");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //when
        underTest.deleteUser(id);

        //then
        verify(userRepository).deleteById(id);
    }

    @Test
    void getAllUsersPagination_ValidOffsetAndPageSize_Success() {
        //given
        int offset = 0;
        int pageSize = 10;
        List<User> users = List.of(
                new User("Michael", "Johnson", "michael.johnson@example.com",
                        LocalDate.of(1988, 1, 10), "321 Cedar St",
                        "789456123"),
                new User("Emma", "Williams", "emma.williams@example.com",
                        LocalDate.of(1998, 2, 25), "654 Maple St",
                        "654123789"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 4, 15), "987 Birch St",
                        "321789456"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 5, 15), "987 Birch St",
                        "321789456"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 6, 15), "987 Birch St",
                        "321789456"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 7, 15), "987 Birch St",
                        "321789456"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 8, 15), "987 Birch St",
                        "321789456"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 9, 15), "987 Birch St",
                        "321789456"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 10, 15), "987 Birch St",
                        "321789456")
        );
        Page<User> userPage = new PageImpl<>(users);
        when(userRepository.findAll(PageRequest.of(offset, pageSize))).thenReturn(userPage);

        //when
        Page<UserResponse> result = underTest.getAllUsersPagination(offset, pageSize);

        //then
        assertNotNull(result);
        assertEquals(users.size(), result.getContent().size());
    }

    @Test
    void getAllUsers_ReturnListOfUsers_Success() {
        //given
        List<User> users = List.of(
                new User("Michael", "Johnson", "michael.johnson@example.com",
                        LocalDate.of(1988, 2, 10), "321 Cedar St", "789456123"),
                new User("Emma", "Williams", "emma.williams@example.com",
                        LocalDate.of(1998, 7, 25), "654 Maple St", "654123789"),
                new User("Sophia", "Brown", "sophia.brown@example.com",
                        LocalDate.of(1989, 11, 15), "987 Birch St", "321789456"));
        when(userRepository.findAll()).thenReturn(users);

        //when
        List<UserResponse> result = underTest.getAllUsers();

        //then
        assertNotNull(result);
    }
}