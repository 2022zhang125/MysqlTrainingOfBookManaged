package com.believesun.mysqltrain.mapper;

import com.believesun.mysqltrain.pojo.Reader;

public interface ReaderManagedMapper {
    String selectTypeByTypeId(Integer typeId);

    int insertReader(Reader reader);
    Reader selectReaderByNo(String no);
    Integer selectTypeIdByType(String type);
}
