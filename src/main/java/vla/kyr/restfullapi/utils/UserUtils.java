package vla.kyr.restfullapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vla.kyr.restfullapi.exception.AgeException;
import vla.kyr.restfullapi.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class UserUtils {

    @Value("${limit.age}")
    private int LimitAge;

    public void isValidAge(LocalDate birthDate){
        System.out.println(LimitAge);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        if(age < LimitAge) {
            throw new AgeException("Age should be more than (or equals) : " + LimitAge);
        }
    }

    public void isEmptyList(List<?> list, LocalDate from, LocalDate to){
        if(list.isEmpty()){
            throw new ResourceNotFoundException("Users with this range not found from:" + from + " to:" + to);
        }
    }
}
