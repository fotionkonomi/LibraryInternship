package org.lms.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String firstName;
	private String lastName;
	private String username;
	private Integer age;
	private Boolean gender;
	private String email;
	private Integer activated;
	private String password;
	//private List<RoleDTO> rolesOfThisUser = new ArrayList<>();
	
	public UserDTO() {

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getActivated() {
		return activated;
	}

	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

/*	public List<RoleDTO> getRolesOfThisUser() {
		return rolesOfThisUser;
	}

	public void setRolesOfThisUser(List<RoleDTO> rolesOfThisUser) {
		this.rolesOfThisUser = rolesOfThisUser;
	}
*/	
	

}
