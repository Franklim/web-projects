/**
 *
 */
package com.lotterychecker.model;

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
 * Copyright 2020 PFranklim
 * </pre>
 */

@Entity
public class LotofacilBet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String numbers;
	private Long start;
	private Long end;

	public LotofacilBet() {
	}

	/**
	 * @param id
	 * @param numbers
	 * @param start
	 * @param end
	 */
	public LotofacilBet(Long id, String numbers, Long start, Long end) {
		super();
		this.id = id;
		this.numbers = numbers;
		this.start = start;
		this.end = end;
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
	 * @return the start
	 */
	public Long getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Long start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Long getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "LotofacilBetVO [id=" + id + ", numbers=" + numbers + ", start=" + start + ", end=" + end + "]";
	}

}
