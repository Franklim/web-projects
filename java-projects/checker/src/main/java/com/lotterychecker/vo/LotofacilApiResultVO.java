/**
 *
 */
package com.lotterychecker.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

@JsonIgnoreProperties(ignoreUnknown = true)
public class LotofacilApiResultVO {

	@JsonProperty("numero_concurso")
	private Long id;
	@JsonProperty("data_concurso")
	private String date;
	@JsonProperty("dezenas")
	private List<String> numbers;

	public LotofacilApiResultVO() {
	}

	/**
	 * @param id
	 * @param date
	 * @param numbers
	 */
	public LotofacilApiResultVO(Long id, String date, List<String> numbers) {
		super();
		this.id = id;
		this.date = date;
		this.numbers = numbers;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the numbers
	 */
	public List<String> getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "LotofacilResult [id=" + id + ", date=" + date + ", numbers=" + numbers + "]";
	}

}
