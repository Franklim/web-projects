/**
 *
 */
package com.lotterychecker.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
import com.lotterychecker.model.Game;
import com.lotterychecker.model.User;
import com.lotterychecker.repository.BetRepository;
import com.lotterychecker.repository.CheckedResultRepository;
import com.lotterychecker.repository.GameRepository;
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
    private Logger		    LOG	 = LoggerFactory.getLogger(CheckerService.class);
    
    private String		    LINE = "\n";
    
    private ApiResultVO		    apiResultVO;

    @Value("${application.prop.api.url}")
    private String		    API_URL;

    @Value("${application.prop.api.token}")
    private String		    TOKEN_PREFIX;

    @Autowired
    private CheckedResultRepository resultRepository;
    
    @Autowired
    private BetRepository	    betRepository;
    
    @Autowired
    private UserRepository	    userRepository;
    
    @Autowired
    private GameRepository	    gameRepository;

    @Autowired
    private JavaMailSender	    mailSender;
    
    public void checkResult(String gameName) {
	LOG.debug("Entry method checkResult()");

	String URL = API_URL + gameName + TOKEN_PREFIX;
	try {
	    String apiJson = CheckerUtil.getApiJSON(URL);
	    apiResultVO = new ObjectMapper().readValue(apiJson, ApiResultVO.class);
	}
	catch (IOException e) {
	    e.printStackTrace();
	    LOG.error("Error trying while create api object. " + e.getMessage());
	}
	
	Game game = gameRepository.findGameByName(gameName);
	List<Bet> bets = betRepository.findAll();

	if (game != null && game.getLastDraw().compareTo(apiResultVO.getDrawNumber()) == -1) {
	    
	    if (bets != null && bets.size() > 0) {
		
		for (Bet bet : bets) {
		    User user = userRepository.findById(bet.getUserId()).orElse(null);

		    String hittedNumbers = CheckerUtil.getHittedNumbers(bet.getNumbers(), apiResultVO.getNumbers());
		    
		    CheckedResult checkedResult = prepareResult(apiResultVO, hittedNumbers);
		    checkedResult.setUserId(user.getId());
		    
		    bet.setAccumulatedPrize(bet.getAccumulatedPrize().add(checkedResult.getPrize()));
		    game.setLastDraw(checkedResult.getDrawNumber());
		    
		    try {
			LOG.info(checkedResult.toString());

			betRepository.save(bet);
			gameRepository.save(game);
			resultRepository.save(checkedResult);
		    }
		    catch (Exception e) {
			LOG.error("Error while saving checked result.");
			LOG.error(e.getLocalizedMessage());
			LOG.error(e.getMessage());
		    }

		    StringBuilder message = new StringBuilder();
		    message.append("Draw result: " + checkedResult.getHitNumber() + LINE);
		    message.append("Hitted Numbers: " + checkedResult.getHittedNumbers() + LINE);
		    message.append("Prize : R$" + checkedResult.getPrize() + LINE);
		    message.append("Accumuled Prize : R$" + bet.getAccumulatedPrize() + LINE);

		    MailCredentialsVO mailCredentials = new MailCredentialsVO();
		    mailCredentials.setSubject(gameName.toUpperCase() + " DRAW - " + checkedResult.getDrawNumber());
		    mailCredentials.setTo(user.getMail());
		    mailCredentials.setMessage(message);
		    
		    sendMail(mailCredentials);

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
	result.setName(apiResult.getName());

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
