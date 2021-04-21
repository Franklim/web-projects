/**
 *
 */
package com.lotterychecker.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class CheckedResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long       id;
    private String     name;
    private Long       drawNumber;
    private String     date;
    private String     numbers;
    private String     hittedNumbers;
    private int	       hitNumber;
    private BigDecimal prize;
    private Long       userId;
    
    public CheckedResult() {
    }

    /**
     * @param id
     * @param name
     * @param drawNumber
     * @param date
     * @param numbers
     * @param hittedNumbers
     * @param hitNumber
     * @param prize
     * @param userId
     */
    public CheckedResult(Long id, String name, Long drawNumber, String date, String numbers, String hittedNumbers, int hitNumber, BigDecimal prize,
            Long userId) {
	super();
	this.id = id;
	this.name = name;
	this.drawNumber = drawNumber;
	this.date = date;
	this.numbers = numbers;
	this.hittedNumbers = hittedNumbers;
	this.hitNumber = hitNumber;
	this.prize = prize;
	this.userId = userId;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
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
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the drawNumber
     */
    public Long getDrawNumber() {
	return drawNumber;
    }

    /**
     * @param drawNumber
     *            the drawNumber to set
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
     * @param date
     *            the date to set
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
     * @param numbers
     *            the numbers to set
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
     * @param hittedNumbers
     *            the hittedNumbers to set
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
     * @param hitNumber
     *            the hitNumber to set
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
     * @param prize
     *            the prize to set
     */
    public void setPrize(BigDecimal prize) {
	this.prize = prize;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
	return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "CheckedResult [id=" + id + ", name=" + name + ", drawNumber=" + drawNumber + ", date=" + date + ", numbers=" + numbers + ", hittedNumbers="
	        + hittedNumbers + ", hitNumber=" + hitNumber + ", prize=" + prize + ", userId=" + userId + "]";
    }

}
