package com.YunussEmree.buyme.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

public interface IModelMapperManager {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
