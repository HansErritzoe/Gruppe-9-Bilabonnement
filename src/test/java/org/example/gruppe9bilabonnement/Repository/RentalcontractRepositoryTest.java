package org.example.gruppe9bilabonnement.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RentalcontractRepositoryTest {

    @Autowired
    RentalcontractRepository rentalcontractRepository;

    @Test
    void testGetExpectedRevenue() {
        int expectedRevenue = rentalcontractRepository.getExpectedRevenue();
        assertEquals(16696, expectedRevenue);
    }

    @Test
    void getHandleTotal() {
        int expectedTotal = rentalcontractRepository.getHandleTotal();
        assertEquals(2, expectedTotal);
    }
}