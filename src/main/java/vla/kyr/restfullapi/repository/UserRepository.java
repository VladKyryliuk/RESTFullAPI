package vla.kyr.restfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vla.kyr.restfullapi.model.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);

}
