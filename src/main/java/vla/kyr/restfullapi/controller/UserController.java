package vla.kyr.restfullapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vla.kyr.restfullapi.dto.UserRequest;
import vla.kyr.restfullapi.dto.UserResponse;
import vla.kyr.restfullapi.service.UserService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "REST FULL API")
public class UserController {

    private final UserService userService;

    // Link on swagger: http://localhost:8080/swagger-ui/index.html

    // Build add User REST API
    @PostMapping
    @Operation(summary = "Create new user", description = "Create new object and save in DB")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse savedUser = userService.createUser(userRequest);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build Get User By ID REST API
    @GetMapping("{id}")
    @Operation(summary = "Get user by id", description = "Returns object with specified id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    //Build Get All With Pagination Users REST API
    @GetMapping("/{offset}/{pageSize}")
    @Operation(summary = "Get all users with help pagination", description = "Return objects with a specific range")
    public ResponseEntity<Page<UserResponse>> getAllUsersPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<UserResponse> users = userService.getAllUsersPagination(offset,pageSize);
        return ResponseEntity.ok(users);
    }

    //Build Update User REST API
    @PutMapping("{id}")
    @Operation(summary = "Update user by id", description = "Update all or some fields and return update object")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        UserResponse userUpdatedResponse = userService.updateUser(id,userRequest);
        return ResponseEntity.ok(userUpdatedResponse);
    }

    //Build Delete User REST API
    @DeleteMapping("{id}")
    @Operation(summary = "Delete user by id", description = "Delete object from DB")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }

    //Build Get From Date Birth to Date Birth REST API
    @GetMapping
    @Operation(summary = "Get all users in range date birth",
            description = "Return all objects with the specified date range from .... to ....")
    public ResponseEntity<List<UserResponse>> getAllUsersByAge(
            @RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate") LocalDate toDate ) {
        List<UserResponse> users = userService.getUserByBirthDateRange(fromDate, toDate);
        return ResponseEntity.ok(users);
    }

    //Build Get All Users REST API
    @GetMapping("/")
    @Operation(summary = "Get all users",
            description = "Return all objects")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
