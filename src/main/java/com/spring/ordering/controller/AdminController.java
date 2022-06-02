package com.spring.ordering.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.spring.ordering.dao.ProductDAO;
import com.spring.ordering.entity.Account;
import org.apache.commons.lang.exception.ExceptionUtils;
import com.spring.ordering.dao.OrderDAO;
import com.spring.ordering.entity.Product;
import com.spring.ordering.form.ProductForm;
import com.spring.ordering.model.OrderDetailInfo;
import com.spring.ordering.model.OrderInfo;
import com.spring.ordering.pagination.PaginationResult;
import com.spring.ordering.validator.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
public class AdminController {

    @Autowired
    private OrderDAO orderDAO;

    private  Connection connection;
    private Statement statement;
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductFormValidator productFormValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == ProductForm.class) {
            dataBinder.setValidator(productFormValidator);
        }
    }

    @RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
    public String login(Model model) {

        return "login";
    }

    @RequestMapping(value = { "/admin/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());

        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }

    @RequestMapping(value = { "/admin/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, //
                            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;

        PaginationResult<OrderInfo> paginationResult //
                = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }


    @RequestMapping(value = { "/admin/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductForm productForm = null;

        if (code != null && code.length() > 0) {
            Product product = productDAO.findProduct(code);
            if (product != null) {
                productForm = new ProductForm(product);
            }
        }
        if (productForm == null) {
            productForm = new ProductForm();
            productForm.setNewProduct(true);
        }
        model.addAttribute("productForm", productForm);
        return "product";
    }


    @RequestMapping(value = { "/admin/product" }, method = RequestMethod.POST)
    public String productSave(Model model, //
                              @ModelAttribute("productForm") @Validated ProductForm productForm, //
                              BindingResult result, //
                              final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "product";
        }
        try {
            productDAO.save(productForm);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            String message = rootCause.getMessage();
            model.addAttribute("errorMessage", message);
            return "product";
        }

        return "redirect:/productList";
    }

    @GetMapping("/admin/register")
    public String registerForm(Model model) {
        model.addAttribute("user",new Account());
        return "registerForm";
    }
    @PostMapping("/admin/save")
    public String register(@ModelAttribute Account account) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/ordering-web-app";
        String username = "postgres";
        String password = "e17182002_";
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            connection = DriverManager.getConnection(jdbcURL,username,password);
            String inserting= String.format("INSERT INTO accounts values ('%s',true,'%s','ROLE_EMPLOYEE')",account.getUserName(),bCryptPasswordEncoder.encode(account.getEncrytedPassword()));
            statement = connection.createStatement();
            statement.executeUpdate(inserting);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return "success";
    }

    @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/admin/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);

        model.addAttribute("orderInfo", orderInfo);

        return "order";
    }

}
