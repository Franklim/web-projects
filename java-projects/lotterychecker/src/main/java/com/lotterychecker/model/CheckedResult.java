/**
 *
 */
package com.lotterychecker.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class CheckedResult {

	@Id
	private Long id;
	private String name;
	@OneToOne(mappedBy = "bet", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private User user;
	private Long drawNumber;
	private String date;
	private String numbers;
	private String hittedNumbers;
	private int hitNumber;
	private BigDecimal prize;

	public CheckedResult() {
	}

	/**
	 * @param name
	 * @param user
	 * @param drawNumber
	 * @param date
	 * @param numbers
	 * @param hittedNumbers
	 * @param hitNumber
	 * @param prize
	 */
	public CheckedResult(String name, User user, Long drawNumber, String date, String numbers, String hittedNumbers,
			int hitNumber, BigDecimal prize) {
		super();
		this.name = name;
		this.user = user;
		this.drawNumber = drawNumber;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the drawNumber
	 */
	public Long getDrawNumber() {
		return drawNumber;
	}

	/**
	 * @param drawNumber the drawNumber to set
	 */
	public void setDrawNumber(Long drawNumber) {
		this.drawNumber = drawNumber;
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
		return "CheckedResult [id=" + id + ", name=" + name + ", user=" + user + ", drawNumber=" + drawNumber
				+ ", date=" + date + ", numbers=" + numbers + ", hittedNumbers=" + hittedNumbers + ", hitNumber="
				+ hitNumber + ", prize=" + prize + "]";
	}

}
