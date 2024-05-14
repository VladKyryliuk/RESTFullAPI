package vla.kyr.restfullapi.mapper;

import vla.kyr.restfullapi.dto.UserRequest;
import vla.kyr.restfullapi.dto.UserResponse;
import vla.kyr.restfullapi.model.User;

public class UserMapper {


    public static User UserRequestMapToUser(UserRequest userRequest) {
        return new User(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getBirthDate(),
                userRequest.getAddress(),
                userRequest.getPhoneNumber()
        );
    }

    public static UserRequest UserMapToUserRequest(User user) {
        return new UserRequest(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getAddress(),
                user.getPhoneNumber()
        );
    }

    public static User UserResponseMapToUser(UserResponse userResponse) {
        return new User(
                userResponse.getId(),
                userResponse.getFirstName(),
                userResponse.getLastName(),
                userResponse.getEmail(),
                userResponse.getBirthDate(),
                userResponse.getAddress(),
                userResponse.getPhoneNumber()
        );
    }

    public static UserResponse UserMapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getAddress(),
                user.getPhoneNumber()
        );
    }

}
