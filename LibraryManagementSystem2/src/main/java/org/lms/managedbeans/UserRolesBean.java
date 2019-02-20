package org.lms.managedbeans;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.UserDTO;
import org.lms.service.UserService;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "userRolesBean")
@ViewScoped
public class UserRolesBean {

	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	private List<UserDTO> allUsers;
	private UserDTO userSelected;
	private List<UserDTO> filteredUsers;
	

	@PostConstruct
	public void init() {
		allUsers = userService.listUser();
		allUsers.remove(userBean.getUserDTOLogged());
	}

	

	public Boolean isAUserDTOAdmin(UserDTO userDTO) {
		return this.userService.isAUserAdmin(userDTO);
	}

	public Boolean isAUserDTOSecretary(UserDTO userDTO) {
		return this.userService.isAUserSecretary(userDTO);
	}

	public void makeAdmin(UserDTO userDTO) {
		this.userService.makeUserAdmin(userDTO);
	}

	public void makeSecretary(UserDTO userDTO) {
		this.userService.makeUserSecretary(userDTO);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<UserDTO> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<UserDTO> allUsers) {
		this.allUsers = allUsers;
	}

	public UserDTO getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(UserDTO userSelected) {
		this.userSelected = userSelected;
	}

	public List<UserDTO> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<UserDTO> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public String goToEdit() {
		return "edit-user?faces-redirect=true&id=" + userSelected.getUserId();
	}



	public UserBean getUserBean() {
		return userBean;
	}



	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}


}
