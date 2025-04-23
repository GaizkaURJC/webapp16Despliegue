package com.daw.daw.controller.MVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.daw.daw.model.Comment;
import com.daw.daw.model.User;
import com.daw.daw.repository.CommentRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * This controller handles the creation of comments for events in the web
 * application.
 * It uses Spring MVC framework annotations to map HTTP requests to handler
 * methods.
 * 
 * The main functionality provided by this controller includes:
 * - Adding a new comment to an event.
 * 
 * The `addComent` method is responsible for:
 * - Checking if the user is authenticated.
 * - Retrieving the username of the authenticated user.
 * - Creating a new Comment object and saving it to the repository.
 * - Redirecting the user to the home page after the comment is saved.
 * 
 * Dependencies:
 * - CommentRepository: Used to interact with the database for saving comments.
 * - UserMVCController: Used to manage user-related operations.
 * 
 * Note: If the user is not authenticated, they will be redirected to the login
 * page.
 */

@Controller
@RequestMapping("/coments/")
public class ComentsMVCController {

    @Autowired
    private CommentRepository comentsRepository;

    @Autowired
    private UserMVCController userController;

    @PostMapping("create")
    public String addComent(@RequestParam("rate") int rating,
            @RequestParam("coment") String comment,
            String userName,
            HttpSession session,
            @RequestParam("id") int eventId,
            HttpServletResponse response) {

        // Check if the user is logged
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUserLogged = authentication.isAuthenticated();

        if (!isUserLogged) {
            return "redirect:/login";
        }
        Object principal = authentication.getPrincipal();
        String username = "";
        User user = null;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof User) {
            username = ((User) principal).getEmail(); // Uses email if it's saved on user
            user = ((User) principal);
        }
        Comment coments = new Comment(rating, comment, username, eventId);
        comentsRepository.save(coments);
        return "redirect:/";
    }

    @PostMapping("deleteComment")
    public String deleteComment(@RequestParam("id") Long id) {
        comentsRepository.deleteById(id);
        return "redirect:/admin";
    }
    

}