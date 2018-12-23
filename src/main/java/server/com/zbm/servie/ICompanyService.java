package server.com.zbm.servie;

import server.com.zbm.entity.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> findAll(Company company, int begin, int pageSize);
}
