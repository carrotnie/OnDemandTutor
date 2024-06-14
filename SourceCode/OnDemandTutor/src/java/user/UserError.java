/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author MSI
 */
public class UserError {
    private int IdError;
    private String NameError;
    private String UsernameError;
    private String PasswordError;
    private String RoleError;
    private String ConfirmError;

    public UserError() {
        this.IdError = 0;
        this.NameError = "";
        this.UsernameError = "";
        this.PasswordError = "";
        this.RoleError = "";
        this.ConfirmError = "";
    }

    public UserError(int IdError, String NameError, String UsernameError, String PasswordError, String RoleError, String ConfirmError) {
        this.IdError = IdError;
        this.NameError = NameError;
        this.UsernameError = UsernameError;
        this.PasswordError = PasswordError;
        this.RoleError = RoleError;
        this.ConfirmError = ConfirmError;
    }

    public int getIdError() {
        return IdError;
    }

    public void setIdError(int IdError) {
        this.IdError = IdError;
    }

    public String getNameError() {
        return NameError;
    }

    public void setNameError(String NameError) {
        this.NameError = NameError;
    }

    public String getUsernameError() {
        return UsernameError;
    }

    public void setUsernameError(String UsernameError) {
        this.UsernameError = UsernameError;
    }

    public String getPasswordError() {
        return PasswordError;
    }

    public void setPasswordError(String PasswordError) {
        this.PasswordError = PasswordError;
    }

    public String getRoleError() {
        return RoleError;
    }

    public void setRoleError(String RoleError) {
        this.RoleError = RoleError;
    }

    public String getConfirmError() {
        return ConfirmError;
    }

    public void setConfirmError(String ConfirmError) {
        this.ConfirmError = ConfirmError;
    }
    
    
    
}
