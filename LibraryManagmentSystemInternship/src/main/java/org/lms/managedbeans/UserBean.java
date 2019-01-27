package org.lms.managedbeans;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.BookDTO;
import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.User;
import org.lms.service.ReservationService;
import org.lms.service.UserService;
import org.lms.utils.Encryptor;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{reservationService}")
	private ReservationService reservationService;
	private UserDTO userDTOLogged;
	private UserDTO userToBeActivated;
	private String username;
	private Integer userId;
	private UserDTO userDTOChanges;
	private String confirmation;
	private List<RoleDTO> rolesOfThisUser;
	
	@PostConstruct
	public void init() {
		userDTOChanges = new UserDTO();
		FacesContext context = FacesContext.getCurrentInstance();
		userDTOLogged = (UserDTO) context.getExternalContext().getSessionMap().get("user");

		username = (String) context.getExternalContext().getSessionMap().get("username");
		userId = (int) context.getExternalContext().getSessionMap().get("userId");
		rolesOfThisUser = (List<RoleDTO>)context.getExternalContext().getSessionMap().get("roles");
	}

	public UserDTO getUserDTOLogged() {
		return userDTOLogged;
	}

	public void setUserDTOLogged(UserDTO userDTOLogged) {
		this.userDTOLogged = userDTOLogged;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public UserDTO getUserToBeActivated() {
		return userToBeActivated;
	}

	public void setUserToBeActivated(UserDTO userToBeActivated) {
		this.userToBeActivated = userToBeActivated;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String passwordi(User user) {
		return Encryptor.decrypt(user.getPassword(), 12);
	}

	public List<UserDTO> listUser() {
		return userService.listUser();
	}

	public List<UserDTO> usersThatAreNotActivated() {
		return userService.findUsersThatAreNotActivated();
	}

	public String activate() {
		this.userService.activateUser(userToBeActivated);
		return "here";
	}

	public String deActivate() {
		this.userService.deActivateUser(userToBeActivated);
		return "here";
	}

	public Boolean isUserAdmin() {
		for(RoleDTO roleDTO : rolesOfThisUser) {
			if(roleDTO.getRoleName().equals("Admin")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean isUserSecretary() {
		for(RoleDTO roleDTO : rolesOfThisUser) {
			if(roleDTO.getRoleName().equals("Secretary")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean isAUserDTOAdmin(UserDTO userDTO) {
		return this.userService.isAUserAdmin(userDTO);
	}
	
	public Boolean isAUserDTOSecretary(UserDTO userDTO) {
		return this.userService.isAUserSecretary(userDTO);
	}

	public UserDTO getUserDTOChanges() {
		return userDTOChanges;
	}

	public void setUserDTOChanges(UserDTO userDTOChanges) {
		this.userDTOChanges = userDTOChanges;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String change() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (confirmation.length() == 0) {
			context.addMessage("confirmation", new FacesMessage("Please enter your current password"));
			return null;

		} else if (!Encryptor.encrypt(confirmation, 12).equals(userDTOLogged.getPassword())) {
			context.addMessage("confirmation", new FacesMessage("The confirmation password is not correct"));
			return null;
		}
		String newFirstName = userDTOChanges.getFirstName();
		String newLastName = userDTOChanges.getLastName();
		Integer newAge = userDTOChanges.getAge();
		String newEmail = userDTOChanges.getEmail();
		String newUsername = userDTOChanges.getUsername();
		String newPassword = userDTOChanges.getPassword();
		boolean checkForEncryption = false;
		boolean checkForError = false;
		if (newFirstName.length() == 0) {
			newFirstName = userDTOLogged.getFirstName();
		} else if (newFirstName.equals(userDTOLogged.getFirstName())) {
			context.addMessage("firstName", new FacesMessage("You entered the same first name as before"));
			checkForError = true;
		}
		if (newLastName.length() == 0) {
			newLastName = userDTOLogged.getLastName();
		} else if (newLastName.equals(userDTOLogged.getLastName())) {
			context.addMessage("lastName", new FacesMessage("You entered the same last name as before"));
			checkForError = true;
		}
		if (newUsername.length() == 0) {
			newUsername = userDTOLogged.getUsername();
		} else if (newUsername.equals(userDTOLogged.getUsername())) {
			context.addMessage("username", new FacesMessage("You entered the same username as before"));
			checkForError = true;
		}
		if (newAge == null) {
			newAge = userDTOLogged.getAge();
		} else if (newAge == userDTOLogged.getAge()) {
			context.addMessage("age",
					new FacesMessage("You entered the same age as before!\nYou keep getting older you know!"));
			checkForError = true;
		}
		if (newEmail.length() == 0) {
			newEmail = userDTOLogged.getEmail();
		} else if (newEmail.equals(userDTOLogged.getEmail())) {
			context.addMessage("email", new FacesMessage("You entered the same email as before"));
			checkForError = true;
		}
		if (newPassword.length() == 0) {
			newPassword = userDTOLogged.getPassword();
		} else if (Encryptor.encrypt(newPassword, 12).equals(userDTOLogged.getPassword())) {
			context.addMessage("password", new FacesMessage("You entered the same password as before"));
			checkForError = true;

		} else {
			checkForEncryption = true;
		}
		if (checkForError == true) {
			return null;
		}
		userDTOLogged.setFirstName(newFirstName);
		userDTOLogged.setLastName(newLastName);
		userDTOLogged.setUsername(newUsername);
		if (checkForEncryption == false) {
			userDTOLogged.setPassword(newPassword);
		} else {
			userDTOLogged.setPassword(Encryptor.encrypt(newPassword, 12));
		}
		userDTOLogged.setEmail(newEmail);
		userDTOLogged.setAge(newAge);
		try {
			userService.updateUser(userDTOLogged);
			username = userDTOLogged.getUsername();
		} catch (DataIntegrityViolationException e) {
			context.addMessage("Existing", new FacesMessage("Username or email are already taken"));
			userDTOLogged = (UserDTO) context.getExternalContext().getSessionMap().get("user");
			return null;
		}
		context.getExternalContext().getSessionMap().replace("user", userDTOLogged);
		context.getExternalContext().getSessionMap().replace("userId", userDTOLogged.getUserId());
		context.getExternalContext().getSessionMap().replace("firstName", userDTOLogged.getFirstName());
		context.getExternalContext().getSessionMap().replace("lastName", userDTOLogged.getLastName());
		context.getExternalContext().getSessionMap().replace("username", userDTOLogged.getUsername());
		context.getExternalContext().getSessionMap().replace("email", userDTOLogged.getEmail());
		context.getExternalContext().getSessionMap().replace("age", userDTOLogged.getAge());
		return ("changed");
	}

	public String deActivateMyAccount() {
		userService.deleteAccount(userDTOLogged);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		return ("deleted");
	}
	
	public String becomeAdmin(UserDTO userDTO) {
		this.userService.makeUserAdmin(userDTO);
		return ("header-admin"); // RREGULLOOOOOOOOOOOO
	}
	
	public String becomeSecretary(UserDTO userDTO) {
		this.userService.makeUserSecretary(userDTO);
		return ("header-admin"); // RREGULLOOOOOOOOOOOO
		
	}

	public List<RoleDTO> getRolesOfThisUser() {
		return rolesOfThisUser;
	}

	public void setRolesOfThisUser(List<RoleDTO> rolesOfThisUser) {
		this.rolesOfThisUser = rolesOfThisUser;
	}

	public ReservationService getReservationService() {
		return reservationService;
	}

	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
		
	public String reserveBook(BookDTO bookDTO) {
		reservationService.bookReservation(bookDTO, userDTOLogged);
		return "header-admin";
	}
	
	
}
