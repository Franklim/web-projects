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
	private Long id;
	private User user;
	private Game game;
	private int firstDraw;
	private int lastDraw;
	private BigDecimal accumulatedPrize;
	private String numbers;

	public Bet() {

	}

	/**
	 * @param user
	 * @param game
	 * @param firstDraw
	 * @param lastDraw
	 * @param accumulatedPrize
	 * @param numbers
	 */
	public Bet(User user, Game game, int firstDraw, int lastDraw, BigDecimal accumulatedPrize, String numbers) {
		super();
		this.user = user;
		this.game = game;
		this.firstDraw = firstDraw;
		this.lastDraw = lastDraw;
		this.accumulatedPrize = accumulatedPrize;
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
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the firstDraw
	 */
	public int getFirstDraw() {
		return firstDraw;
	}

	/**
	 * @param firstDraw the firstDraw to set
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
	 * @param lastDraw the lastDraw to set
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
	 * @param accumulatedPrize the accumulatedPrize to set
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
	 * @param numbers the numbers to set
	 */
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "Bet [id=" + id + ", user=" + user + ", game=" + game + ", firstDraw=" + firstDraw + ", lastDraw="
				+ lastDraw + ", accumulatedPrize=" + accumulatedPrize + ", numbers=" + numbers + "]";
	}

}
