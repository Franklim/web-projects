/**
 *
 */
package com.lotterychecker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotterychecker.model.Bet;

/**
 * <pre>
 * Author         : Paulo Franklim, paulofranklim@hotmail.com
 * Purpose        : <Purpose>
 * Input files    : N/A
 * Log File       : N/A
 * Output file    : N/A
 *
 * Copyright 2020 PFranklim
 * </pre>
 */

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

	public List<Bet> findAllByGame(String game);

}
