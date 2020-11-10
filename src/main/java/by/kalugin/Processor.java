package by.kalugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Processor {
    enum RequestNumber {
        BY_SHORT_NAME(1),
        BY_DEPARTMENT(2),
        BY_ACTIVITY(3),
        BY_FOUNDATION_DATE(4),
        BY_EMPLOYEE_NUMBER(5),
        DEFAULT(0);

        private final int value;

        RequestNumber(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }

        public static RequestNumber getTypeFromInt(int value) {
            for (RequestNumber type : values()) {
                if (type.value == value) return type;
            }
            return RequestNumber.DEFAULT;
        }
    }

    static List<Company> findByShortName(List<Company> companyList, String shortName) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getShortName().equalsIgnoreCase(shortName)) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findByDepartment(List<Company> companyList, String industry) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getDepartment().equalsIgnoreCase(industry)) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findByActivity(List<Company> companyList, String activity) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getActivityKind().equalsIgnoreCase(activity)) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findByFoundationDate(List<Company> companyList, Date startDate, Date endDate) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getFoundationDate().compareTo(startDate) >= 0 && company.getFoundationDate().compareTo(endDate) <= 0) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findByEmployeeNumber(List<Company> companyList, int minNumber, int maxNumber) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getEmployeeNumber() >= minNumber && company.getEmployeeNumber() <= maxNumber) {
                result.add(company);
            }
        }
        return result;
    }
}