package com.junit;

import com.indyDAO.ConcreteIndyWinnerDAO;
import com.indyDTO.IndyWinnerDTO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAOJunit {

    private ConcreteIndyWinnerDAO dao;

    @BeforeEach
    void setUp() {
        dao = new ConcreteIndyWinnerDAO();
    }

    @Test
    void testGetAllWinners_withValidData() {
        // Fetch the first 10 rows
        List<IndyWinnerDTO> winners = dao.getAllWinners(0, 10);

        // Validate the results
        assertNotNull(winners, "The winners list should not be null");
        assertTrue(winners.size() <= 10, "The winners list should contain 10 or fewer entries");

        // Check individual fields of the first entry (if data is known)
        if (!winners.isEmpty()) {
            IndyWinnerDTO firstWinner = winners.get(0);
            assertNotNull(firstWinner.getYear(), "Year should not be null");
            assertNotNull(firstWinner.getDriver(), "Driver should not be null");
            assertNotNull(firstWinner.getAverageSpeed(), "Average Speed should not be null");
            assertNotNull(firstWinner.getCountry(), "Country should not be null");
        }
    }

    @Test
    void testGetAllWinners_withOffsetBeyondData() {
        // Fetch data with an offset that exceeds the number of rows
        List<IndyWinnerDTO> winners = dao.getAllWinners(1000, 10);

        // Validate that no data is returned
        assertNotNull(winners, "The winners list should not be null");
        assertTrue(winners.isEmpty(), "The winners list should be empty when offset exceeds available rows");
    }
}
