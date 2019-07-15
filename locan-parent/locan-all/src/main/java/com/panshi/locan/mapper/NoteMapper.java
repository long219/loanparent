package com.panshi.locan.mapper;

import com.panshi.locan.domain.NoteVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteMapper {

    List<NoteVO> queryAll();

    void addNote(NoteVO noteVO);

    void updateNote(NoteVO noteVO);

    List<NoteVO> queryByIphone(NoteVO noteVO);
}
