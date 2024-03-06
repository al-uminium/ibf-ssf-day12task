package ibf.sdf.day12task.Controller;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/")
public class CartController {

  @GetMapping
  public String landingPage() {
    return "cart";
  }

  @PostMapping
  public String cartSubmit(@RequestParam MultiValueMap<String,String> params, Model model) {

    List<CartItem> cartItemList = new LinkedList<CartItem>();

    List<String> names = params.get("name");
    List<String> quantities = params.get("quantity");

    for (int i = 0; i < names.size(); i++) {
      cartItemList.add(new CartItem(names.get(i), quantities.get(i)));
    }

    model.addAttribute("itemList", cartItemList);
    if (cartItemList.size() >= 1) {
      model.addAttribute("isVisible", "unhide");
    } else {
      model.addAttribute("isVisible", "hide");
    }

    return "cart";
  }
}
