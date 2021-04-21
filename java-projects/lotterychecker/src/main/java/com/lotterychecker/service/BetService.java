/**
 *
 */
package com.lotterychecker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotterychecker.model.Bet;
import com.lotterychecker.repository.BetRepository;

/**
 * <pre>
 * Author         : Paulo Franklim, paulofranklim@hotmail.com
 * Purpose        : <Purpose>
 * Input files    : N/A
 * Log File       : N/A
 * Output file    : N/A
 *
 * Copyright 2020 github.com/pfranklim
 * </pre>
 */

@Service
public class BetService {
    
    private Logger	  LOG = LoggerFactory.getLogger(BetService.class);
    
    @Autowired
    private BetRepository repository;
    
    public Bet saveBet(Bet bet) {
	LOG.debug("Entry method saveBet(Bet bet)");
	Bet savedBet = null;
	
	validBet(bet);
	
	try {
	    savedBet = repository.save(bet);
	}
	catch (Exception e) {
	    String msg = e.getMessage();
	    LOG.error("Error while trying save bet. " + msg);
	    throw new RuntimeException(msg);
	}
	
	LOG.debug("Exit method saveBet(Bet bet)");
	return savedBet;
    }
    
    private void validBet(Bet bet) {
	LOG.debug("Entry method validBet(Bet bet)");
	
	// TODO: Game Limits method
	
	LOG.debug("Exit method validBet(Bet bet)");
    }
}
