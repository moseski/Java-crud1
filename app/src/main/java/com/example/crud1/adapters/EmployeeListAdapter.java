package com.example.crud1.adapters;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;
        import com.example.crud1.Employee;
        import com.example.crud1.R;

        import java.util.List;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {
    private List<Employee> employees;

    public EmployeeListAdapter(Context context, List<Employee> employees) {
        super(context, R.layout.employee_list, employees);
        this.employees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.employee_list, parent, false);
        }

        // Populate the views with data from the employee object
        Employee employee = employees.get(position);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_address = convertView.findViewById(R.id.tv_address);

        tv_name.setText(employee.getFirstname() + " " + employee.getLastname());
        tv_address.setText(employee.getAddress());

        return convertView;
    }
}
