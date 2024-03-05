package cycleTest.cycleDemo.utils;

import cycleTest.cycleDemo.config.MediaProperties;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class ImageUtils {

    public static MediaProperties mediaProperties;

    public ImageUtils(MediaProperties properties) {
        ImageUtils.mediaProperties = properties;
    }

    public static String getImageUrl(String imageUrl) {
        if (!Objects.isNull(imageUrl))
            return mediaProperties.getUrl() + imageUrl;
        else
            return null;
    }

    public static byte[] getImageBytes(String base64image){

        String[] parts=base64image.split(",");

        byte[] imageByte=Base64.decodeBase64(parts[1]);
        return imageByte;
    }

    public static String uploadImage(String image,String imageDirectory){

        return "test";
    }
}


