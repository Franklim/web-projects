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
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotterychecker.model.Bet;
import com.lotterychecker.model.CheckedResult;
import com.lotterychecker.model.User;
import com.lotterychecker.repository.BetRepository;
import com.lotterychecker.repository.CheckedResultRepository;
import com.lotterychecker.util.CheckerConstants;
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
 * Copyright 2020 github.com/franklim
 * </pre>
 */

@Service
public class CheckerService {

	private Logger LOG = LoggerFactory.getLogger(CheckerService.class);

	private Map<String, Long> lastDraws = new HashMap<String, Long>();

	private String LINE = "\n";

	@Autowired
	private CheckedResultRepository resultRepository;

	@Autowired
	private BetRepository betRepository;

	public void checkResult(String game) {
		LOG.debug("Entry method checkResult()");

		ObjectMapper mapper = new ObjectMapper();
		ApiResultVO apiResultVO = null;

		String URL = CheckerConstants.API_URL + game + CheckerConstants.TOKEN_PREFIX;
		try {
			String apiJson = CheckerUtil.getApiJSON(URL);
			apiResultVO = mapper.readValue(apiJson, ApiResultVO.class);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Error trying while create api object. " + e.getMessage());
		}

		// TODO: lastDraws logic
		Long drawNumber = lastDraws.get(game);
		if (drawNumber.compareTo(apiResultVO.getDrawNumber()) < 1) {

			List<Bet> bets = null;
			try {
				// TODO: Query for specific game
				bets = betRepository.findAllByGame(game);
				LOG.debug("bet=" + bets);

			} catch (Exception e) {
				LOG.error("Error while trying load bet");
			}

			if (bets != null && bets.size() > 0) {
				User lastUser = null;
				StringBuilder message = null;
				MailCredentialsVO mailCredentials = null;
				List<MailCredentialsVO> mailCredentialsList = new ArrayList<MailCredentialsVO>();

				for (Bet bet : bets) {

					String hittedNumbers = CheckerUtil.getHittedNumbers(bet.getNumbers(), apiResultVO.getNumbers());
					CheckedResult checkedResult = prepareResult(apiResultVO, hittedNumbers);

					try {
						LOG.info(checkedResult.toString());
						resultRepository.save(checkedResult);
					} catch (Exception e) {
						LOG.error("Error while saving checked result.");
					}

					if (!bet.getUser().equals(lastUser)) {
						mailCredentials = new MailCredentialsVO();
						message = new StringBuilder();

						mailCredentialsList.add(mailCredentials);

						mailCredentials.setSubject(game.toUpperCase() + " DRAW - " + checkedResult.getDrawNumber());
						mailCredentials.setTo(bet.getUser().getMail());
						mailCredentials.setMessage(message);

					}
					message.append("Draw result: " + checkedResult.getHitNumber() + LINE);
					message.append("Hitted Numbers: " + checkedResult.getHittedNumbers() + LINE);
					message.append("Prize : R$" + checkedResult.getPrize() + LINE);

					lastUser = bet.getUser();
				}

				for (MailCredentialsVO credential : mailCredentialsList) {
					CheckerUtil.sendMail(credential);
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

}
