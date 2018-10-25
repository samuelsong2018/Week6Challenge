package me.samuel.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    AccountList accountList;

    @RequestMapping("/")
    public String listTransactions(Model model){
        model.addAttribute("accounts", accountList.findAll());
        return "atmform";
    }

    @GetMapping("/add")
    public String listForm(Model model){
        model.addAttribute("account", new Account());
        return "depositform";
    }

    @PostMapping("/accountconfirm")
    public String processTvForm(@Valid Account account,
                                BindingResult result){
        if (result.hasErrors()){
            return "depositform";
        }
        accountList.save(account);
        return "atmform";
    }

    @RequestMapping("/deposit/{id}")
    public String deposit(@PathVariable("id") long id, Model model){
        model.addAttribute("list", accountList.findById(id).get());
        return "deposit";
    }


    @RequestMapping("/withdraw/{id}")
    public String withdraw(@PathVariable("id") long id, Model model){
        model.addAttribute("list", accountList.findById(id).get());
        return "withdraw";
    }

}