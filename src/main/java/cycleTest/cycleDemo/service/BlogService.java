package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.BLogDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.entity.Blog;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.payloads.MediaUploadResponse;
import cycleTest.cycleDemo.repository.BlogRepository;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.utils.CommonUtils;
import cycleTest.cycleDemo.utils.ImageUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryService cloudinaryService;

    public PageableDTO getAllBlogs(Long userId, Status status,Pageable pageable){

        Page<Object[]> blogPage=blogRepository.findAllWithoutText(userId,status,pageable);
        List<Object[]> blogList=blogPage.getContent();

        List<BLogDTO> bLogDTOList=new ArrayList<>();
        Blog bLog=new Blog();
        for (Object[] obj : blogList){
            bLog.setId((Long) obj[0]);
            bLog.setName((String) obj[1]);
            bLog.setCoverImageUrl((String) obj[2]);
            bLog.setUser((User) obj[3]);
            bLog.setPreviewText((String) obj[4]);
            bLog.setText((String) obj[5]);
            bLog.setStatus((Status) obj[6]);

            BLogDTO bLogDTO=new BLogDTO(bLog);
            bLogDTOList.add(bLogDTO);
        }
        PageableDTO pageableDTO=new PageableDTO(bLogDTOList,blogPage);
        return pageableDTO;
    }


    @Transactional(rollbackFor = Exception.class)
    public void addBlog(CustomUserDetails customUserDetails,@Valid BLogDTO bLogDTO) throws IOException {

        if (ObjectUtils.isEmpty(bLogDTO.getCoverImageUrl())){
            throw new BadRequestException("please provide a cover image.");
        }
        User currentUser=userService.findByName(customUserDetails.getUsername())
                .orElseThrow(()->new BadRequestException("username not found."));
        System.out.println("currentUser::" + currentUser);
        userService.validateUser(currentUser.getId(),bLogDTO.getUserId());

        Blog blog=new Blog();
        blog.setName(bLogDTO.getName());
        blog.setPreviewText(blog.getPreviewText());
        blog.setText(blog.getText());
        blog.setStatus(blog.getStatus());
        blog.setUser(currentUser);

        byte[] getImages=ImageUtils.getImageBytes(bLogDTO.getCoverImageUrl());
        MediaUploadResponse mediaUploadResponse=cloudinaryService.uploadImage(getImages);
        blog.setCoverImageUrl(mediaUploadResponse.getUrl());
        blogRepository.save(blog);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBlog(CustomUserDetails customUserDetails, @Valid BLogDTO bLogDTO) throws IOException {

        Blog blog=blogRepository.findById(bLogDTO.getId())
                .orElseThrow(()->new BadRequestException("Invalid Blog Id."));

        User currentUser=userService.getUserById(customUserDetails.getId());
        userService.validateUser(currentUser.getId(),bLogDTO.getUserId());

        blog.setName(bLogDTO.getName());
        blog.setUser(currentUser);
        blog.setPreviewText(bLogDTO.getPreviewText());
        blog.setText(bLogDTO.getText());
        blog.setStatus(bLogDTO.getStatus());

        byte[] imageBytes=ImageUtils.getImageBytes(bLogDTO.getCoverImageUrl());
        MediaUploadResponse response=cloudinaryService.uploadImage(imageBytes);
        blog.setCoverImageUrl(response.getUrl());
        blogRepository.save(blog);
    }

    public  BLogDTO getBlog(Long id){
        return new BLogDTO(getBlogById(id));
    }

    public Blog getBlogById(Long id){
        return CommonUtils.getById(id,blogRepository);
    }
}
