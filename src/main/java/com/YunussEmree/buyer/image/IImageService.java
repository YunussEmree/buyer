package com.YunussEmree.buyer.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    List<ImageDto> addImages(List<MultipartFile> file, Long productId);

    void deleteImageById(Long id);

    void updateImage(MultipartFile file, Long imageId);

    Image getImageById(Long id);

}