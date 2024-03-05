package cycleTest.cycleDemo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import cycleTest.cycleDemo.entity.CloudinaryKey;
import cycleTest.cycleDemo.payloads.MediaUploadResponse;
import cycleTest.cycleDemo.repository.CloudinaryKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CloudinaryService {

    Cloudinary cloudinary;

    @Autowired
    CloudinaryKeyRepository cloudinaryKeyRepository;

    public Cloudinary getCloudinary(){
        Optional<CloudinaryKey> cloudinaryData=cloudinaryKeyRepository.findById(1L);
        if (cloudinaryData.isEmpty()){
            throw new RuntimeException("CloudinaryKey not found.");
        }
        this.cloudinary=new Cloudinary(cloudinaryData.get().getCloudinaryUrl());
        return cloudinary;
    }

    public MediaUploadResponse uploadImage(byte[] file) throws RuntimeException, IOException {

        Map<?,?> uploadResult=cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        MediaUploadResponse response=new MediaUploadResponse();
        response.setUrl((String) uploadResult.get("secure_url"));
        return response;
    }


    public MediaUploadResponse uploadVideo(byte[] file) throws RuntimeException, IOException {
        Map<String,Object> videoConfig=new HashMap<>();
        videoConfig.put("resource_type","video");

        Map<?,?> uploadResult=cloudinary.uploader().upload(file,videoConfig);
        MediaUploadResponse response=new MediaUploadResponse();
        response.setUrl((String) uploadResult.get("secure_url"));
        return response;
    }

}
