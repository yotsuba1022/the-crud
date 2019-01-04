package idv.clu.the.crud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Carl Lu
 * <p>
 * <p>
 * This controller is used for customized the swagger ui URL since at current time,
 * the official lib of swagger ui didn't support the functionality of customizing the url.
 */
@Controller
public class SwaggerController {

    @RequestMapping("/docs")
    public String swaggerHome() {
        return "redirect:/swagger-ui.html";
    }

}
