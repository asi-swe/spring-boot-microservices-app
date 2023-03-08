import axios from "axios";

const EMPLOYEE_SERVICE_URL = "http://localhost:9090/api/employees";

const EMPLOYEE_ID = 1;

class Employeeservice {
  getEmployee() {
    return axios.get(EMPLOYEE_SERVICE_URL + "/" + EMPLOYEE_ID);
  }
}


export default new Employeeservice();