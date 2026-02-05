package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class vehicleController {
  @GetMapping("/showVehicle")
  public String showVehicle(Model model) {
    model.addAttribute("vroom", new vehicle());
    return "vehicleview";
  }

  @PostMapping("/showVehicle")
  public String submitForm(@ModelAttribute vehicle vroom, Model model) {
    model.addAttribute("car", vroom);
    // System.out.println(car.getPlate());
    return "result2";
  }
}
