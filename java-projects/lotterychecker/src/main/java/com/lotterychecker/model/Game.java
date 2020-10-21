/**
 *
 */
package com.lotterychecker.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "game")
public class Game {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private String name;
	private int numberMin;
	private int numberMax;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Bet bet;

	public Game() {
	}

	/**
	 * @param id
	 * @param name
	 * @param numberMin
	 * @param numberMax
	 */
	public Game(Long id, String name, int numberMin, int numberMax) {
		super();
		this.id = id;
		this.name = name;
		this.numberMin = numberMin;
		this.numberMax = numberMax;
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
	 * @return the numberMin
	 */
	public int getNumberMin() {
		return numberMin;
	}

	/**
	 * @param numberMin the numberMin to set
	 */
	public void setNumberMin(int numberMin) {
		this.numberMin = numberMin;
	}

	/**
	 * @return the numberMax
	 */
	public int getNumberMax() {
		return numberMax;
	}

	/**
	 * @param numberMax the numberMax to set
	 */
	public void setNumberMax(int numberMax) {
		this.numberMax = numberMax;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", numberMin=" + numberMin + ", numberMax=" + numberMax + "]";
	}

}
