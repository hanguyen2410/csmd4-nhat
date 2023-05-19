package com.cg.service.uploadMedia;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IUploadMediaService {

    Map uploadImage(MultipartFile multipartFile, Map params) throws IOException;

    Map destroyImage(String publicId, Map params) throws IOException;

}
