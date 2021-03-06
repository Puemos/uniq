package com.puemos.uniq.servlet.user;

import com.puemos.uniq.dto.Question;
import com.puemos.uniq.dto.User;
import com.puemos.uniq.exceptions.InputException;
import com.puemos.uniq.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by shy on 23/11/15.
 * REST service for Users - allows to update, create and search for users
 */
@Controller
public interface IUserController {

    @RequestMapping("/isin")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    boolean isIn();

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<User> getUserDetails(Principal principal) throws NotFoundException;

    @RequestMapping(value = "/userQuery", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<List<User>> findUsers(@RequestBody Map<String, String> requestData);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    ResponseEntity<String> createUser(@RequestBody User user) throws IOException, InputException;

    @RequestMapping(value = "/user/questions", method = RequestMethod.POST)
    ResponseEntity<Page<Question>> getUserQuestions(@RequestBody Map<String, Integer> requestData, Principal principal) throws NotFoundException;

    @ExceptionHandler(InputException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    String unavailableUsernameHandler(InputException exc);
}
