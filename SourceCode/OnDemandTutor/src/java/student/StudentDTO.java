package student;

/**
 *
 * @author ADMIN
 */
public class StudentDTO {

    private int Id;
    private int AccountId;
    private int Yob; // năm sinh
    private String Location; // địa chỉ cụ thể
    private String Gender;
    private int Grade;
    private String PhoneNumber;
    private String Name; // Tên của học sinh từ bảng Account
    private String Username;

    public StudentDTO() {
        this.Id = 0;
        this.AccountId = 0;
        this.Yob = 0;
        this.Location = "";
        this.Gender = "";
        this.Grade = 0;
        this.PhoneNumber = "";
        this.Name = "";
    }

    public StudentDTO(int Id, int AccountId, int Yob, String Location, String Gender, int Grade, String PhoneNumber) {
        this.Id = Id;
        this.AccountId = AccountId;
        this.Yob = Yob;
        this.Location = Location;
        this.Gender = Gender;
        this.Grade = Grade;
        this.PhoneNumber = PhoneNumber;
        this.Name = "";
    }

    //View stu info from schedule

    public StudentDTO(int AccountId, String Location, String PhoneNumber, String Name, String Username) {
        this.AccountId = AccountId;
        this.Location = Location;
        this.PhoneNumber = PhoneNumber;
        this.Name = Name;
        this.Username = Username;
    }

    
    

    public StudentDTO(int Id, int AccountId) {
        this.Id = Id;
        this.AccountId = AccountId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public int getYob() {
        return Yob;
    }

    public void setYob(int Yob) {
        this.Yob = Yob;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
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
    
}
