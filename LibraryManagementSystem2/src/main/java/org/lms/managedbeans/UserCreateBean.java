package org.lms.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.service.RoleService;
import org.lms.service.SignUpService;
import org.lms.service.UserService;
import org.lms.utils.Encryptor;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "userCreateBean")
@ViewScoped
public class UserCreateBean {
	private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());

	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{roleService}")
	private RoleService roleService;
	@ManagedProperty(value = "#{signUpService}")
	private SignUpService signUpService;
	private UserDTO userDTO;

	private DualListModel<String> roles;
	private String gender;

	@PostConstruct
	public void init() {
		List<String> rolesSource = roleService.getAllRolesAsString();
		List<String> rolesTarget = new ArrayList<>();
		roles = new DualListModel<>(rolesSource, rolesTarget);
		userDTO = new UserDTO();
	}

	public void addUser() {

		LOGGER.info("First Name entered in the form: " + userDTO.getFirstName());
		LOGGER.info("Last Name entered in the form: " + userDTO.getLastName());
		LOGGER.info("Username entered in the form: " + userDTO.getUsername());
		LOGGER.info("Email entered in the form: " + userDTO.getEmail());
		LOGGER.info("Gender entered in the form: " + gender);
		LOGGER.info("Age entered in the form: " + userDTO.getAge());
		LOGGER.info("Password entered in the form: ******************** ");

		userDTO.setPassword(Encryptor.encrypt(userDTO.getPassword(), 12));
		if (gender.equals("Male")) {
			userDTO.setGender(true);
		} else if (gender.equals("Female")) {
			userDTO.setGender(false);
		} else {
			FacesContext.getCurrentInstance().addMessage("gender",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select your gender!", null));
			return;
		}
		if (userDTO.getAge() < 5 || userDTO.getAge() > 120) {
			FacesContext.getCurrentInstance().addMessage("gender",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter your correct age!", null));
			return;
		}

		if (roles.getTarget().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select at least one role!", null));
			return;
		}
		userDTO.setActivated(1);
		try {
			signUpService.addUser(userDTO);
			userService.setRolesToUserViaListString(roles.getTarget(), userDTO);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"User " + userDTO.getUsername() + " was registered successfully!", null));
			LOGGER.log(Level.INFO, "User Created");
			return;
		} catch (ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage("Existing",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or email is already taken", null));
			LOGGER.log(Level.WARNING, "User Not Created");
			return;
		} catch (org.hibernate.exception.DataException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data you put was too long!", null));
			LOGGER.log(Level.WARNING, "User Not Created");

			return;
		}

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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public SignUpService getSignUpService() {
		return signUpService;
	}

	public void setSignUpService(SignUpService signUpService) {
		this.signUpService = signUpService;
	}

	public DualListModel<String> getRoles() {
		return roles;
	}

	public void setRoles(DualListModel<String> roles) {
		this.roles = roles;
	}

}
