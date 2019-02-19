package org.lms.managedbeans;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.UserDTO;
import org.lms.service.SignUpService;
import org.lms.service.UserService;
import org.lms.utils.Encryptor;

@ManagedBean(name = "signUpBean")
@RequestScoped
public class SignUpBean {
	private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@ManagedProperty(value = "#{signUpService}")
	private SignUpService signUpService;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	private String gender;
	private UserDTO userDTO;

	@PostConstruct
	public void init() {
		userDTO = new UserDTO();
	}

	public String addUser() {
	/*	LOGGER.info("First Name entered in the form: " + firstName);
		LOGGER.info("Last Name entered in the form: " + lastName);
		LOGGER.info("Username entered in the form: " + username);
		LOGGER.info("Email entered in the form: " + email);
		LOGGER.info("Gender entered in the form: " + gender);
		LOGGER.info("Age entered in the form: " + age);
		LOGGER.info("Password entered in the form: ******************** "); */

		
		userDTO.setPassword(Encryptor.encrypt(userDTO.getPassword(), 12));
		if (gender.equals("Male")) {
			userDTO.setGender(true);
		} else if (gender.equals("Female")) {
			userDTO.setGender(false);
		} else {
			FacesContext.getCurrentInstance().addMessage("gender",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select your gender!", null));
			return null;
		}
		if(userDTO.getAge() < 5 || userDTO.getAge() > 120) {
			FacesContext.getCurrentInstance().addMessage("gender",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter your correct age!", null));
			return null;
		}
		userDTO.setActivated(0);
		try {
			signUpService.addUser(userDTO);
		} catch (ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage("Existing",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or email is already taken", null));
			return null;
		} catch(org.hibernate.exception.DataException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data you put was too long!", null));
			return null;
		}
		userService.makeUserStudent(userDTO);
		return "user-signed";
	}

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public SignUpService getSignUpService() {
		return signUpService;
	}

	public void setSignUpService(SignUpService signUpService) {
		this.signUpService = signUpService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
