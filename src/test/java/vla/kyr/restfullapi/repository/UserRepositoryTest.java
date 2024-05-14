package vla.kyr.restfullapi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vla.kyr.restfullapi.exception.ResourceNotFoundException;
import vla.kyr.restfullapi.model.User;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;

    @BeforeEach
    void setUp() {
        List<User> users = List.of(
                new User(1L,"Vlad","Kyryliuk","vlad.kyryliukr@gmail.com",
                        LocalDate.of(2003,6,5),"Test",
                        "380669458568"),
                new User(2L,"Dima","Solokino","dima.solokino@gmail.com",
                        LocalDate.of(2002,7,23),"Test",
                        "380989455835"),
                new User(3L,"Roma","Pavliuk","roma.pavliuk@gmail.com",
                        LocalDate.of(2000,10,5),"Test",
                        "380989458945")
        );
        underTest.saveAll(users);
    }

    @AfterEach
    void tearDown() {
        List<User> users = underTest.findAll()
                .stream().filter(el -> {
                    assert el.getAddress() != null;
                    return el.getAddress().contains("Test");
                })
                .toList();
        underTest.deleteAll(users);
    }


    @Test
    void itShouldCheckThatCollectionIsNotEmpty() {
        assertFalse(underTest.findAll().isEmpty());
        List<User> smartphones = underTest.findAll()
                .stream().filter(el -> {
                    assert el.getAddress() != null;
                    return el.getAddress().contains("Test");
                })
                .toList();
        assertEquals(smartphones.size(), 3);
    }

    @Test
    void itShouldSaveItem() {
        //given
        User testUser = new User("Bodya","Dobyik","bodya.dobyik@gmail.com",
                LocalDate.of(2001,12,17),"Test",
                "380989458945");

        // when
        underTest.save(testUser);

        // then
        User forTest = underTest.findById(testUser.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User is not exist with given id : " + testUser.getId())
        );

        assertNotNull(forTest);
        assertNotNull(forTest.getId());
        assertFalse(forTest.getId().describeConstable().isEmpty());
    }

    @Test
    void itShouldDeleteItem() {
        // given
        User testUser = new User(1L,"Bodya","Dobyik","bodya.dobyik@gmail.com",
                LocalDate.of(2001,12,17),"Test",
                "380989458945");
        underTest.save(testUser);

        // when
        underTest.deleteById(1L);

        // then
        assertTrue(underTest.findById(1L).isEmpty());
    }

    @Test
    void itShouldUpdateItem() {
        // given
        User testUser = new User(1L,"Vlad","Kyryliuk","vlad.kyryliukr@gmail.com",
                LocalDate.of(2003,6,5),"Test",
                "380669458568");
        underTest.save(testUser);

        // when
        User updateUser = new User(1L,"Dima","Kyryliuk","dima.kyryliukr@gmail.com",
                LocalDate.of(2003,6,5),"Test",
                "380669458568");
        underTest.save(updateUser);

        // then
        User updatedUser = underTest.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        assertEquals("Dima", updatedUser.getFirstName());
        assertTrue(updatedUser.getEmail().contains("dima"));
    }

    @Test
    public void testFindByBirthDateBetween() {
        User user1 = new User("Michael", "Johnson", "michael.johnson@example.com", LocalDate.of(1988, 2, 10), "321 Cedar St", "789456123");
        User user2 = new User("Emma", "Williams", "emma.williams@example.com", LocalDate.of(1998, 7, 25), "654 Maple St", "654123789");
        User user3 = new User("Sophia", "Brown", "sophia.brown@example.com", LocalDate.of(1989, 11, 15), "987 Birch St", "321789456");

        // Save users to the repository
        underTest.saveAll(List.of(user1, user2, user3));

        LocalDate fromDate = LocalDate.of(1980, 1, 1);
        LocalDate toDate = LocalDate.of(1990, 12, 31);

        List<User> users = underTest.findByBirthDateBetween(fromDate, toDate);

        assertEquals(2, users.size());
    }

}