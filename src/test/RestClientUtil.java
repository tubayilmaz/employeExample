import com.employ.repository.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
public class RestClientUtil {
    /*  @Test
    public void addEmployees(){
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         RestTemplate restTemplate = new RestTemplate();
         String url = "http://localhost:8080/employee";
         Employee objEmployee = new Employee();
         objEmployee.setName("Kamps");
         HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(objEmployee, headers);
         URI uri = restTemplate.postForLocation(url, requestEntity);
         System.out.println(uri.getPath());
     }
    */
    @Test
    public void getEmployeeById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employee/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee.class, 11);
        Employee employee = responseEntity.getBody();
        System.out.println("Id:"+employee.getEmployeeId()+", Name:"+employee.getName()
                +", Created on:"+employee.getCreate_Time());
    }



   // public static void main(String args[]) {
        //RestClientUtil util = new RestClientUtil();
        //util.addEmployees();
        //util.getEmployeeById();
    //}

}
