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
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long       id;
    private int	       firstDraw;
    private int	       lastDraw;
    private BigDecimal accumulatedPrize;
    private String     numbers;
    private Long       userId;
    private Long       gameId;

    public Bet() {
    }
    
    /**
     * @param id
     * @param firstDraw
     * @param lastDraw
     * @param accumulatedPrize
     * @param numbers
     * @param userId
     * @param gameId
     */
    public Bet(Long id, int firstDraw, int lastDraw, BigDecimal accumulatedPrize, String numbers, Long userId, Long gameId) {
	super();
	this.id = id;
	this.firstDraw = firstDraw;
	this.lastDraw = lastDraw;
	this.accumulatedPrize = accumulatedPrize;
	this.numbers = numbers;
	this.userId = userId;
	this.gameId = gameId;
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
     * @return the firstDraw
     */
    public int getFirstDraw() {
	return firstDraw;
    }
    
    /**
     * @param firstDraw
     *            the firstDraw to set
     */
    public void setFirstDraw(int firstDraw) {
	this.firstDraw = firstDraw;
    }
    
    /**
     * @return the lastDraw
     */
    public int getLastDraw() {
	return lastDraw;
    }
    
    /**
     * @param lastDraw
     *            the lastDraw to set
     */
    public void setLastDraw(int lastDraw) {
	this.lastDraw = lastDraw;
    }
    
    /**
     * @return the accumulatedPrize
     */
    public BigDecimal getAccumulatedPrize() {
	return accumulatedPrize;
    }
    
    /**
     * @param accumulatedPrize
     *            the accumulatedPrize to set
     */
    public void setAccumulatedPrize(BigDecimal accumulatedPrize) {
	this.accumulatedPrize = accumulatedPrize;
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
    
    /**
     * @return the gameId
     */
    public Long getGameId() {
	return gameId;
    }
    
    /**
     * @param gameId
     *            the gameId to set
     */
    public void setGameId(Long gameId) {
	this.gameId = gameId;
    }
    
    @Override
    public String toString() {
	return "Bet [id=" + id + ", firstDraw=" + firstDraw + ", lastDraw=" + lastDraw + ", accumulatedPrize=" + accumulatedPrize + ", numbers=" + numbers
	        + ", userId=" + userId + ", gameId=" + gameId + "]";
    }
    
}
