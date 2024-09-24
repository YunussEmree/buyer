package com.YunussEmree.buyme.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface IModelMapperManager {
    ModelMapper forResponse();

    ModelMapper forRequest();
}
