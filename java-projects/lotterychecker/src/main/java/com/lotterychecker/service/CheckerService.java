/**
 *
 */
package com.lotterychecker.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotterychecker.model.Bet;
import com.lotterychecker.model.CheckedResult;
import com.lotterychecker.model.User;
import com.lotterychecker.repository.BetRepository;
import com.lotterychecker.repository.CheckedResultRepository;
import com.lotterychecker.repository.UserRepository;
import com.lotterychecker.util.CheckerUtil;
import com.lotterychecker.vo.ApiResultVO;
import com.lotterychecker.vo.MailCredentialsVO;

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

@Service
public class CheckerService {

    @Value("${application.prop.api.url}")
    private String		    API_URL;

    @Value("${application.prop.api.token}")
    private String		    TOKEN_PREFIX;

    private Logger		    LOG	      = LoggerFactory.getLogger(CheckerService.class);

    private Map<String, Long>	    lastDraws = new HashMap<String, Long>();

    private String		    LINE      = "\n";

    @Autowired
    private CheckedResultRepository resultRepository;
    
    @Autowired
    private BetRepository	    betRepository;
    
    @Autowired
    private UserRepository	    userRepository;

    @Autowired
    private JavaMailSender	    mailSender;
    
    public void checkResult(String game) {
	LOG.debug("Entry method checkResult()");

	ObjectMapper mapper = new ObjectMapper();
	ApiResultVO apiResultVO = null;

	String URL = API_URL + game + TOKEN_PREFIX;
	try {
	    String apiJson = CheckerUtil.getApiJSON(URL);
	    apiResultVO = mapper.readValue(apiJson, ApiResultVO.class);
	}
	catch (IOException e) {
	    e.printStackTrace();
	    LOG.error("Error trying while create api object. " + e.getMessage());
	}

	// TODO: lastDraws logic
	// Long drawNumber = lastDraws.get(game);
	Long drawNumber = Long.valueOf(0);
	if (drawNumber.compareTo(apiResultVO.getDrawNumber()) < 1) {

	    List<Bet> bets = null;
	    try {
		// TODO: Query for specific game
		bets = betRepository.findAll();
		LOG.debug("bet=" + bets);

	    }
	    catch (Exception e) {
		LOG.error("Error while trying load bet");
		LOG.error(e.getMessage());
		LOG.error(e.getLocalizedMessage());
	    }

	    if (bets != null && bets.size() > 0) {
		Long lastUserId = null;
		StringBuilder message = null;
		MailCredentialsVO mailCredentials = null;
		List<MailCredentialsVO> mailCredentialsList = new ArrayList<MailCredentialsVO>();

		for (Bet bet : bets) {

		    String hittedNumbers = CheckerUtil.getHittedNumbers(bet.getNumbers(), apiResultVO.getNumbers());
		    CheckedResult checkedResult = prepareResult(apiResultVO, hittedNumbers);

		    try {
			LOG.info(checkedResult.toString());
			resultRepository.save(checkedResult);
		    }
		    catch (Exception e) {
			LOG.error("Error while saving checked result.");
			LOG.error(e.getLocalizedMessage());
			LOG.error(e.getMessage());
		    }
		    User user = userRepository.findById(bet.getUserId()).orElse(null);
		    if (!user.getId().equals(lastUserId)) {
			mailCredentials = new MailCredentialsVO();
			message = new StringBuilder();

			mailCredentialsList.add(mailCredentials);

			mailCredentials.setSubject(game.toUpperCase() + " DRAW - " + checkedResult.getDrawNumber());
			mailCredentials.setTo(user.getMail());
			mailCredentials.setMessage(message);

		    }
		    message.append("Draw result: " + checkedResult.getHitNumber() + LINE);
		    message.append("Hitted Numbers: " + checkedResult.getHittedNumbers() + LINE);
		    message.append("Prize : R$" + checkedResult.getPrize() + LINE);

		    lastUserId = bet.getUserId();
		}

		for (MailCredentialsVO credential : mailCredentialsList) {
		    sendMail(credential);
		}

		LOG.debug("Exit method checkResult()");
	    }
	}
    }

    private CheckedResult prepareResult(ApiResultVO apiResult, String hittedNumbers) {
	LOG.debug("Entry method prepareResult(LotofacilApiResultVO apiResult, String hittedNumbers)");
	CheckedResult result = new CheckedResult();

	result.setDrawNumber(apiResult.getDrawNumber());
	result.setDate(apiResult.getDate());
	result.setNumbers(apiResult.getNumbers().toString());
	result.setHittedNumbers(hittedNumbers);
	result.setHitNumber(hittedNumbers.split(",").length);
	result.setPrize(BigDecimal.ZERO);

	LOG.debug("Exit method prepareResult(LotofacilApiResultVO apiResult, String hittedNumbers)");
	return result;
    }

    public boolean sendMail(MailCredentialsVO credentials) {
	LOG.debug("Entry method sendMail(MailCredentialsVO credentials)");
	LOG.debug("credentials" + credentials);
	boolean result = true;
	
	SimpleMailMessage message = new SimpleMailMessage();
	message.setSubject(credentials.getSubject());
	message.setTo(credentials.getTo());
	message.setText(credentials.getMessage().toString());
	
	try {
	    mailSender.send(message);
	    LOG.info("Send mail sucess");
	    
	}
	catch (Exception e) {
	    result = false;
	    LOG.error("Error while tryng send mail. " + e.getMessage());
	    
	}
	LOG.debug("result=" + result);
	LOG.debug("Exit method sendMail(MailCredentialsVO credentials)");
	return result;
    }

}
