package com.YunussEmree.buyme.image;

import java.util.List;

public interface IImageService {

    Image addImage(AddImageRequest request);
    void deleteImageById(Long id);
    void updateImage(Image image, Long imageId);
    Image getImageById(Long id);
    List<Image> getAllImages();
}
