package org.chocolates.controller;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static  spark.Spark.*;

import java.util.Map;

public class ChocolateController {
    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }


}
