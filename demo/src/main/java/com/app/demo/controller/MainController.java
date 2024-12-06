package com.app.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "main")
public class MainController {

    @RequestMapping("/form")
    public String form() {
        return """
                <!DOCTYPE html>
                <html>
                <body>

                <h2>Text input fields</h2>

                <form action="/main/message" method="post">
                  <label for="username">username:</label><br>
                  <input type="text" id="username" name="username"><br>
                  <label for="password">password:</label><br>
                  <input type="text" id="password" name="password"><br>
                  <br>
                  <input type="submit" value="submit">
                </form>

                </body>
                </html>
                """;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public String message(@RequestParam String username, @RequestParam String password) {
        return username + ":" + password;
    }
}
