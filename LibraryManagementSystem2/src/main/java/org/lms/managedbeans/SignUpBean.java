package org.lms.managedbeans;

import java.util.logging.Level;
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
			LOGGER.log(Level.WARNING, "Enter your gender");
			return null;
		}
		if(userDTO.getAge() < 5 || userDTO.getAge() > 120) {
			FacesContext.getCurrentInstance().addMessage("gender",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter your correct age!", null));
			LOGGER.log(Level.WARNING, "Enter your correct age");

			return null;
		}
		userDTO.setActivated(0);
		try {
			signUpService.addUser(userDTO);
			LOGGER.log(Level.INFO, "User added Successfully");

		} catch (ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage("Existing",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or email already exists!", null));
			LOGGER.log(Level.WARNING, "Username or email is already taken");

			return null;
		} catch(org.hibernate.exception.DataException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data you put was too long!", null));
			LOGGER.log(Level.WARNING, "Data you put was too long");

			return null;
		}
		userService.makeUserStudent(userDTO);
		LOGGER.log(Level.INFO, "User was set to be a student");
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
