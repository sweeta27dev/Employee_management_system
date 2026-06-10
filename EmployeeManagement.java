import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;         
import java.time.format.DateTimeFormatter;  

public class EmployeeManagement {

    static ArrayList<Employee> empList = new ArrayList<>();

    public static void addEmp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee Id:");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Employee Name:");
        String empName = sc.nextLine();
        System.out.println("Enter Employee Salary:");
        double empSal = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter Employee Department:");
        String department = sc.nextLine();

        System.out.println("Enter Joining Date (dd-MM-yyyy):");
        String dateInput = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate joiningDate = LocalDate.parse(dateInput, formatter);

        Employee emp = new Employee(empId, empName, empSal, department, joiningDate);
        empList.add(emp);
        System.out.println("Employee Added Successfully");
    }

    public static void searchEmp() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Employee Id to Search:");
            int searchId = sc.nextInt();
            boolean found = false;
            for (Employee emp : empList) {
                if (emp.empId == searchId) {
                    System.out.println("\nEmployee Found");
                    System.out.println("ID: " + emp.empId);
                    System.out.println("Name: " + emp.empName);
                    System.out.println("Salary: " + emp.empSal);
                    System.out.println("Department: " + emp.department);
                    System.out.println("Joining Date: " + emp.joiningDate);  
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateSalary() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Employee Id to Update Salary:");
            int searchId = sc.nextInt();
            boolean found = false;
            for (Employee emp : empList) {
                if (emp.empId == searchId) {
                    System.out.println("Current Salary: " + emp.empSal);
                    System.out.println("Enter New Salary:");
                    double newSalary = sc.nextDouble();
                    emp.empSal = newSalary;
                    System.out.println("Salary Updated Successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeEmp() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Employee Id to Remove:");
            int id = sc.nextInt();
            boolean found = false;
            Iterator<Employee> it = empList.iterator();
            while (it.hasNext()) {
                Employee emp = it.next();
                if (emp.empId == id) {
                    it.remove();
                    System.out.println("Employee Removed Successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayAllEmp() {
        if (empList.isEmpty()) {
            System.out.println("No Employees Found");
            return;
        }
        System.out.println("\n===== EMPLOYEE LIST =====");
        for (Employee emp : empList) {
            System.out.println("ID: " + emp.empId);
            System.out.println("Name: " + emp.empName);
            System.out.println("Salary: " + emp.empSal);
            System.out.println("Department: " + emp.department);
            System.out.println("Joining Date: " + emp.joiningDate);  
            System.out.println("----------------------");
        }
    }

    public static void groupByDepartment() {
        if (empList.isEmpty()) {
            System.out.println("No Employees Found");
            return;
        }
        ArrayList<String> departments = new ArrayList<>();
        for (Employee emp : empList) {
            if (!departments.contains(emp.department)) {
                departments.add(emp.department);
            }
        }
        for (String dept : departments) {
            System.out.println("\nDepartment: " + dept);
            for (Employee emp : empList) {
                if (emp.department.equalsIgnoreCase(dept)) {
                    System.out.println("ID: " + emp.empId);
                    System.out.println("Name: " + emp.empName);
                    System.out.println("Salary: " + emp.empSal);
                    System.out.println("Joining Date: " + emp.joiningDate);  
                    System.out.println("----------------------");
                }
            }
        }
    }

    public static void sortById() {
        if (empList.isEmpty()) {
            System.out.println("No Employees Found");
            return;
        }
        Collections.sort(empList, Comparator.comparingInt(emp -> emp.empId));
        System.out.println("Employees Sorted By ID Successfully");
        System.out.println("\n===== SORTED EMPLOYEE LIST =====");
        for (Employee emp : empList) {
            System.out.println("ID: " + emp.empId);
            System.out.println("Name: " + emp.empName);
            System.out.println("Salary: " + emp.empSal);
            System.out.println("Department: " + emp.department);
            System.out.println("Joining Date: " + emp.joiningDate);  
            System.out.println("----------------------");
        }
    }

    public static void sortBySalary() {
        if (empList.isEmpty()) {
            System.out.println("No Employees Found");
            return;
        }
        Collections.sort(empList, Comparator.comparingDouble(emp -> emp.empSal));
        System.out.println("Employees Sorted By Salary Successfully");
        System.out.println("\n===== SORTED EMPLOYEE LIST =====");
        for (Employee emp : empList) {
            System.out.println("ID: " + emp.empId);
            System.out.println("Name: " + emp.empName);
            System.out.println("Salary: " + emp.empSal);
            System.out.println("Department: " + emp.department);
            System.out.println("Joining Date: " + emp.joiningDate);  
            System.out.println("----------------------");
        }
    }

    public static void findHighestPaidEmp() {
        if (empList.isEmpty()) {
            System.out.println("No Employees Found");
            return;
        }
        Employee highestPaid = Collections.max(empList, Comparator.comparingDouble(emp -> emp.empSal));
        System.out.println("\n===== HIGHEST PAID EMPLOYEE =====");
        System.out.println("ID: " + highestPaid.empId);
        System.out.println("Name: " + highestPaid.empName);
        System.out.println("Salary: " + highestPaid.empSal);
        System.out.println("Department: " + highestPaid.department);
        System.out.println("Joining Date: " + highestPaid.joiningDate);  
        System.out.println("----------------------");
    }

    public static void countByDepartment() {
        if (empList.isEmpty()) {
            System.out.println("No Employees Found");
            return;
        }
        ArrayList<String> departments = new ArrayList<>();
        for (Employee emp : empList) {
            if (!departments.contains(emp.department)) {
                departments.add(emp.department);
            }
        }
        System.out.println("\n===== DEPARTMENT-WISE EMPLOYEE COUNT =====");
        for (String dept : departments) {
            int count = 0;
            for (Employee emp : empList) {
                if (emp.department.equalsIgnoreCase(dept)) {
                    count++;
                }
            }
            System.out.println("Department: " + dept + " --> Count: " + count);
        }
        System.out.println("------------------------------------------");
    }

    
  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1.  Add Employee");
            System.out.println("2.  Search Employee");
            System.out.println("3.  Display All Employees");
            System.out.println("4.  Update Salary");
            System.out.println("5.  Remove Employee");
            System.out.println("6.  Group Employees by Department");
            System.out.println("7.  Sort Employees By ID");
            System.out.println("8.  Sort Employees By Salary");
            System.out.println("9.  Find Highest Paid Employee");
            System.out.println("10. Count Employee Department-Wise");
            System.out.println("11. Exit");  
            

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmp();
                    break;
                case 2:
                    searchEmp();
                    break;
                case 3:
                    displayAllEmp();
                    break;
                case 4:
                    updateSalary();
                    break;
                case 5:
                    removeEmp();
                    break;
                case 6:
                    groupByDepartment();
                    break;
                case 7:
                    sortById();
                    break;
                case 8:
                    sortBySalary();
                    break;
                case 9:
                    findHighestPaidEmp();
                    break;
                case 10:
                    countByDepartment();
                    break;
                case 11:
                    System.out.println("Thank You...");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 11);

        sc.close();
    }
}


class Employee {
    int empId;
    String empName;
    double empSal;
    String department;
    LocalDate joiningDate;  

    Employee(int empId, String empName, double empSal, String department, LocalDate joiningDate) {
        this.empId = empId;
        this.empName = empName;
        this.empSal = empSal;
        this.department = department;
        this.joiningDate = joiningDate;  
    }
}

class EmployeeNotFoundException extends Exception {
    EmployeeNotFoundException(String message) {
        super(message);
    }
}