package com.panshi.locan.service;

import com.panshi.locan.domain.NoteVO;

import java.util.List;

public interface NoteService {

    List<NoteVO> queryAll();

    void addNote(NoteVO noteVO);

    void updateNote(NoteVO noteVO);

    List<NoteVO> queryByIphone(NoteVO noteVO);
}
