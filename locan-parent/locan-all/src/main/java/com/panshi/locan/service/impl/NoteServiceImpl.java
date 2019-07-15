package com.panshi.locan.service.impl;

import com.panshi.locan.domain.NoteVO;
import com.panshi.locan.mapper.NoteMapper;
import com.panshi.locan.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public List<NoteVO> queryAll() {
        return noteMapper.queryAll();
    }

    @Override
    public void addNote(NoteVO noteVO) {
         noteMapper.addNote(noteVO);
    }

    @Override
    public void updateNote(NoteVO noteVO) {
        noteMapper.updateNote(noteVO);
    }

    @Override
    public List<NoteVO> queryByIphone(NoteVO noteVO) {

        return noteMapper.queryByIphone(noteVO);
    }


    /**
     * 校验短信信息
     * @param noteVO
     * @return
     */
    /*public  int check(NoteVO noteVO){
        List<NoteVO> noteVOS = noteMapper.queryAll();

        for (int i = 0; i < noteVOS.size(); i++) {
            NoteVO noteVO1 = noteVOS.get(i);

            if (noteVO1.getType().equals(noteVO.getType())){
                if (noteVO1.getIphone().equals(noteVO.getIphone())){
                    return  i;
                }
            }
        }
        return  -1;
    }*/


    /**
     * 生成验证码
     * @return
     */
    public String getVftCode(){

        String str="0123456789";
        StringBuilder sb=new StringBuilder(4);

        // 生成四位验证码
        for(int i=0;i<4;i++){
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }

        return sb.toString();
    }



}
