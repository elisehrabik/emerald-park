package edu.kirkwood.emeraldpark.model;

public class Visitor {
    private int visitor_id;
    private String visitor_first_name;
    private String visitor_last_name;
    private String visitor_email;

    public Visitor() {

    }

    public Visitor(int visitor_id, String visitor_first_name, String visitor_last_name, String visitor_email) {
        this.visitor_id = visitor_id;
        this.visitor_first_name = visitor_first_name;
        this.visitor_last_name = visitor_last_name;
        this.visitor_email = visitor_email;
    }

    public String getVisitor_first_name() {
        return visitor_first_name;
    }

    public void setVisitor_first_name(String visitor_first_name) {
        this.visitor_first_name = visitor_first_name;
    }

    public String getVisitor_last_name() {
        return visitor_last_name;
    }

    public void setVisitor_last_name(String visitor_last_name) {
        this.visitor_last_name = visitor_last_name;
    }

    public String getVisitor_email() {
        return visitor_email;
    }

    public void setVisitor_email(String visitor_email) {
        this.visitor_email = visitor_email;
    }

    public int getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(int visitor_id) {
        this.visitor_id = visitor_id;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitor_id=" + visitor_id +
                ", visitor_first_name='" + visitor_first_name + '\'' +
                ", visitor_last_name='" + visitor_last_name + '\'' +
                ", visitor_email='" + visitor_email + '\'' +
                '}';
    }
}
