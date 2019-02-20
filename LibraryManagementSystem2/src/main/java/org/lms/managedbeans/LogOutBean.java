package org.lms.managedbeans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "logOutBean")
@ViewScoped
public class LogOutBean {
	private static final Logger LOGGER = Logger.getLogger(LogOutBean.class.getName());

	public void logout() {
		invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			LOGGER.log(Level.INFO, "Logged Out");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void invalidateSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
	}
}
