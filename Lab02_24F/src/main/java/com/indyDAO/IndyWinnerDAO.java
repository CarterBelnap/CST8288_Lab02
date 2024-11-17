package com.indyDAO;

import com.indyDTO.IndyWinnerDTO;

import java.util.List;

/**
 * Data Access Object (DAO) interface for accessing Indi 500 winners.
 */
public interface IndyWinnerDAO {
    /**
     * Retrieves a paginated list of winners from the database.
     *
     * @param offset The starting point of the results.
     * @param limit  The maximum number of results to return.
     * @return A list of IndyWinnerDTO objects.
     */
    List<IndyWinnerDTO> getAllWinners(int offset, int limit);
}
