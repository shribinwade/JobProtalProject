package com.shrihari.companies.companies;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepo companyRepo;
    @Override
    public List<Company> getCompanies() {
        List<Company> allcompanies = companyRepo.findAll();
        return allcompanies;
    }

    @Override
    public boolean createCompany(Company company) {
        Company save = companyRepo.save(company);
        if(save != null){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Company getCompaniesById(Long id){
        Optional<Company> companyById = companyRepo.findById(id);
        if(companyById.isPresent()){
            return companyById.get();
        }
        return null;
    }

    @Override
    public boolean deleteCompanyById(Long id) {

        Optional<Company> byId = companyRepo.findById(id);
        if(byId.isPresent()){
            companyRepo.deleteById(id);
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> byId = companyRepo.findById(id);
        if (byId.isPresent()) {
            Company company = byId.get();
            BeanUtils.copyProperties(updateCompany, company);
            company.setId(id);
            Company save = companyRepo.save(company);
            if (save != null) {
                return true;
            }
        }
        return false;
    }
}
