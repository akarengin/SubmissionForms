package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	@Autowired
	CustomerRepo repo;
	@GetMapping("/")
	public String details() {
		System.out.println("location a");
		return "edureka";
	}
	@PostMapping("/details")
	public String details(Customers customers) {
		System.out.println("location b");
		repo.save(customers);
		return "edureka";
	}
	@RequestMapping("/getdetails")
	public String getDetails() {
		System.out.println("location c");
		return "ViewCustomers";
	}

@PostMapping("/getdetails")
public ModelAndView viewDetails(@RequestParam int cid) {
		ModelAndView mv = new ModelAndView("Retrieve");
		Customers customer = repo.findById(cid).orElse(null);
		mv.addObject(customer);
		System.out.println("location ");
	return mv;						
}
@RequestMapping("/customers")
@ResponseBody
public List<Customers> getCustomers() {
	return repo.findAll();
}
@RequestMapping("/customers/{cid}")
@ResponseBody
public Optional<Customers> getCustomers2(@PathVariable("cid") int cid) {
	return repo.findById(cid);
}
@PostMapping("/customers")
public Customers getCustomers3(@RequestBody Customers customers) {
	repo.save(customers);
	return customers;
}
@DeleteMapping("/customers/{cid}")
public Customers getCustomers4(@PathVariable("cid") int cid) {
	Customers cust = repo.getOne(cid);
	repo.delete(cust);
	return cust;
}
@PutMapping(path="/customers", consumes= {"application/json"})
@ResponseBody
public Customers getCustomers5(@RequestBody Customers customers) {
	repo.save(customers);
	return customers;
}
}