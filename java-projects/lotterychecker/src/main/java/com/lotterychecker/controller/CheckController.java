/**
 *
 */
package com.lotterychecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lotterychecker.model.LotofacilBet;
import com.lotterychecker.service.CheckerService;

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
public class CheckController {

	@Autowired
	CheckerService service;

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public void check() {
		service.checkResult();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public boolean save(@RequestBody LotofacilBet bet) {
		return service.saveBet(bet);
	}
}
