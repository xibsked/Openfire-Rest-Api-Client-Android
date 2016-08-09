package com.sked.ofrestclient.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The Class UserEntities.
 */
//@XmlRootElement(name = "users")
public class UserEntities {

	/** The users. */
	@SerializedName("user")
	List<UserEntity> users;

	/**
	 * Instantiates a new user entities.
	 */
	public UserEntities() {

	}

	/**
	 * Instantiates a new user entities.
	 *
	 * @param users
	 *            the users
	 */
	public UserEntities(List<UserEntity> users) {
		this.users = users;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	//@XmlElement(name = "user")
	public List<UserEntity> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users
	 *            the new users
	 */
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

}