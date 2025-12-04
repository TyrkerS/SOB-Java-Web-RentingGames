package deim.urv.cat.homework2.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

@Named("userForm")
@RequestScoped
public class UserForm implements Serializable {
    private static final long serialVersionUID = 1L;
        
    // JSR 303 validation
    @NotBlank
    @FormParam("nombre")
    @MvcBinding
    @Size(min=2, max=30, message = "First name must be between 2 and 30 characters")
    private String nombre;
    
    // JSR 303 validation
    @NotBlank
    @FormParam("clave")
    @MvcBinding
    @Size(min=2, max=30, message = "Password must be between 2 and 30 characters")
    private String clave;

    
    public String getNombre() {
        return fixNull(this.nombre);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return fixNull(this.clave);
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }
}
