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
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private Integer age;
	private String gender;
	private String password;
	
	private UserDTO userDTO;

	@PostConstruct
	public void init() {
		userDTO = new UserDTO();
	}

	public void addUser() {
		LOGGER.info("First Name entered in the form: " + firstName);
		LOGGER.info("Last Name entered in the form: " + lastName);
		LOGGER.info("Username entered in the form: " + username);
		LOGGER.info("Email entered in the form: " + email);
		LOGGER.info("Gender entered in the form: " + gender);
		LOGGER.info("Age entered in the form: " + age);
		LOGGER.info("Password entered in the form: ******************** ");

		userDTO.setFirstName(firstName);
		userDTO.setLastName(lastName);
		userDTO.setAge(age);
		userDTO.setEmail(email);
		userDTO.setPassword(Encryptor.encrypt(password, 12));
		if (gender.equals("Male")) {
			userDTO.setGender(true);
		} else if (gender.equals("Female")) {
			userDTO.setGender(false);
		} else {
			FacesContext.getCurrentInstance().addMessage("gender",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select your gender!", null));
			return;
		}
		userDTO.setUsername(username);
		userDTO.setActivated(0);
		try {
			signUpService.addUser(userDTO);
		} catch (ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage("Existing",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or email is already taken", null));
			return;
		}
		userService.makeUserStudent(userDTO);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
