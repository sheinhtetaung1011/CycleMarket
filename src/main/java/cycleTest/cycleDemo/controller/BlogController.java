package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.annotation.CurrentUser;
import cycleTest.cycleDemo.dto.BLogDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.service.BlogService;
import cycleTest.cycleDemo.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/blog")
@Slf4j
public class BlogController {

    @Autowired
    BlogService blogService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getBlogsPage(@Param("userId")Long userId
            , @Param("status")Status status
            , @PageableDefault Pageable pageable, Errors errors){

        log.debug("start getting blog pages::");
        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        PageableDTO pageableDTO=blogService.getAllBlogs(userId, status, pageable);
        log.debug("end getting all pages::");
        return new ResponseEntity<>(pageableDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addBlog(@CurrentUser CustomUserDetails customUserDetails
            , @RequestBody BLogDTO bLogDTO,Errors errors) throws IOException {

        System.out.println("user::"+customUserDetails.getUsername());
        log.debug("start adding a blog::");
        if(errors.hasErrors()){
            return  new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }
        blogService.addBlog(customUserDetails,bLogDTO);
        log.debug("end adding a blog::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("Successfully add a blog."),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateBlog(@CurrentUser CustomUserDetails customUserDetails
            , @RequestBody BLogDTO bLogDTO,Errors errors) throws IOException {

        log.debug("start updating a blog::");
        if(errors.hasErrors()){
            return  new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }
        blogService.updateBlog(customUserDetails,bLogDTO);
        log.debug("end updating a blog::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("Successfully update a blog."),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogDetail(@PathVariable("id") Long id,Errors errors){

        log.debug("start getting blog by id::");
        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }
        BLogDTO bLogDTO=blogService.getBlog(id);
        log.debug("end getting blog by id::");
        return new ResponseEntity<>(bLogDTO,HttpStatus.OK);
    }

}
