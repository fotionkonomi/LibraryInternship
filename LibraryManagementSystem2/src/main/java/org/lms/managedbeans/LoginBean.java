package org.lms.managedbeans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.service.LoginService;
import org.lms.service.RoleService;
import org.lms.service.UserService;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
	private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());
	private UserDTO userDTO;
	private String username;
	private String password;
	@ManagedProperty(value = "#{loginService}")
	private LoginService loginService;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{roleService}")
	private RoleService roleService;

	@PostConstruct
	public void init() {
		userDTO = new UserDTO();
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		userDTO = loginService.login(username, password);
		
		if (userDTO == null) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong username or password", null));
			return null;
		} else {
			if (userDTO.getActivated() == 0) {
				context.addMessage(null,
						new FacesMessage("This account has not been activated yet by the administrators, please wait"));
				LOGGER.log(Level.INFO, "Account not approved yet");
				return null;
			} else if (userDTO.getActivated() == -2) {
				context.addMessage(null, new FacesMessage("This account has been deactivated"));
				LOGGER.log(Level.INFO, "Deactivated Account");
				return null;

			} else if (userDTO.getActivated() == -1) {
				context.addMessage(null,
						new FacesMessage("Your profile was not approved, this profile is now deleted\r\n"
								+ "		from our database, please create a new profile with correct data"));
				LOGGER.log(Level.INFO, "Disapproved Account");
				userService.deleteUser(userDTO);
				return null;
			}
			
			List<RoleDTO> rolesOfThisUser = roleService.getRolesOfAUser(userDTO);
			context.getExternalContext().getSessionMap().put("user", userDTO);
			context.getExternalContext().getSessionMap().put("userId", userDTO.getUserId());
			context.getExternalContext().getSessionMap().put("firstName", userDTO.getFirstName());
			context.getExternalContext().getSessionMap().put("lastName", userDTO.getLastName());
			context.getExternalContext().getSessionMap().put("username", userDTO.getUsername());
			context.getExternalContext().getSessionMap().put("email", userDTO.getEmail());
			context.getExternalContext().getSessionMap().put("age", userDTO.getAge());
			context.getExternalContext().getSessionMap().put("roles", rolesOfThisUser);
			LOGGER.log(Level.INFO, "Logged user: " + userDTO.getUsername());
			return ("login");
		}
	}

	public UserDTO getUser() {
		return userDTO;
	}

	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	

}
