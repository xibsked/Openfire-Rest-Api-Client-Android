package com.sked.ofrestclient.entity;

import java.util.List;

/**
 * The Class SystemProperties.
 */
//@XmlRootElement(name = "properties")
public class SystemProperties {

	/** The properties. */
	List<SystemProperty> properties;

	/**
	 * Instantiates a new system properties.
	 */
	public SystemProperties() {

	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	//@XmlElement(name = "property")
	public List<SystemProperty> getProperties() {
		return properties;
	}


	/**
	 * Sets the properties.
	 *
	 * @param properties the new properties
	 */
	public void setProperties(List<SystemProperty> properties) {
		this.properties = properties;
	}
}
