package org.chocolates.controller;

import org.chocolates.model.Chocolate;
import org.chocolates.service.ChocolateService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static  spark.Spark.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChocolateController {
    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public ChocolateController(final ChocolateService chocolateService){

        get("/", ((request, response) -> {
            response.redirect("/chocs");

            return  "";
        }));

        get("/chocs", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int chocCount = chocolateService.selectAllChocolate().size();

            List<Chocolate> chocLists = chocolateService.selectAllChocolate();


            model.put("chocCount", chocCount);
            model.put("chocLists", chocLists);

            return render(model, "index.hbs");
        }));

        get("/chocs/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "addChocForm.hbs");
        }));

        post("/chocs/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String chocolateName = request.queryParams("chocName");
            int chocInitQty = Integer.parseInt(request.queryParams("initialQty"));

            Supplier<Integer> getNewId = () -> {
                List<Chocolate> chocolateList = chocolateService.selectAllChocolate();
                Comparator<Chocolate> byId = (c1, c2) -> c1.getId()-c2.getId();
                Collections.sort(chocolateList, byId);

                if(chocolateList.size() == 0)
                    return 1;

                Chocolate lastChocInList = chocolateList.get(chocolateList.size() - 1);

                int newId = lastChocInList.getId() + 1;

                return newId;
            };

            Chocolate newChoc = new Chocolate(getNewId.get(), chocolateName, chocInitQty);

            chocolateService.insertChocolate(newChoc);

            response.redirect("/chocs");

            return  "";
        }));

        get("/chocs/delete/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int idOfChocToBeDeleted = Integer.parseInt(request.params("id"));

            chocolateService.deleteChocolate(idOfChocToBeDeleted);

            int chocCount = chocolateService.selectAllChocolate().size();
            List<Chocolate> chocLists = chocolateService.selectAllChocolate();


            model.put("chocCount", chocCount);
            model.put("chocLists", chocLists);

            return render(model, "index.hbs");
        }));

        get("/chocs/update/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int idOfChocToBeDeleted = Integer.parseInt(request.params("id"));

            Chocolate selectedChoc = chocolateService.selectChocolate(idOfChocToBeDeleted);

            String chocName = selectedChoc.getName();
            int chocQty = selectedChoc.getQty();

            model.put("chocName", chocName);
            model.put("chocQty", chocQty);
            model.put("id", idOfChocToBeDeleted);

            return render(model, "updateChocForm.hbs");
        }));

        post("/chocs/update/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int idOfChocToBeDeleted = Integer.parseInt(request.params("id"));
            int newQty = Integer.parseInt(request.queryParams("updateQty"));

            Chocolate selectedChoc = chocolateService.selectChocolate(idOfChocToBeDeleted);
            selectedChoc.setQty(newQty);

            chocolateService.updateChocolate(selectedChoc);

            response.redirect("/chocs");

            return  "";
        }));
    }
}
