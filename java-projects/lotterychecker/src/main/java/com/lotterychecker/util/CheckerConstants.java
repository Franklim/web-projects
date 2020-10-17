/**
 *
 */
package com.lotterychecker.util;

import java.math.BigDecimal;

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

public class CheckerConstants {

	public static final BigDecimal PRIZE_11_HITS = new BigDecimal("5");
	public static final BigDecimal PRIZE_12_HITS = new BigDecimal("10");
	public static final BigDecimal PRIZE_13_HITS = new BigDecimal("25");

	public static final String API_URL = "https://apiloterias.com.br/app/resultado?loteria=";
	public static final String TOKEN_PREFIX = "&token=16Q6XoOUilHAaLG";

	public static final String DRAW_NUMBER_PREFIX = "&concurso=";

	public static final String LOTOFACIL = "lotofacil";

}
