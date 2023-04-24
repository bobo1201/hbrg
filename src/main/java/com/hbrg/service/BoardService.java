package com.hbrg.service;

import com.hbrg.dto.BoardFormDto;
import com.hbrg.dto.BoardSearchDto;
import com.hbrg.dto.HFileDto;
import com.hbrg.entity.*;
import com.hbrg.repository.BoardRepository;
import com.hbrg.repository.FileRepository;
import com.hbrg.repository.LikesRepository;
import com.hbrg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileAddService fileAddService;
    private final FileRepository fileRepository;
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;

    public Long saveBoard(BoardFormDto boardFormDto, List<MultipartFile> fileList) throws Exception{

        // 게시물 등록
        Board board = boardFormDto.createBoard();
        boardRepository.save(board);

        // 이미지 등록
        for (int i=0; i<fileList.size(); i++){
            Hfile file = new Hfile();
            file.setBoard(board);
            if (i == 0)
                file.setRepImgYn("Y");
            else
                file.setRepImgYn("N");
            fileAddService.saveFile(file, fileList.get(i));
        }
        return board.getBoardId();
    }

    // 파일 수정을 위한 파일 읽기
    @Transactional(readOnly = true)
    public BoardFormDto getBoardDtl(Long boardId){

        // 이미지 파일 저장
        Board board = boardRepository.findByBoardId(boardId);
        List<Hfile> fileList = board.getFiles();
        List<HFileDto> fileDtoList = new ArrayList<>();

        for (Hfile file : fileList){
            HFileDto fileDto = HFileDto.of(file);
            fileDtoList.add(fileDto);

            System.out.println(fileDto + " 1");
        }

        // boardform으로 데이터 전달 및 fileDto 저장
        BoardFormDto boardFormDto = BoardFormDto.of(board);
        boardFormDto.setFileDtoList(fileDtoList);
        return boardFormDto;
    }

    // 상품 수정을 위한 메소드 생성
    public Long updateBoard(BoardFormDto boardFormDto,
                            List<MultipartFile> fileList) throws Exception{

        // 상품 수정
        Board board = boardRepository.findByBoardId(boardFormDto.getBoardId());
        board.updateBoard(boardFormDto);

        // 이미지 파일 삭제
        List<Hfile> files = board.getFiles();
        if (!CollectionUtils.isEmpty(files)) {
            fileRepository.deleteAll(files);
        }

        // 게시판에서 삭제
        board.removeHFiles();

        // 이미지 파일 다시 저장
        for (int i=0; i<fileList.size(); i++){
            Hfile file = new Hfile();
            file.setBoard(board);
            if (i == 0)
                file.setRepImgYn("Y");
            else
                file.setRepImgYn("N");
            fileAddService.saveFile(file, fileList.get(i));
        }
        return board.getBoardId();
    }

    // like 좋아요
    public Board findBoard(Long boardId){
        Board board = boardRepository.findByBoardId(boardId);
        return board;
    }

    public Optional<Likes> findLike(Board board, Huser user) {

        return likesRepository.findByUserAndBoard(user, board);

    }

    public int saveLike(Board board, Huser user) {
        /** 로그인한 유저가 해당 게시물을 좋아요 했는지 안 했는지 확인 **/
        if(findLike(board, user).isEmpty()){
            /* 좋아요 하지 않은 게시물이면 좋아요 추가, true 반환 */
            user = userRepository.findById(user.getId());

            if(user == null){
                new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
            }

            board = boardRepository.findByBoardId(board.getBoardId());
            if(board == null){
                new IllegalArgumentException("해당 게시물이 존재하지 않습니다.");
            }

            /* 좋아요 엔티티 생성 */
//            좋아요가 없으면 추가
            Likes like = new Likes(user, board);
            likesRepository.save(like);
            board.setBLike(board.getBLike()+1);
            return 1;
        } else {
            /* 좋아요 한 게시물이면 좋아요 삭제, false 반환 */
            likesRepository.deleteByUserAndBoard(user, board);
            board.setBLike(board.getBLike()-1);
            return 0;
        }
    }


    // 페이징 처리를 위한 코드 구현 23/04/17 16:22 아래 문구 추가
    public Page<Board> boardList(Pageable pageable){
        //기존 List<Board>값으로 넘어가지만 페이징 설정을 해주면 Page<Board>로 넘어감
        return boardRepository.findAll(pageable);
    }

    public Board boardview(Long boardId){
        return boardRepository.getOne(boardId);
    }


    // 글삭제(23/04/18 16:58)
    public void boardDelete(Long boardId){
        Board board = boardRepository.findByBoardId(boardId);
        List<Hfile> files = board.getFiles();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String author = authentication.getName();

        Huser user = userRepository.findById(author);
        likesRepository.deleteByUserAndBoard(user, board);
        if (!CollectionUtils.isEmpty(files)) {
            fileRepository.deleteAll(files);
        }

        List<Reply> replies = board.

        board.removeHFiles();
        boardRepository.delete(board);
    }


    // 조회수 증가를 위한 쿼리문 사용
        @Transactional
        public int updateView(Long boardId){
            return boardRepository.updateView(boardId);
        }


        @Transactional(readOnly = true)
        public Page<Board> getAdminItemPage(BoardSearchDto boardSearchDto, Pageable pageable) {
            return boardRepository.getAdminItemPage(boardSearchDto, pageable);
    }

}
