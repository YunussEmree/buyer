package com.YunussEmree.buyme.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{

    private ImageRepository imageRepository;


    @Override
    public Image addImage(AddImageRequest request) {

        return null;
    }

    @Override
    public void deleteImageById(Long id) {

    }

    @Override
    public void updateImage(Image image, Long imageId) {

    }

    @Override
    public Image getImageById(Long id) {
        return null;
    }

    @Override
    public List<Image> getAllImages() {
        return List.of();
    }
}
