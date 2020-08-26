package com.project.MyStickyNotes.Controllers;

import com.project.MyStickyNotes.Dto.*;
import com.project.MyStickyNotes.Repositories.DetailsRepository;
import com.project.MyStickyNotes.Services.DetailsService;
import com.project.MyStickyNotes.Services.MyUserDetailsService;
import com.project.MyStickyNotes.Services.RegisterService;
import com.project.MyStickyNotes.Util.DataResponse;
import com.project.MyStickyNotes.Util.InitialRequestResponse;
import com.project.MyStickyNotes.Util.JsonResponse;
import com.project.MyStickyNotes.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/mystickynotes")
public class MainController {
    @Autowired
    RegisterService registerService;
    @Autowired
    DetailsService detailsService;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    DetailsRepository detailsRepository;


    @RequestMapping("/hello")
    @ResponseBody
    public String registerUser() {
        String p = "hello";
        return p;
    }

////// Register

    @RequestMapping("/register")
    @ResponseBody
    public JsonResponse Register(@RequestBody RegisterDto obj) throws Exception {

        return registerService.register(obj);


    }

    /// initial request after login which fetches count of pending completed and all tasks and the tasks of that logged date


    @RequestMapping("/initialrequest")
    @ResponseBody
    public InitialRequestResponse InitialRequest(@RequestBody Initialrequestdto initialrequestdto) {
        String username = initialrequestdto.getUsername();
        InitialRequestResponse initialRequestResponse = new InitialRequestResponse();
        UsernameAndDates usernameAndDates = new UsernameAndDates();
        usernameAndDates.setUsername(username);
        List<DetailsDto> initialList = detailsService.fetchByUsernameAndDates(usernameAndDates, initialrequestdto.getPagenumber(), initialrequestdto.getPagesize(), initialRequestResponse,null);
        initialRequestResponse.setTasksonloginday(initialList);// this will return list of all pending &completed requests
        initialRequestResponse.setTodaytasks(initialRequestResponse.getTotalsize());

        int allDetailsCount = detailsRepository.allDetailsCount(username);
        initialRequestResponse.setTotaltasks(allDetailsCount);
        int pendingDetailsCount = detailsRepository.pendingDetailsCount(username, "Pending");
        initialRequestResponse.setPendingtasks(pendingDetailsCount);
        int completedTasksCount = allDetailsCount - pendingDetailsCount;
        initialRequestResponse.setCompletedtasks(completedTasksCount);
        return initialRequestResponse;


    }

    // deletes the taskdetails by id
    @RequestMapping("/delete")
    @ResponseBody
    public int deleteById(@RequestBody Deletedto deletedto) {

        return detailsService.deleteById(deletedto);

    }

//// edit by id edits the task details and status but not the createdon date
    @RequestMapping("/edit")
    @ResponseBody
    public int editById(@RequestBody EditDto editDto) {

        return detailsService.editById(editDto);

    }
    // Mark as pending or completed

    @RequestMapping("/markby")
    @ResponseBody
    public int markById(@RequestBody MarkByDto markByDto) {

        return detailsService.markById(markByDto);

    }






////fetch by username  --->  fetch all including pending and completed for all dates

    @RequestMapping("/getByUsername/{username}")
    @ResponseBody
    public RegisterDto fetchByUsername(@PathVariable("username") String username) {

        return detailsService.fetchByUsername(username);

    }
//fetch by username and dates ---> fetches all including pending and completed between dates
    // ---> able to fetch by status both pending and completed


    @RequestMapping("/getByUsernameAndDates")
    @ResponseBody
    public DataResponse fetchByUsernameAndDates(@RequestBody UsernameAndDates usernameAndDates) {
        DataResponse<DetailsDto> dataResponse = new DataResponse<DetailsDto>();
        List<DetailsDto> data =  detailsService.fetchByUsernameAndDates(usernameAndDates,usernameAndDates.getPagenumber(),
                usernameAndDates.getPagesize(), null,dataResponse);

        if(data!=null){
            dataResponse.setSuccess(true);
            dataResponse.setData(data);
        }
        else{
            dataResponse.setSuccess(false);
            dataResponse.setData(data);
        }
        return dataResponse;
    }


    /// save details
    @RequestMapping("/savedetails/{username}")
    @ResponseBody
    public Boolean saveDetails(@RequestBody DetailsDto obj, @PathVariable("username") String username) {

        return detailsService.saveDetails(obj, username);

    }

// Authentication Login


    @RequestMapping("/login")
    @ResponseBody
    public JsonResponse authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Authentication a = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));
        } catch (Exception e) {
            return new JsonResponse("notloggedin", false, null, null);
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());

        // create a cookie for auto login


        String jwt = jwtUtil.generateToken(userDetails);
//        if (authenticationRequestDto.isRememberme()) {
//            Cookie c = new Cookie("remember-me", userDetails.getUsername());
//            response.addCookie(c);
//            return new JsonResponse("autologin", true, jwt, userDetails.getUsername());
//        }
        return new JsonResponse("loggedin", true, jwt, userDetails.getUsername());
    }

    @RequestMapping("/logout")
    @ResponseBody
    public JsonResponse logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie[] = request.getCookies();
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("remember-me")) {
                    c.setMaxAge(0);
                    c.setValue("");
                    response.addCookie(c);
                }
            }
        }

        return new JsonResponse("logout", false, null, null);
    }

    //
    @RequestMapping("/deletecookie")
    public JsonResponse deleteCooke(HttpServletRequest req,
                                    HttpServletResponse res) {

        Cookie cookie[] = req.getCookies();
        if (cookie != null) {

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("remember-me")) {
                    c.setValue("");
                    c.setMaxAge(0);
                    res.addCookie(c);
                }

            }
        }
        return new JsonResponse("deleted", false, null, null);
    }


    // refresh token generator

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public JsonResponse refreshAuthenticationToken(
            HttpServletRequest request,
            HttpServletResponse response,
            Principal principal
    ) {

        String authToken = jwtUtil.getToken(request);


        if (authToken != null && principal != null) {

            // TODO check user password last update
            String refreshedToken = jwtUtil.refreshToken(authToken);


            return new JsonResponse("refresh token", true, refreshedToken, jwtUtil.getUsernameFromToken(refreshedToken));
        }
        return null;
    }


/*
    //fetch all records


    @RequestMapping("/getAllRecords")
    @ResponseBody
    public List<RegisterDto> fetchAllRecords(@RequestBody RegisterDto obj) {

        return loginRegisterService.fetchAllRecords(obj);

    }

    // update a record
    @RequestMapping("/update")
    @ResponseBody
    public void updateByUsername(@RequestBody RegisterDto obj) {

        loginRegisterService.updateByUsername(obj);


    }


    @RequestMapping("/hello")
    public String Hello(HttpServletRequest request, HttpServletResponse response) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession newsession = request.getSession(true);
        newsession.setMaxInactiveInterval(60);
        return "hello";
    }

    @RequestMapping("/sessioncheck")
    public String sessioncheck(HttpServletRequest request, HttpServletResponse response) {

        return "sessioncheck";
    }

 */
}

