package com.bnymellon.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public interface MainController {

	String login();

	String signup();

	String home();

}