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
public class UserDTO {
    private int Id;
    private String Name;
    private String Username;
    private String Password;
    private String Role;

    public UserDTO() {
        this.Id=0;
        this.Name="";
        this.Username="";
        this.Password="";
        this.Role="";
    }

    public UserDTO(int Id, String Name, String Username, String Password, String Role) {
        this.Id = Id;
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    
    
    
}
