/**
 *
 */
package com.lotterychecker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotterychecker.model.Game;
import com.lotterychecker.repository.GameRepository;

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
@Service
public class GameService {

	private Logger LOG = LoggerFactory.getLogger(GameService.class);

	@Autowired
	private GameRepository repository;

	public Game saveGame(Game game) {
		LOG.debug("Entry method saveGame(Game game)");

		validGame(game);
		Game savedGame = null;
		try {
			savedGame = repository.save(game);
		} catch (Exception e) {
			String msg = e.getMessage();
			LOG.error("Error while trying save game. " + msg);
			throw new RuntimeException(msg);
		}

		LOG.debug("Entry method saveGame(Game game)");
		return savedGame;

	}

	private void validGame(Game game) {
		LOG.debug("Entry method validGame(Game game)");
		if (game.getName() == null) {
			String msg = "The field name is required.";
			LOG.error(msg);
			throw new RuntimeException(msg);
		}

		if (game.getNumberMin() < 0 || game.getNumberMax() < 0) {
			String msg = "The fields numberMin and numberMax are required.";
			LOG.error(msg);
			throw new RuntimeException(msg);
		}

		if (game.getNumberMin() > game.getNumberMax()) {
			String msg = "The field numberMin cannot be higher than numberMax.";
			LOG.error(msg);
			throw new RuntimeException(msg);
		}
		LOG.debug("Exit method validGame(Game game)");
	}
}
