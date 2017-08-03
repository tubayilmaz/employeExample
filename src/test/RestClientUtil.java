import com.employee.repository.Employee;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
public class RestClientUtil {
     @Test
    public void addEmployees(){
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         RestTemplate restTemplate = new RestTemplate();
         String url = "http://localhost:8080/employee";
         Employee objEmployee = new Employee();
         objEmployee.setFirstName("Stein");
         objEmployee.setLastName("Becker");
         HttpEntity<Employee> requestEntity = new HttpEntity<>(objEmployee, headers);
         URI uri = restTemplate.postForLocation(url, requestEntity);
         System.out.println(uri.getPath());
     }

    @Test
    public void getEmployeeById() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employee/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee.class, 1);
        Employee employee = responseEntity.getBody();
        System.out.println("ID: "+employee.getEmployeeId()+" , Firstname: "+employee.getFirstName()+" , Lastname: " +employee.getLastName()
                +" , Created on: "+employee.getCreate_Time());
    }

    @Test
    public void getAllEmployees(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employees";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Employee[]> responseEntity= restTemplate.exchange(url, HttpMethod.GET,requestEntity,Employee[].class);
        Employee[] employees = responseEntity.getBody();

        for (Employee e:employees) {
            System.out.println("ID: " +e.getEmployeeId()+ ", Firstname: "
                    + e.getFirstName() + ", Lastname: "
                    + e.getLastName()+ " , Created on: " + e.getCreate_Time());
        }
    }

    @Test
    public void updateEmployee(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employee";
        Employee objEmployee = new Employee();
        objEmployee.setEmployeeId(1);
        objEmployee.setFirstName("Blatt");
        objEmployee.setLastName("Bäckerei");
        HttpEntity<Employee> requestEntity = new HttpEntity<>(objEmployee, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }

    @Test
    public void deleteEmployee(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employee/{id}";
        restTemplate.delete(url,22);
    }

    @Test
    public void countEmployees(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employee/count";
        HttpEntity<Employee> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Long> count= restTemplate.exchange(url, HttpMethod.GET,requestEntity, Long.class);
        System.out.println("All employees are " + count.getBody() +".");
    }
}
