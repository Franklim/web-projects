/**
 *
 */
package com.lotterychecker.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import com.lotterychecker.model.CheckedResult;
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

public class CheckerUtil {
	private static Logger LOG = LoggerFactory.getLogger(CheckerUtil.class);

	@Autowired
	private static JavaMailSender mailSender;

	public static void setPrize(CheckedResult result) {
		LOG.debug("Entry method setPrize(LotofacilResult result)");
		if (result.getHitNumber() < 11) {
			result.setPrize(BigDecimal.ZERO);
		} else if (result.getHitNumber() == 11) {
			result.setPrize(CheckerConstants.PRIZE_11_HITS);
		} else if (result.getHitNumber() == 12) {
			result.setPrize(CheckerConstants.PRIZE_12_HITS);
		} else if (result.getHitNumber() == 13) {
			result.setPrize(CheckerConstants.PRIZE_13_HITS);
		} else {
			result.setPrize(BigDecimal.TEN);
		}
		LOG.debug("Exit method setPrize(LotofacilResult result)");
	}

	public static String getHittedNumbers(String bet, List<String> drawNumbers) {
		LOG.debug("Entry method getHittedNumbers(String bet, String result)");

		List<String> betNumbers = Arrays.asList(bet.split(","));
		Set<String> hittedNumbers = betNumbers.stream().distinct().filter(drawNumbers::contains)
				.collect(Collectors.toSet());

		LOG.debug("Exit method getHittedNumbers(String bet, String result)");
		return hittedNumbers.toString().replace(" ", "").replace("[", "").replace("]", "");
	}

	public static String getApiJSON(String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return res.getBody();

	}

	public static boolean sendMail(MailCredentialsVO credentials) {
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

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			LOG.error("Error while tryng send mail. " + e.getMessage());

		}
		LOG.debug("result=" + result);
		LOG.debug("Exit method sendMail(MailCredentialsVO credentials)");
		return result;
	}

}