package mod;

/**
 *
 * @author ADMIN
 */
public class ReasonDenyCvDTO {
    private int modId;
    private int tutorId;
    private String reason;

    public ReasonDenyCvDTO() {
    }

    public ReasonDenyCvDTO(int modId, int tutorId, String reason) {
        this.modId = modId;
        this.tutorId = tutorId;
        this.reason = reason;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    
}
