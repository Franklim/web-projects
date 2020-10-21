/**
 *
 */
package com.lotterychecker.components;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lotterychecker.service.CheckerService;
import com.lotterychecker.util.CheckerConstants;

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

	List<String> draws = Arrays.asList(CheckerConstants.LOTOFACIL);

	@Scheduled(fixedDelay = 3600000)
	private void scheduledCheck() {
		LOG.debug("Entry method scheduledCheck()");
		LOG.info("START CHECK - " + Instant.now());
		for (String draw : draws) {
			service.checkResult(draw);
		}
		LOG.info("END CHECK - " + Instant.now());
		LOG.debug("Exit method scheduledCheck()");
	}
}
