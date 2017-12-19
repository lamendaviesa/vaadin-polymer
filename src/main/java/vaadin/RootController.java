package vaadin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RootController {

	/**
	 *  A basic way to prove that Spring right boot 
	 * @return text
	 */
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello! A basic way to prove that Spring right boot.";
	}
	
	/**
	 *  A basic way to prove that Spring show a template html
	 * @return jsp_example.html
	 */
	@RequestMapping("/jspExample")
	public ModelAndView jspExample() {
		Map<String, Object> model = new HashMap<>();
		return new ModelAndView("jsp_example", model);
	}
	
	/**
	 *  A basic way to prove that Spring show a template html with Polymer
	 * @return app_polymer.html
	 */
	@RequestMapping("/appPolymer")
	public ModelAndView appPolymer() {
		Map<String, Object> model = new HashMap<>();
		return new ModelAndView("app_polymer", model);
	}

	/**
	 *  A basic way to prove that Spring show a template html with Polymer and an iframe. An iframe is includes with Vaadin. 
	 * @return app_polymer.html
	 */
	@RequestMapping("/polymerIframe")
	public ModelAndView polymerIframe() {
		Map<String, Object> model = new HashMap<>();
		return new ModelAndView("polymer_iframe", model);
	}
	
	/**
	 *  Integrate Polymer with a Vaadin component. The component Vaadin is built from Vaadin FrameFork.   
	 * @return app_polymer.html
	 */
	@RequestMapping("/polymerVaadin")
	public ModelAndView polymerVaadin() {
		Map<String, Object> model = new HashMap<>();
		return new ModelAndView("polymer_vaadin", model);
	}
	
	/**
	 * 
	 * @return index.html
	 */
	@RequestMapping("/")
	public String root() {
		return "redirect:/figure";
	}
	
	@RequestMapping(value={"/figure", "/draw", "/lazydraw", "/lazyperson"})
	public ModelAndView index() {
		Map<String, Object> model = new HashMap<>();
		return new ModelAndView("index", model);
	}
}
