package BTOManagementSystem;

public class Enquiry {
    private String content;
    private String reply;
    private Applicant sender;
    private Project project;

    public Enquiry(Applicant sender, String content) {
        this.sender = sender;
        this.content = content;
        this.reply = null;
    }

    public String getContent() {
        return content;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public Applicant getSender() {
        return sender;
    }

    public Project getProject() {
        return project;
    }
}
