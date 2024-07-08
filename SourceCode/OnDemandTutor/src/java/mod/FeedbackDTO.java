package mod;

/**
 *
 * @author ADMIN
 */
public class FeedbackDTO {
    private int accountId;// accountId of modeId
    private int modId;
    private int studentId;
    private int feedbackId;
    private String studentName;
    private int tutorId;
    private String tutorName;
    private String feedBackText;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int accountId, int modId, int studentId, int feedbackId, String studentName, int tutorId, String tutorName, String feedBackText) {
        this.accountId = accountId;
        this.modId = modId;
        this.studentId = studentId;
        this.feedbackId = feedbackId;
        this.studentName = studentName;
        this.tutorId = tutorId;
        this.tutorName = tutorName;
        this.feedBackText = feedBackText;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getFeedBackText() {
        return feedBackText;
    }

    public void setFeedBackText(String feedBackText) {
        this.feedBackText = feedBackText;
    }
}

