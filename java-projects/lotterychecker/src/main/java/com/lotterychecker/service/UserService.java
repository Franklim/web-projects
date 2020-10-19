/**
 *
 */
package com.lotterychecker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotterychecker.model.User;
import com.lotterychecker.repository.UserRepository;

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
public class UserService {

	private Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		LOG.debug("Entry method saveUser(User user)");

		validUser(user);
		User savedUser = null;
		try {
			savedUser = repository.save(user);
		} catch (Exception e) {
			String msg = e.getMessage();
			LOG.error("Error while trying save user. " + msg);
			throw new RuntimeException(msg);
		}

		LOG.debug("Exit method saveUser(User user)");
		return savedUser;
	}

	private void validUser(User user) {
		LOG.debug("Entry method validUser(User user)");

		if (user.getName() == null || user.getMail() == null || user.getPassword() == null) {
			String msg = "The fields: name, mail and password are required.";
			LOG.error(msg);
			throw new RuntimeException(msg);
		}

		LOG.debug("Exit method validUser(User user)");
	}
}
