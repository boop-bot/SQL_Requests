package by.kalugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Company {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private String fullName;
    private String shortName;
    private Date actualizationDate;
    private String address;
    private Date foundationDate;
    private int employeeNumber;
    private String auditor;
    private String phoneNumber;
    private String email;
    private String department;
    private String activityKind;
    private String url;

    public Company(String fullName, String shortName, Date actualizationDate, String address, Date foundationDate, int employeeNumber, String auditor, String phoneNumber, String email, String department, String activityKind, String url) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.actualizationDate = actualizationDate;
        this.address = address;
        this.foundationDate = foundationDate;
        this.employeeNumber = employeeNumber;
        this.auditor = auditor;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        this.activityKind = activityKind;
        this.url = url;
    }

    Company(String[] args) throws ParseException, IllegalArgumentException {
        if (args.length != 12) {
            throw new IllegalArgumentException("wrong DATA used");
        }
        this.fullName = args[0];
        this.shortName = args[1];
        this.actualizationDate = dateFormat.parse(args[2]);
        this.address = args[3];
        this.foundationDate = dateFormat.parse(args[4]);
        this.employeeNumber = Integer.parseInt(args[5]);
        this.auditor = args[6];
        this.phoneNumber = args[7];
        this.email = args[8];
        this.department = args[9];
        this.activityKind = args[10];
        this.url = args[11];
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Date getActualizationDate() {
        return actualizationDate;
    }

    public void setActualizationDate(Date actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getActivityKind() {
        return activityKind;
    }

    public void setActivityKind(String activityKind) {
        this.activityKind = activityKind;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return fullName + ";" + shortName + ";" + actualizationDate.toString() + ";"
                + address + ";" + foundationDate.toString() + ";" + employeeNumber + ";"
                + auditor + ";" + phoneNumber + ";" + email + ";" + department + ";" + activityKind + ";"
                + url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (employeeNumber != company.employeeNumber) return false;
        if (!fullName.equals(company.fullName)) return false;
        if (!shortName.equals(company.shortName)) return false;
        if (!actualizationDate.equals(company.actualizationDate)) return false;
        if (!address.equals(company.address)) return false;
        if (!foundationDate.equals(company.foundationDate)) return false;
        if (!auditor.equals(company.auditor)) return false;
        if (!phoneNumber.equals(company.phoneNumber)) return false;
        if (!email.equals(company.email)) return false;
        if (!department.equals(company.department)) return false;
        if (!activityKind.equals(company.activityKind)) return false;
        return url.equals(company.url);
    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + actualizationDate.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + foundationDate.hashCode();
        result = 31 * result + employeeNumber;
        result = 31 * result + auditor.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + activityKind.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}