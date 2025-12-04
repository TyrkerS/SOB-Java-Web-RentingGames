package deim.urv.cat.homework2.controller;
import deim.urv.cat.homework2.service.UserService;
import deim.urv.cat.homework2.model.User;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.security.CsrfProtected;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


import java.util.logging.Logger;

@Controller
@Path("SignUp")
public class SignUpFormController {

    @Inject
    BindingResult bindingResult;
    @Inject
    Logger log;
    @Inject
    UserService service;
    @Inject
    Models models;
    @Inject HttpSession session;

    @GET
    public String showForm() {
        return "signup-form.jsp"; // Injects CRSF token
    }


    @POST
    @UriRef("sign-up")
    @CsrfProtected
    public String signUp(@Valid @BeanParam UserForm userForm) {

        if (bindingResult.isFailed()) {
            models.put("errors", bindingResult.getAllErrors());
            return "signup-form.jsp";
        }
        models.put("user", userForm);

        User user = service.getUserByName(userForm.getNombre(), userForm.getClave());
        
        if (user == null) {
            models.put("message", "No hemos podido identificarte.");
            return "signup-form.jsp";

        } else {
            session.setAttribute("user", user);
            
            String redirect = (String) session.getAttribute("lastRedirect");
            session.removeAttribute("lastRedirect");
            return "redirect:"+redirect;
        }
    }
}
