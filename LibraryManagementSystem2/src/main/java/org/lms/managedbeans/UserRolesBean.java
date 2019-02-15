package org.lms.managedbeans;

import java.util.List;

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
	private List<UserDTO> allUsers;
	private UserDTO userSelected;
	private List<UserDTO> filteredUsers;
	
	@PostConstruct
	public void init() {
		allUsers = userService.listUser();
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

	public void onRowEdit(RowEditEvent event) {
		UserDTO userDTO = (UserDTO) event.getObject();
		int find = allUsers.indexOf(userDTO);
		try {
			userService.updateUser(userDTO);
			FacesMessage msg = new FacesMessage("User Edited", userDTO.getUsername());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataIntegrityViolationException e) {
			userDTO.setUsername("Error");
			allUsers.set(find, userDTO);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or email are already taken", null));
		}
		allUsers = userService.listUser();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((UserDTO) event.getObject()).getUsername());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
}
