package org.lms.managedbeans;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.UserDTO;
import org.lms.service.UserService;

@ManagedBean(name = "userRequestsBean")
@ViewScoped
public class UserRequestsBean {
	private static final Logger LOGGER = Logger.getLogger(UserRequestsBean.class.getName());

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public void activate(UserDTO userToBeActivated) {
		this.userService.activateUser(userToBeActivated);
		try {
			LOGGER.log(Level.INFO, "User " + userToBeActivated + " was activated successfully");
			FacesContext.getCurrentInstance().getExternalContext().redirect("request.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deActivate(UserDTO userToBeActivated) {
		this.userService.deActivateUser(userToBeActivated);
		LOGGER.log(Level.INFO, "User " + userToBeActivated + " was deactivated successfully");

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("request.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<UserDTO> usersThatAreNotActivated() {
		return userService.findUsersThatAreNotActivated();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
