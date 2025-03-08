package edu.kirkwood.emeraldpark.model;

import java.time.LocalDate;

public class Maintenance {
    private int maintenance_id;
    private String trail_name;
    private String first_name;
    private String maintenance_type;
    private LocalDate request_date;
    private LocalDate completion_date;
    private boolean maintenance_complete;
    private String maintenance_notes;

    public Maintenance() {

    }

    public Maintenance(int maintenance_id, String trail_name, String first_name, String maintenance_type, LocalDate request_date, LocalDate completion_date, boolean maintenance_complete, String maintenance_notes) {
        this.maintenance_id = maintenance_id;
        this.trail_name = trail_name;
        this.first_name = first_name;
        this.maintenance_type = maintenance_type;
        this.request_date = request_date;
        this.completion_date = completion_date;
        this.maintenance_complete = maintenance_complete;
        this.maintenance_notes = maintenance_notes;
    }

    public String getTrail_name() {
        return trail_name;
    }

    public void setTrail_name(String trail_name) {
        this.trail_name = trail_name;
    }

    public String getAdmin_first_name() {
        return first_name;
    }

    public void setAdmin_first_name(String admin_first_name) {
        this.first_name = first_name;
    }

    public String getMaintenance_type() {
        return maintenance_type;
    }

    public void setMaintenance_type(String maintenance_type) {
        this.maintenance_type = maintenance_type;
    }

    public LocalDate getRequest_date() {
        return request_date;
    }

    public void setRequest_date(LocalDate request_date) {
        this.request_date = request_date;
    }

    public LocalDate getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(LocalDate completion_date) {
        this.completion_date = completion_date;
    }

    public boolean isMaintenance_complete() {
        return maintenance_complete;
    }

    public void setMaintenance_complete(boolean maintenance_complete) {
        this.maintenance_complete = maintenance_complete;
    }

    public String getMaintenance_notes() {
        return maintenance_notes;
    }

    public void setMaintenance_notes(String maintenance_notes) {
        this.maintenance_notes = maintenance_notes;
    }

    public int getMaintenance_id() {
        return maintenance_id;
    }

    public void setMaintenance_id(int maintenance_id) {
        this.maintenance_id = maintenance_id;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "maintenance_id=" + maintenance_id +
                ", trail_name='" + trail_name + '\'' +
                ", admin_first_name='" + first_name + '\'' +
                ", maintenance_type='" + maintenance_type + '\'' +
                ", request_date=" + request_date +
                ", completion_date=" + completion_date +
                ", maintenance_complete=" + maintenance_complete +
                ", maintenance_notes='" + maintenance_notes + '\'' +
                '}';
    }
}
