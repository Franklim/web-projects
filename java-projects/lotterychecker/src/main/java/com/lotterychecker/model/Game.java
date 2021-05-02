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
 * Copyright 2020 github.com/pfranklim
 * </pre>
 */

@Entity
public class Game {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long   id;
    private String name;
    private int	   numberMin;
    private int	   numberMax;
    private Long   lastDraw;

    public Game() {
    }

    /**
     * @param name
     * @param numberMin
     * @param numberMax
     * @param lastDraw
     */
    public Game(String name, int numberMin, int numberMax, Long lastDraw) {
	super();
	this.name = name;
	this.numberMin = numberMin;
	this.numberMax = numberMax;
	this.lastDraw = lastDraw;
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
     * @return the numberMin
     */
    public int getNumberMin() {
	return numberMin;
    }

    /**
     * @param numberMin
     *            the numberMin to set
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
     * @param numberMax
     *            the numberMax to set
     */
    public void setNumberMax(int numberMax) {
	this.numberMax = numberMax;
    }

    /**
     * @return the lastDraw
     */
    public Long getLastDraw() {
	return lastDraw;
    }

    /**
     * @param lastDraw
     *            the lastDraw to set
     */
    public void setLastDraw(Long lastDraw) {
	this.lastDraw = lastDraw;
    }

    @Override
    public String toString() {
	return "Game [id=" + id + ", name=" + name + ", numberMin=" + numberMin + ", numberMax=" + numberMax + ", lastDraw=" + lastDraw + "]";
    }

}
