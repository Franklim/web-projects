/**
 *
 */
package com.lotterychecker.vo;

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

public class MailCredentialsVO {

	private String subject;
	private String to;
	private String message;

	public MailCredentialsVO() {
	}

	/**
	 * @param subject
	 * @param to
	 * @param message
	 */
	public MailCredentialsVO(String subject, String to, String message) {
		super();
		this.subject = subject;
		this.to = to;
		this.message = message;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MailCredentialsVO [subject=" + subject + ", to=" + to + ", message=" + message + "]";
	}

}
