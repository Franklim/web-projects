/**
 *
 */
package com.lotterychecker.components;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lotterychecker.service.CheckerService;
import com.lotterychecker.util.CheckerUtil;

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

@Component
public class CheckerScheduled {
    
    private static final Logger	LOG		   = LoggerFactory.getLogger(CheckerScheduled.class);
    
    @Autowired
    CheckerService		service;
    
    @Value("${application.prop.api.scheduler.game.types}")
    private String		games;

    private final String	DELAY_PROP	   = "${application.prop.api.scheduler.delay:3600000}";

    private final String	INITIAL_DELAY_PROP = "${application.prop.api.scheduler.initial.delay:0}";
    
    @Scheduled(fixedDelayString = DELAY_PROP, initialDelayString = INITIAL_DELAY_PROP)
    private void scheduledCheck() {
	LOG.debug("Entry method scheduledCheck()");
	List<String> draws = Arrays.asList(games.split(","));
	
	Instant start = Instant.now();
	LOG.info("START CHECK - " + CheckerUtil.dateTimeFormatter(start));
	for (String draw : draws) {
	    service.checkResult(draw);
	}
	Instant end = Instant.now();
	LOG.info("END CHECK - " + CheckerUtil.dateTimeFormatter(end));
	LOG.info("CHECK DURATION: " + Duration.between(start, end).toMillis() + " millis.");
	LOG.debug("Exit method scheduledCheck()");
    }
}
