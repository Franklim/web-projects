/**
 *
 */
package com.lotterychecker.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

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

@Entity
public class LotofacilResult {

	@Id
	private Long id;
	private String date;
	private String numbers;
	private String hittedNumbers;
	private int hitNumber;
	private BigDecimal prize;

	public LotofacilResult() {
	}

	/**
	 * @param id
	 * @param date
	 * @param numbers
	 * @param hittedNumbers
	 * @param hitNumber
	 * @param prize
	 */
	public LotofacilResult(Long id, String date, String numbers, String hittedNumbers, int hitNumber,
			BigDecimal prize) {
		super();
		this.id = id;
		this.date = date;
		this.numbers = numbers;
		this.hittedNumbers = hittedNumbers;
		this.hitNumber = hitNumber;
		this.prize = prize;
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
	public String getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	/**
	 * @return the hittedNumbers
	 */
	public String getHittedNumbers() {
		return hittedNumbers;
	}

	/**
	 * @param hittedNumbers the hittedNumbers to set
	 */
	public void setHittedNumbers(String hittedNumbers) {
		this.hittedNumbers = hittedNumbers;
	}

	/**
	 * @return the hitNumber
	 */
	public int getHitNumber() {
		return hitNumber;
	}

	/**
	 * @param hitNumber the hitNumber to set
	 */
	public void setHitNumber(int hitNumber) {
		this.hitNumber = hitNumber;
	}

	/**
	 * @return the prize
	 */
	public BigDecimal getPrize() {
		return prize;
	}

	/**
	 * @param prize the prize to set
	 */
	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	@Override
	public String toString() {
		return "LotofacilResult [id=" + id + ", date=" + date + ", numbers=" + numbers + ", hittedNumbers="
				+ hittedNumbers + ", hitNumber=" + hitNumber + ", prize=" + prize + "]";
	}

}
