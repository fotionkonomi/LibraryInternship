package org.lms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false)
	private Integer roleId;

	@Column(name = "role_name", nullable = false, length = 12)
	private String roleName;

	@Column(name = "role_description", nullable = false, length = 255)
	private String roleDescription;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "rolesOfThisUser")
	private List<User> usersThatHaveThisRole = new ArrayList<User>();

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<User> getUsersThatHaveThisRole() {
		return usersThatHaveThisRole;
	}

	public void setUsersThatHaveThisRole(List<User> usersThatHaveThisRole) {
		this.usersThatHaveThisRole = usersThatHaveThisRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleDescription == null) ? 0 : roleDescription.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((usersThatHaveThisRole == null) ? 0 : usersThatHaveThisRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (roleDescription == null) {
			if (other.roleDescription != null)
				return false;
		} else if (!roleDescription.equals(other.roleDescription))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (usersThatHaveThisRole == null) {
			if (other.usersThatHaveThisRole != null)
				return false;
		} else if (!usersThatHaveThisRole.equals(other.usersThatHaveThisRole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDescription=" + roleDescription
				+ ", usersThatHaveThisRole=" + usersThatHaveThisRole + "]";
	}

}
