/**
 *
 */
package com.lotterychecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/check-result/{game}", method = RequestMethod.GET)
    public String check(@PathVariable("game") String game) {
	service.checkResult(game);
	return "OK";
    }

}
