/**
 *
 */
package com.lotterychecker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lotterychecker.model.Bet;
import com.lotterychecker.service.BetService;

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

@RestController
public class BetController {
    
    private Logger     LOG = LoggerFactory.getLogger(BetController.class);
    
    @Autowired
    private BetService service;
    
    @RequestMapping(value = "save-bet", method = RequestMethod.POST)
    public String saveGame(@RequestBody Bet bet) {
	LOG.debug("Entry method saveGame(@RequestBody Game game)");
	Bet savedBet = service.saveBet(bet);
	LOG.debug(savedBet.toString());
	LOG.debug("Exit method saveGame(@RequestBody Game game)");
	return "Bet created. id=" + savedBet.getId();
    }
    
}
