package vla.kyr.restfullapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vla.kyr.restfullapi.dto.UserRequest;
import vla.kyr.restfullapi.dto.UserResponse;
import vla.kyr.restfullapi.exception.ResourceNotFoundException;
import vla.kyr.restfullapi.mapper.UserMapper;
import vla.kyr.restfullapi.model.User;
import vla.kyr.restfullapi.repository.UserRepository;
import vla.kyr.restfullapi.service.UserService;
import vla.kyr.restfullapi.utils.UserUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserUtils userUtils;


    @Override
    public UserResponse createUser(UserRequest userRequest) {

        LocalDate userBirthDate = userRequest.getBirthDate();

        // Validate age using your UserUtils method
        userUtils.isValidAge(userBirthDate);

        User user = UserMapper.UserRequestMapToUser(userRequest);

        User userSaved = userRepository.save(user);

        return UserMapper.UserMapToUserResponse(userSaved);

    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exist with given id : " + userId)
        );

        // Check if each field is present in the updatedUser object and update only if it's not null
        if (updatedUser.getFirstName() != null) {
            user.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            user.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getBirthDate() != null) {
            user.setBirthDate(updatedUser.getBirthDate());
        }
        if (updatedUser.getAddress() != null) {
            user.setAddress(updatedUser.getAddress());
        }
        if (updatedUser.getPhoneNumber() != null) {
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        User updatedUserObj = userRepository.save(user);

        return UserMapper.UserMapToUserResponse(updatedUserObj);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Users is not exist with given id : " + userId));

        return UserMapper.UserMapToUserResponse(user);
    }

    @Override
    public List<UserResponse> getUserByBirthDateRange(LocalDate fromDate, LocalDate toDate) {
        List<User> users = userRepository.findByBirthDateBetween(fromDate, toDate);
        userUtils.isEmptyList(users,fromDate, toDate);
        return users.stream().map(UserMapper::UserMapToUserResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User is not exist with given id : " + id)
        );

        userRepository.deleteById(user.getId());
    }


    @Override
    public Page<UserResponse> getAllUsersPagination(int offset, int pageSize) {
        Page<User> users = userRepository.findAll(PageRequest.of(offset, pageSize));
        return users.map(UserMapper::UserMapToUserResponse);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::UserMapToUserResponse).collect(Collectors.toList());
    }
}
