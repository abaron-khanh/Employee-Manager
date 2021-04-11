/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Employee;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class EmployeeDAO {
    public static List<Employee> list = new ArrayList<Employee>();

    public EmployeeDAO() {
    }

    public static List<Employee> getList() {
        return list;
    }
    
    public static boolean saveEmployee(Employee emp)
    {
        if(list.add(emp))
            return true;
        return false;
    }
    
    public static Employee findEmpById(String id)
    {
        for (Employee employee : list) 
        {
            if(employee.getEmpID().equalsIgnoreCase(id) && employee.isIsDelete()==false)
                return employee;
        }
        return null;
    }
    
    public static boolean updateEmp(Employee emp)
    {
        for (int i=0; i<list.size(); i++) 
        {
            if(list.get(i).getEmpID().equalsIgnoreCase(emp.getEmpID()))
            {
                list.set(i, emp);
                return true;
            }
        }
        return false;
    }
    
    public static boolean removeEmp(Employee emp)
    {
        for (int i=0; i<list.size(); i++)
        {
            if(list.get(i).getEmpID().equalsIgnoreCase(emp.getEmpID()))
            {
                list.set(i, emp);
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkValidate(boolean addNew,String id, String fullname, String phone, String email, String address, String dob)
    {
        if(addNew)
        {
            if(id.isEmpty() || id.length()>10)
            {
                JOptionPane.showMessageDialog(null, "ID can not be empty or more than 10 characters!!!");
                return false;
            }
            if(!id.matches("\\w{1,10}$"))
            {
                JOptionPane.showMessageDialog(null, "ID can not contain special character!!!");
                return false;
            }
            for (Employee employee : list) 
            {
                if(id.equalsIgnoreCase(employee.getEmpID()))
                {
                    JOptionPane.showMessageDialog(null, "Duplicate ID!!!");
                    return false;
                }
            }
        }
        if(fullname.isEmpty() || fullname.length()>30)
        {
            JOptionPane.showMessageDialog(null, "Fullname can not be empty or more than 30 characters!!!");
            return false;
        }
        if(!fullname.matches("^[a-zA-Z\\s]+"))
        {
            JOptionPane.showMessageDialog(null, "Fullname can not contain special character !!!");
            return false;
        }
        if(!phone.matches("^\\d{10}$"))
        {
            JOptionPane.showMessageDialog(null, "Phone must contain 10 number characters!!!");
            return false;
        }
        if(!email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$"))
        {
            JOptionPane.showMessageDialog(null, "Email does not match!!!");
            return false;
        }
        if(address.isEmpty() || fullname.length()>300)
        {
            JOptionPane.showMessageDialog(null, "Address can not be empty or more than 300 characters!!!");
            return false;
        }
        if(dob.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Date Of Birth can not be empty!!!");
            return false;
        }
        if(!dob.matches("\\d{2}[/]\\d{2}[/]\\d{4}"))
        {
            JOptionPane.showMessageDialog(null, "Date Of Birth must follow dd/MM/yyyy!!!");
            return false;
        }
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int year = Integer.parseInt(dob.substring(6, 10));
        int month = Integer.parseInt(dob.substring(3, 5));
        int day = Integer.parseInt(dob.substring(0, 2));
        if(year>=curYear)
        {
            JOptionPane.showMessageDialog(null, "Year is not valid!!!");
            return false;
        }
        if(month>12 || month==0)
        {
            JOptionPane.showMessageDialog(null, "Month is not valid!!!");
            return false;
        }
        if(day>31 || day==0)
        {
            JOptionPane.showMessageDialog(null, "Day is not valid!!!");
            return false;
        }
        if(year%4==0 && month==2 && day>29 || year%4==1 && month==2 && day>28 || month==4 && day>30 || month==6 && day>30 || month==9 && day>30 || month==11 && day>30)
        {
            JOptionPane.showMessageDialog(null, "This day does not exist!!!");
            return false;
        }
        return true;
    }
}
