package vla.kyr.restfullapi.service;

import org.springframework.data.domain.Page;
import vla.kyr.restfullapi.dto.UserRequest;
import vla.kyr.restfullapi.dto.UserResponse;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long userId, UserRequest userRequest);

    UserResponse getUserById(Long id);

    List<UserResponse> getUserByBirthDateRange(LocalDate fromDate, LocalDate toDate);

    void deleteUser(Long id);

    Page<UserResponse> getAllUsersPagination(int offset, int pageSize);

    List<UserResponse> getAllUsers();
}
