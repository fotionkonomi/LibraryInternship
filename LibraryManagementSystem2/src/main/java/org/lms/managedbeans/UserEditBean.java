package org.lms.managedbeans;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.UserDTO;
import org.lms.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "userEditBean")
@ViewScoped
public class UserEditBean {

	private static final Logger LOGGER = Logger.getLogger(UserEditBean.class.getName());

	private UserDTO userToEdit;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	private String newFirstName;
	private String newLastName;
	private Integer newAge;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String userIdString = paramMap.get("id");
		try {
			Integer userId = Integer.parseInt(userIdString);
			userToEdit = userService.getUserById(userId);
			LOGGER.log(Level.INFO, "User was gotten successfully");
		} catch (NullPointerException e) {
			LOGGER.log(Level.WARNING, "User was not found");
			redirectTo404();

		} catch (NumberFormatException e) {
			LOGGER.log(Level.INFO, "User was gotten successfully");
			redirectTo404();
		}
	}

	public void deActivateAccount() {
		userService.deleteAccount(userToEdit);
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/LibraryManagementSystem/Admin/choose-roles.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void change() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean checkForError = false;
		if (newFirstName.length() == 0) {
			newFirstName = userToEdit.getFirstName();
		} else if (newFirstName.equals(userToEdit.getFirstName())) {
			context.addMessage("firstName", new FacesMessage("You entered the same first name as before"));
			checkForError = true;
		}
		if (newLastName.length() == 0) {
			newLastName = userToEdit.getLastName();
		} else if (newLastName.equals(userToEdit.getLastName())) {
			context.addMessage("lastName", new FacesMessage("You entered the same last name as before"));
			checkForError = true;
		}

		if (newAge == null) {
			newAge = userToEdit.getAge();
		} else if (newAge == userToEdit.getAge()) {
			context.addMessage("age",
					new FacesMessage("You entered the same age as before!\nYou keep getting older you know!"));
			checkForError = true;
		}

		if (checkForError == true) {
			LOGGER.log(Level.WARNING, "User Data Not Changed");

			return;
		}
		if (userToEdit.getFirstName().equals(newFirstName) && userToEdit.getLastName().equals(newLastName)
				&& userToEdit.getAge() == newAge) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "You did not enter any new data!", null));
			return;
		}
		userToEdit.setFirstName(newFirstName);
		userToEdit.setLastName(newLastName);
		userToEdit.setAge(newAge);
		try {
			userService.updateUser(userToEdit);
			context.addMessage("age", new FacesMessage("User was edited successfully"));
			LOGGER.log(Level.INFO, "User Data Was Changed Successfully");

		} catch (DataIntegrityViolationException e) {
			context.addMessage("Existing", new FacesMessage("Username or email are already taken"));
			userToEdit = (UserDTO) context.getExternalContext().getSessionMap().get("user");
			LOGGER.log(Level.WARNING, "User Data Not Changed");

			return;
		} catch (org.hibernate.exception.DataException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data you put was too long!", null));
			LOGGER.log(Level.WARNING, "User Data Not Changed");

			return;
		}
	}

	public UserDTO getUserToEdit() {
		return userToEdit;
	}

	public void setUserToEdit(UserDTO userToEdit) {
		this.userToEdit = userToEdit;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private void redirectTo404() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/LibraryManagementSystem/error/error-404.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getNewFirstName() {
		return newFirstName;
	}

	public void setNewFirstName(String newFirstName) {
		this.newFirstName = newFirstName;
	}

	public String getNewLastName() {
		return newLastName;
	}

	public void setNewLastName(String newLastName) {
		this.newLastName = newLastName;
	}

	public Integer getNewAge() {
		return newAge;
	}

	public void setNewAge(Integer newAge) {
		this.newAge = newAge;
	}

	public boolean isUserAdmin() {
		return userService.isAUserAdmin(userToEdit);
	}

}
