package net.friendface;

import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceServiceStub;
import net.friendface.friendface.service.user.UserUtils;
import net.friendface.friendface.view.beans.RegistrationBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 5/1/11
 * Time: 2:59 PM
 */

public class ParentTest {
    protected static User receiver;
    protected static User sender;
    protected static RegistrationBean registrationBean;
    protected static FriendfaceServiceStub serviceStub;

    @BeforeClass
    public static void setUp() throws Exception {
        registrationBean = new RegistrationBean();
        registrationBean.setLoginEmail("test@sender.com");
        registrationBean.setUsername("John");
        registrationBean.setUserSurname("Doe");
        registrationBean.setPassword("121212");
        registrationBean.setPasswordConfirmation("121212");

        sender = UserUtils.getUserFromBean(registrationBean);

        serviceStub = new FriendfaceServiceStub();
        serviceStub.start();
        serviceStub.getUserService().register(registrationBean);

        receiver = UserUtils.getUserFromBean(registrationBean);

        registrationBean.setLoginEmail("test@receiver.com");
        registrationBean.setUsername("Jane");
        registrationBean.setUserSurname("Roe");

        serviceStub.getUserService().register(registrationBean);
    }

    @AfterClass
    public static void tearDown() throws RepositoryException {
        serviceStub.getUserService().removeUser(sender);
        serviceStub.getUserService().removeUser(receiver);
        serviceStub.stop();
    }
}
