package com.naver.myhome4.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.myhome4.domain.Board;
import com.naver.myhome4.mybatis.mapper.BoardMapper;



@Service
public class BoardServiceImpl implements BoardService{
	
	private BoardMapper dao;
	
	@Autowired
	public BoardServiceImpl(BoardMapper dao) {
		this.dao = dao;
	}

	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public List<Board> getBoardList(int page, int limit) {

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	@Override
	public Board getDetail(int num) {
		return dao.getDetail(num);
	}

	@Override
	@Transactional
	public int boardReply(Board board) {
		boardReplyUpdate(board);
		//double i = 1/0;
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
		return dao.boardReply(board);
	}

	@Override
	public int boardModify(Board modifyboard) {
		return dao.boardModify(modifyboard);
	}

	@Override
	public int boardDelete(int num) {
		int result=0;
		Board board = dao.getDetail(num);
		if(board != null) {
			result=dao.boardDelete(board);
		}
		return result;
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	@Override
	public boolean isBoarWriter(int num, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		Board result = dao.isBoarWriter(map);
		if(result==null)
			return false;
		else
			return true;
	}

	@Override
	public void insertBoard(Board board) {
		dao.insertBoard(board);
		
	}

	@Override
	public int boardReplyUpdate(Board board) {
		return dao.boardReplyUpdate(board);
	}

	@Override
	public List<String> getDeleteFileList() {
		return dao.getDeleteFileList();
	}

	@Override
	public void deleteFileList(String filename) {
		dao.deleteFileList(filename);
	}

	


	
}
