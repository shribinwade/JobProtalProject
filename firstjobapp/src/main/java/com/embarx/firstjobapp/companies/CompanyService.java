package com.embarx.firstjobapp.companies;

import java.util.List;

public interface CompanyService {

    public List<Company> getCompanies();

    public boolean createCompany(Company company);

    public Company getCompaniesById(Long id);

    boolean deleteCompanyById(Long id);

    boolean updateCompany(Long id, Company updateCompany);
}
