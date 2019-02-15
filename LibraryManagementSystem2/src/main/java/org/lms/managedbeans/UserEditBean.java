package org.lms.managedbeans;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.UserDTO;
import org.lms.service.UserService;

@ManagedBean(name = "userEditBean")
@ViewScoped
public class UserEditBean {

	private UserDTO userToEdit;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String userIdString = paramMap.get("id");
		try {
			Integer userId = Integer.parseInt(userIdString);
			userToEdit = userService.getUserById(userId);
		} catch (NullPointerException e) {
			redirectTo404();

		} catch (NumberFormatException e) {
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

}
