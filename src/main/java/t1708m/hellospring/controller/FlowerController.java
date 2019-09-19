package t1708m.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708m.hellospring.entity.Flower;
import t1708m.hellospring.service.FlowerService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/flowers")
public class FlowerController {
    @Autowired
    FlowerService flowerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("flowers", flowerService.getList(1, 10));
        return "admin/flowers/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getDetail(@PathVariable int id, Model model) {
        model.addAttribute("flower", flowerService.getDetail(id));
        return "admin/flowers/detail";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("flower", new Flower());
        return "admin/flowers/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String store(@Valid Flower flower, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/flowers/form";
        }
        flowerService.create(flower);
        return "redirect:/admin/flowers";
    }
}
