package com.training.platform.controllers;

import com.training.platform.entities.User;
import com.training.platform.repositories.UserRepository;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    //select* from table
   /* public String index() {
        List<User> users = userRepository.findAll();
        System.out.println("############### Find All User (In Console) ###############");
        System.out.println(users);
        return "Method GET, Function : index => SHOW data list on page";

    }*/
    
    public List<User> index() {
        //select*from table where f1 = '' and f2=''
        //List<User> users = userRepository.findByCityAndActiveAndAge("nakornpathom", 1, 18);

        //select * from table where f1 in('')
        /*List<Integer> ages = new ArrayList<Integer>(Arrays.asList(18, 19, 22) );
        List<User> users = userRepository.findByAgeIn(ages);
        return users;*/
        //use by userService
        // Change from UserRepository to UserService
        return userService.findAllByJpqlParamsQuery(0, "bangkok");


    }
    @GetMapping(value = "/ser1")
    public List<User> ser1(@RequestParam String active,@RequestParam String city) {
        return userService.findAllByParamsQuery(Integer.parseInt(active),city);
    }//run on post man--> http://localhost:8080/demo/ser1?active=1&city=nakornpathom
    @GetMapping(value = "/ser2")
    public List<User> ser2() {
        return userService.findAllByJpqlQuery();
    }//run on post man--> http://localhost:8080/demo/ser2
    @GetMapping(value = "/ser3")
    public List<User> ser3(@RequestParam String active,@RequestParam String city) {
        return userService.findAllByJpqlParamsQuery(Integer.parseInt(active),city);
    }//run on post man---> http://localhost:8080/demo/ser3?active=1&city=nakornpathom
    @GetMapping(value = "/ser4")
    public List<User> ser4() {
        return userService.findAll();
    }
    @GetMapping(value = "/ser5")
    public List<User> ser5() {
        return userService.findAllByQuery();
    }
    @GetMapping(value = "/ser6")
    public List<User> ser6(@RequestParam(name="age") List<Integer> listAge) {
        return userService.findByAgeIn(listAge);
    }
    @GetMapping(value = "/ser6.2")
    public List<User> getByAgewithBody(@RequestBody Map<String,Object> input) {
        return userService.findByAgeIn((List<Integer>)input.get("age"));
    }
    @GetMapping(value = "/ser7")
    public List<User> ser7(@RequestParam String city,@RequestParam String active,@RequestParam String age) {
        return userService.findByCityAndActiveAndAge(city,Integer.parseInt(active),Integer.parseInt(age));
    }
    @GetMapping(value = "/ser8")
    public Page<User> ser8(@RequestParam String start, @RequestParam String limit, @RequestParam String field) {
        return userService.findAllByLimit(Integer.parseInt(start),Integer.parseInt(limit),field);
    }
    @GetMapping(value = "/ser9/{id}")
    public Optional<User> ser9(@PathVariable String id) {
        return userService.findById(Integer.parseInt(id));
    }

    //2 methods from userRepository
    @GetMapping(value = "/repo_city")
    public List<User> repoCity(@RequestParam(name="city") List<String> listCity) {
        return userRepository.findByCityIn(listCity);
    }
    @GetMapping(value = "/repo_min_age")
    public String repoMinAge() {
        return "Minimum of age is " + userRepository.findMinAge();
    }
    //2 methods from userService
    @GetMapping(value = "/ser_city")
    public List<User> serCity(@RequestParam(name="city") List<String> listCity) {
        return userService.findByCityIn(listCity);
    }
    @GetMapping(value = "/ser_min_age")
    public String serMinAge() {
        return "Minimum of age is " +userService.findMinAge();
    }



//findByPrimary
    /*@GetMapping(value = "/{id}")
    public String showWithPath(@PathVariable String id) {
        Optional<User> user = userRepository.findById(Integer.parseInt(id));
        System.out.println("############### Find User By ID (In Console) ###############");
        System.out.println(user);

        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with path";
    }*/
    //Native Query
    // Example for findAllByQuery
    /*@GetMapping(value = "/test1")
    public List<User> test1() {
        return userRepository.findAllByQuery();
    }*/
    // Example for findAllByParamsQuery
    /*@GetMapping(value = "/test2")
    public List<User> test2() {
        return userRepository.findAllByParamsQuery(0, "nakornpathom");
    }*/
    /*@GetMapping(value = "/test21")
    public List<User> test21(@RequestParam String active,@RequestParam String city) {
        return userRepository.findAllByParamsQuery(Integer.parseInt(active),city);
    }*/
    //JPQL
    // Example for findAllByJpqlQuery
   /* @GetMapping(value = "/test3")
    public List<User> test3() {
        return userRepository.findAllByJpqlQuery();
    }*/

    // Example for findAllByJpqlParamsQuery
   /* @GetMapping(value = "/test4")
    public List<User> test4() {
        return userRepository.findAllByJpqlParamsQuery(0, "bangkok");
    }*/



//17April
//    @GetMapping(value = "")
//    public String index() {
//        return "Method GET, Function : index => SHOW data list on page";
//    }

/*@RequestMapping(value = "/healthcheck")
@ResponseStatus(code = HttpStatus.ACCEPTED)//first method: add ResponseStatus and code=202
//public String healthCheck() {
    //return "Hello KitKat!";
//}
//method 2: ทำผ่าน HttpServletResponse
  public String healthCheck(HttpServletResponse response) {
     response.setStatus(HttpServletResponse.SC_NOT_FOUND);
     return "Hello Gorgi!";
  }


    @GetMapping(value = "")
    public String showWithParam(@RequestParam String id) {
        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with query string";
    }*/

   /* @PostMapping(value = "")
    public String create(@RequestBody Map<String,Object> inputs) {
        System.out.println("########### POST Param ###########");
        System.out.println(inputs);

        return "Method POST, Function : create => INSERT data to DB";
    }

    @GetMapping(value = "/{id}")
    public String showWithPath(@PathVariable String id) {
        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with path";
    }

    @PatchMapping(value = "/{id}")
    public String update(@PathVariable String id, @RequestParam Map<String,String> inputs) {
        System.out.println("########### PATCH Param ###########");
        System.out.println(inputs);

        return "Method PATCH, Function : update => ID : " + id + " UPDATE data to DB";
    }

    @DeleteMapping(value = "/{id}")
    public String destroy(@PathVariable String id)  {
        return "Method DELETE, Function : delete, ID : " + id + " => DELETE data to DB";
    }*/
}

