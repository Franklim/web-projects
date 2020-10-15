/**
 *
 */
package com.lotterychecker.service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotterychecker.model.LotofacilBet;
import com.lotterychecker.model.LotofacilResult;
import com.lotterychecker.repository.LotofacilBetRepository;
import com.lotterychecker.repository.LotofacilResultRepository;
import com.lotterychecker.util.CheckerConstants;
import com.lotterychecker.util.CheckerUtil;
import com.lotterychecker.vo.LotofacilApiResultVO;
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

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private LotofacilResultRepository resultRepository;

	@Autowired
	private LotofacilBetRepository betRepository;

	public boolean saveBet(LotofacilBet bet) {
		LOG.debug("Entry method saveBet(LotofacilBet bet)");

		boolean result = true;
		try {
			betRepository.save(bet);
			LOG.info("Saved bet values. " + bet);
		} catch (Exception e) {
			result = false;
			LOG.error("Error while trying save bet values");
		}

		LOG.debug("Exit method saveBet(LotofacilBet bet)");
		return result;
	}

	public void checkResult() {
		LOG.debug("Entry method checkResult()");

		ObjectMapper mapper = new ObjectMapper();
		LotofacilApiResultVO apiResult = null;

		try {
			String apiJson = CheckerUtil.getApiJSON(CheckerConstants.LOTOFACIL_LAST_RESULT_URL);
			apiResult = mapper.readValue(apiJson, LotofacilApiResultVO.class);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Error trying while create api object. " + e.getMessage());
		}

		long lastDraw = resultRepository.count();
		LOG.debug("lastDraw=" + lastDraw);

		if (apiResult.getId() != null && apiResult.getId().longValue() > lastDraw) {

			LotofacilBet bet = null;

			try {
				bet = betRepository.findById(Long.valueOf(1)).orElseThrow(RuntimeException::new);
				LOG.debug("bet=" + bet);

			} catch (Exception e) {
				LOG.error("Error while trying load bet");
			}

			if (bet != null) {
				String hittedNumbers = CheckerUtil.getHittedNumbers(bet.getNumbers(), apiResult.getNumbers());
				LotofacilResult checkedResult = prepareResult(apiResult, hittedNumbers);

				try {
					LOG.info(checkedResult.toString());
					resultRepository.save(checkedResult);
				} catch (Exception e) {
					LOG.error("Error while saving checked result.");
				}

				LOG.debug("Preparing mail to send");
				MailCredentialsVO mailCredentials = new MailCredentialsVO();
				mailCredentials.setSubject("Lotofacil Draw - " + checkedResult.getId());
				mailCredentials.setTo("paulofranklim@hotmail.com");

				StringBuilder message = new StringBuilder();
				message.append("Draw result: " + checkedResult.getHitNumber());
				message.append("\nHitted Numbers: " + checkedResult.getHittedNumbers());
				message.append("\nPrize : R$" + checkedResult.getPrize());
				mailCredentials.setMessage(message.toString());

				sendMail(mailCredentials);

				String msg = "Hits=[" + checkedResult.getHitNumber() + "]. Hitted Numbers=[" + hittedNumbers + "]";
				LOG.info(msg);
				LOG.debug("Exit method checkResult()");
			}
		} else {
			LOG.debug(Instant.now() + ". No new results available.");
		}

	}

	private LotofacilResult prepareResult(LotofacilApiResultVO apiResult, String hittedNumbers) {
		LOG.debug("Entry method prepareResult(LotofacilApiResultVO apiResult, String hittedNumbers)");
		LotofacilResult result = new LotofacilResult();

		result.setId(apiResult.getId());
		result.setDate(apiResult.getDate());
		result.setNumbers(apiResult.getNumbers().toString());
		result.setHittedNumbers(hittedNumbers);
		result.setHitNumber(hittedNumbers.split(",").length);

		CheckerUtil.setPrize(result);

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
		message.setText(credentials.getMessage());

		try {
			mailSender.send(message);
			LOG.info("Send mail sucess");

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			LOG.error("Error while tryng send mail. " + e.getMessage());

		}
		LOG.debug("result=" + result);
		LOG.debug("Exit method sendMail(MailCredentialsVO credentials)");
		return result;
	}

	public boolean loadAll(int start, int end) {
		LOG.debug("Entry method loadAll(int start, int end)");
		boolean result = true;
		LotofacilBet bet = null;
		List<LotofacilResult> allCheckedResults = new ArrayList<>();
		ObjectMapper mapper;
		LotofacilApiResultVO apiResult;

		try {
			bet = betRepository.findById(Long.valueOf(1)).orElseThrow(RuntimeException::new);
			LOG.debug("bet=" + bet);

		} catch (Exception e) {
			result = false;
			LOG.error("Error while trying load bet");
		}
		if (bet != null) {
			for (int i = start; i <= end; i++) {
				mapper = new ObjectMapper();
				apiResult = new LotofacilApiResultVO();

				try {
					String apiJson = CheckerUtil.getApiJSON(CheckerConstants.LOTOFACIL_ESPECIFIC_RESULT_URL + i);
					apiResult = mapper.readValue(apiJson, LotofacilApiResultVO.class);
				} catch (IOException e) {
					result = false;
					e.printStackTrace();
					LOG.error("Error trying while create api object. " + e.getMessage());
				}

				String hittedNumbers = CheckerUtil.getHittedNumbers(bet.getNumbers(), apiResult.getNumbers());
				LotofacilResult checkedResult = prepareResult(apiResult, hittedNumbers);
				allCheckedResults.add(checkedResult);
				LOG.debug("Result " + checkedResult.getId() + " added");

			}

		}
		try {
			resultRepository.saveAll(allCheckedResults);
			LOG.debug("Saved all draws");
		} catch (Exception e) {
			result = false;
			LOG.error("Error while saving checked result.");
		}

		LOG.debug("Exit method loadAll(int start, int end)");
		LOG.info("Finished load all draws");
		return result;
	}

}
