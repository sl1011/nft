package com.shitouren.core.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HttpFormParameter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	private boolean hidden;

	/**
	 * 
	 */
	public HttpFormParameter() {
		super();
	}

	/**
	 * @param name
	 * @param value
	 */
	public HttpFormParameter(String name, String value) {
		this(name, value, true);
	}

	/**
	 * @param name
	 * @param value
	 * @param hidden
	 */
	public HttpFormParameter(String name, String value, boolean hidden) {
		super();
		this.name = name;
		this.value = value;
		this.hidden = hidden;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden
	 *            the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

}
