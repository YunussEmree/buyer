package com.YunussEmree.buyer.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface IModelMapperManager {
    ModelMapper forResponse();

    ModelMapper forRequest();
}
