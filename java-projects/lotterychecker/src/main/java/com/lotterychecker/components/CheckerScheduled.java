/**
 *
 */
package com.lotterychecker.components;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

@Component
public class CheckerScheduled {

	private static final Logger LOG = LoggerFactory.getLogger(CheckerScheduled.class);

	@Autowired
	CheckerService service;

	@Scheduled(fixedDelay = 3600000)
	private void scheduledCheck() {
		LOG.debug("Entry method scheduledCheck()");
		LOG.info(Instant.now() + " - Initializing check");

		service.checkResult();

		LOG.debug("Exit method scheduledCheck()");
	}
}
