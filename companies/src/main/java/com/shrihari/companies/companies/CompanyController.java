package com.shrihari.companies.companies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = companyService.getCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompanies(@RequestBody Company company) {
        boolean saved = companyService.createCompany(company);
        if (saved) {
            return new ResponseEntity<String>("Company Info saved", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company companiesById = companyService.getCompaniesById(id);
        return new ResponseEntity<Company>(companiesById, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCompanyById(@PathVariable Long id) {
        boolean deleteById = companyService.deleteCompanyById(id);
        if (deleteById) {
           return new ResponseEntity<String>("Company Info Deleted SuccessFully", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateCompanyById(@PathVariable Long id, @RequestBody Company company){
        boolean updated = companyService.updateCompany(id, company);
        if (updated) {
           return new ResponseEntity<String>("Company Info Updated SuccessFully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
