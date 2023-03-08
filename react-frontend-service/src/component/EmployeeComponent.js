import React, { useState, useEffect } from "react";
import EmployeeService from "../service/EmployeeService";

const EmployeeComponent = () => {
  const [employee, setEmployee] = useState({});
  const [department, setDepartment] = useState({});
  const [organization, setOrganization] = useState({});

  useEffect(() => {
    EmployeeService.getEmployee().then((res) => {
      setEmployee(res.data.employeeDto);
      setDepartment(res.data.departmentDto);
      setOrganization(res.data.organizationDto);
    });
  }, []);

  useEffect(() => {
    console.log(employee);
    console.log(department);
    console.log(organization);
  });

  return (
    <div>
      <div className="card col-md-6 offset-md-3">
        <h3 className="text-center card-header"> View Employee Details </h3>
        <div className="card-body">
            <div className="row">
                <p><strong>Employee First Name: {employee.firstName}</strong></p>
            </div>
            <div className="row">
                <p><strong>Employee Last Name: {employee.lastName}</strong></p>
            </div>
            <div className="row">
                <p><strong>Employee Email: {employee.email}</strong></p>
            </div>
        </div>
        <h3 className="text-center card-header"> View Organization Details </h3>
        <div className="card-body">
            <div className="row">
                <p><strong>Employee First Name: {organization.organizationName}</strong></p>
            </div>
            <div className="row">
                <p><strong>Employee Last Name: {organization.organizationDescription}</strong></p>
            </div>
            <div className="row">
                <p><strong>Employee Email: {organization.organizationCode}</strong></p>
            </div>
        </div>
        <h3 className="text-center card-header"> View Department Details </h3>
        <div className="card-body">
            <div className="row">
                <p><strong>Employee First Name: {department.departmentName}</strong></p>
            </div>
            <div className="row">
                <p><strong>Employee Last Name: {department.departmentDescription}</strong></p>
            </div>
            <div className="row">
                <p><strong>Employee Email: {employee.departmentCode}</strong></p>
            </div>
        </div>
      </div>
    </div>
  );
};

export default EmployeeComponent;
