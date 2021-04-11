/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class EmployeeTableModel <E> extends AbstractTableModel{
    String[] header;
    int[] indexes;
    Vector<Employee> employeeData;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    public EmployeeTableModel(String[] header, int[] indexes) 
    {
        int i=0;
        this.header = new String[header.length];
        for (i = 0; i < header.length; i++) 
        {
            this.header[i]=header[i];
        }
        this.indexes = new int[indexes.length];
        for(i=0; i<header.length; i++)
        {
            this.indexes[i]=indexes[i];
        }
        this.employeeData = new Vector<Employee>();
    }

    public Vector<Employee> getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(Vector<Employee> employeeData) {
        this.employeeData = employeeData;
    }

    @Override
    public int getRowCount() {
        return employeeData.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return (column>=0 && column<header.length)?header[column]:"";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex<0 || rowIndex>= employeeData.size() || columnIndex<0 || columnIndex>=header.length)
            return null;
        Employee emp = employeeData.get(rowIndex);
        switch(indexes[columnIndex])
        {
            case 0: return emp.getEmpID();
            case 1: return emp.getFullname();
            case 2: return emp.getPhone();
            case 3: return emp.getEmail();
            case 4: return emp.getAddress();
            case 5: return format.format(emp.getDateOfBirth());
            case 6: return emp.isIsDelete();
        }
        return null;
    }
}
