package com.hbrg.service;

import com.hbrg.entity.BoardTag;
import com.hbrg.entity.Tag;
import com.hbrg.repository.BoardTagRepository;
import com.hbrg.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardTagService {
    @Autowired
    BoardTagRepository boardTagRepository;

    @Autowired
    TagRepository tagRepository;

    public List<Long> findTagBoardIndex(String tagNm){
        Tag tag = tagRepository.findByTagNm(tagNm);

        List<BoardTag> hashList = boardTagRepository.findByTag(tag);

        List<Long> indexList = new ArrayList<>();

        for(BoardTag hash : hashList){
            indexList.add(hash.getBoard().getBoardId());
        }

        return indexList;
    }
}
