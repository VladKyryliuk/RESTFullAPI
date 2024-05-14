package vla.kyr.restfullapi.utils;

import org.junit.jupiter.api.Test;
import vla.kyr.restfullapi.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserUtilsTest {

    @Test
    void itShouldNotThrowAgeExceptionWhenAgeIsEqualToLimit() {
        UserUtils userUtils = new UserUtils();
        LocalDate birthDate = LocalDate.of(1980, 5, 14);
        assertDoesNotThrow(() -> userUtils.isValidAge(birthDate));
    }

    @Test
    void itShouldNotThrowAgeExceptionWhenAgeIsGreaterThanLimit() {
        UserUtils userUtils = new UserUtils();
        LocalDate birthDate = LocalDate.of(1970, 5, 14);
        assertDoesNotThrow(() -> userUtils.isValidAge(birthDate));
    }

    @Test
    void itShouldThrowResourceNotFoundExceptionWhenListIsEmpty() {
        UserUtils userUtils = new UserUtils();
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2021, 1, 1);
        List<Object> emptyList = Collections.emptyList();
        assertThrows(ResourceNotFoundException.class, () -> userUtils.isEmptyList(emptyList, from, to));
    }

    @Test
    void itShouldNotThrowResourceNotFoundExceptionWhenListIsNotEmpty() {
        UserUtils userUtils = new UserUtils();
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2021, 1, 1);
        List<Object> nonEmptyList = new ArrayList<>();
        nonEmptyList.add(new Object()); // Adding some dummy object
        assertDoesNotThrow(() -> userUtils.isEmptyList(nonEmptyList, from, to));
    }
}