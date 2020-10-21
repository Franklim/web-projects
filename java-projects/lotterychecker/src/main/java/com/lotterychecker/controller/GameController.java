/**
 *
 */
package com.lotterychecker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lotterychecker.model.Game;
import com.lotterychecker.service.GameService;

/**
 * <pre>
 * Author         : Paulo Franklim, paulofranklim@hotmail.com
 * Purpose        : <Purpose>
 * Input files    : N/A
 * Log File       : N/A
 * Output file    : N/A
 *
 * Copyright 2020 github.com/franklim
 * </pre>
 */

@RestController
public class GameController {

	private Logger LOG = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService service;

	public String saveGame(@RequestBody Game game) {
		LOG.debug("Entry method saveGame(@RequestBody Game game)");
		Game savedGame = service.saveGame(game);
		LOG.debug(savedGame.toString());
		LOG.debug("Exit method saveGame(@RequestBody Game game)");
		return "Game '" + savedGame.getName() + "' created. id=" + savedGame.getId();
	}

}
