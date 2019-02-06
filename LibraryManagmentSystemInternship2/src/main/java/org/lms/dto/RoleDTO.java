package org.lms.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {

	private Integer roleId;
	private String roleName;
	private String roleDescription;
//	private List<UserDTO> usersThatHaveThisRole = new ArrayList<>();
	
	public RoleDTO() {

	}

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

/*	public List<UserDTO> getUsersThatHaveThisRole() {
		return usersThatHaveThisRole;
	}

	public void setUsersThatHaveThisRole(List<UserDTO> usersThatHaveThisRole) {
		this.usersThatHaveThisRole = usersThatHaveThisRole;
	}
*/
	
}
