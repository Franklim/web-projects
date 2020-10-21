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

import com.lotterychecker.model.User;
import com.lotterychecker.service.UserService;

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
public class UserController {

	private Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@RequestMapping(value = "save-user", method = RequestMethod.POST)
	public String saveUser(@RequestBody User user) {
		LOG.debug("Entry method saveUser(@RequestBody User user)");
		User savedUser = service.saveUser(user);
		LOG.debug(savedUser.toString());
		LOG.debug("Exit method saveUser(@RequestBody User user)");
		return "User '" + savedUser.getName() + "' created. id=" + savedUser.getId();
	}

}
