package org.lms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	/**
	 * Represents the primary key of the id of users table.
	 */
	private Integer userId;

	@Column(name = "first_name", nullable = false, length = 30)
	/**
	 * First name of a user
	 */
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 30)
	/**
	 * Last name of a user
	 */
	private String lastName;

	@Column(name = "username", nullable = false, length = 20, unique = true)
	/**
	 * Username of a user
	 */
	private String username;

	@Column(name = "age", nullable = false)
	/**
	 * Age of a user
	 */
	private Integer age;

	@Column(name = "gender", nullable = false)
	/**
	 * Age of a user
	 */
	private Boolean gender;

	@Column(name = "email", nullable = false, length = 40, unique = true)
	/**
	 * Email of a user
	 */
	private String email;

	@Column(name = "password", nullable = false, length = 40)
	/**
	 * Password of a user
	 */
	private String password;

	@Column(name = "activated", nullable = false)
	/**
	 * 0 -> User's profile has not been activated by the administrators yet
	 * 1 -> User's profile is activated
	 * -1 -> User's profile has not been approved by the administrators
	 * -2 -> User's profile has been deactivated
	 */
	private Integer activated;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	/**
	 * List that keeps the roles of a user
	 */
	private List<Role> rolesOfThisUser = new ArrayList<>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getActivated() {
		return activated;
	}

	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	public List<Role> getRolesOfThisUser() {
		return rolesOfThisUser;
	}

	public void setRolesOfThisUser(List<Role> rolesOfThisUser) {
		this.rolesOfThisUser = rolesOfThisUser;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activated == null) ? 0 : activated.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rolesOfThisUser == null) ? 0 : rolesOfThisUser.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (activated == null) {
			if (other.activated != null)
				return false;
		} else if (!activated.equals(other.activated))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rolesOfThisUser == null) {
			if (other.rolesOfThisUser != null)
				return false;
		} else if (!rolesOfThisUser.equals(other.rolesOfThisUser))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", age=" + age + ", gender=" + gender + ", email=" + email + ", password=" + password
				+ ", activated=" + activated + ", rolesOfThisUser=" + rolesOfThisUser + "]";
	}

	/**
	 * Adds a role for a user
	 * @param role
	 */
	public void addRole(Role role) {
		this.rolesOfThisUser.add(role);
		role.getUsersThatHaveThisRole().add(this);
		
	}

}
