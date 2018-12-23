package server.com.zbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import server.com.zbm.entity.Company;
import server.com.zbm.servie.ICompanyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/company/")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;


    @RequestMapping(value = "list", produces = "text/html;charset=utf-8")
    public String list(HttpServletRequest request, Company company, Integer pageNum) {
        List<Company> companies;
        if(pageNum == null ){
            pageNum = 1;
        }
        companies = companyService.findAll(company, pageNum, 10);
        request.setAttribute("varList", companies);
        return "company/companyList";
    }
}
