package LeoValdiviaSuasnabar.ExamenFinal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirigirVeterinaria()
    {
        return "redirect:/veterinaria";
    }
}
