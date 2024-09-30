package com.YunussEmree.buyer.image;

import lombok.Data;

@Data
public class ImageDto {  //Todo change to record
    private Long id;
    private String fileName;
    private String downloadUrl;
}
