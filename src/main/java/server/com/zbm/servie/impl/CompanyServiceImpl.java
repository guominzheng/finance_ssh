package server.com.zbm.servie.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.com.zbm.dao.company.CompanyDao;
import server.com.zbm.entity.Company;
import server.com.zbm.entity.PageBean.Page;
import server.com.zbm.servie.ICompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll(Company company, int begin, int pageSize) {
        Page page = new Page();
        page.setPageno(begin);
        page.setTotalpage(companyDao.getTotalCount(), pageSize);
        return companyDao.findAll(company, page.getStartrow(), page.getPagesize());
    }
}
