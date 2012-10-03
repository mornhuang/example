package com.redsaga.hibernatesample.step4.action;

import java.util.List;
import java.util.Iterator;

import com.redsaga.hibernatesample.step4.Board;
import com.redsaga.hibernatesample.step4.ForumService;
import com.redsaga.hibernatesample.step4.ForumServiceFactory;
import com.redsaga.hibernatesample.step4.Vote;


public class ListArticleAction extends AbstractAction {

	private List articles;
    private Board board;
	private int pageSize = 20;
	private int pageNo;
	private Integer boardId;
	
	public String execute(){
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		board = fs.getBoard(boardId);
		articles = fs.getBoardPostList(board,pageSize,pageNo);

		set("articles",articles);
		return SUCCESS;
	}

	public List getArticles() {
		return articles;
	}
	
	
	/**
	 * @return Returns the boardId.
	 */
	public Integer getBoardId() {
		return boardId;
	}
	/**
	 * @param boardId The boardId to set.
	 */
	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}
	/**
	 * @return Returns the pageNo.
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo The pageNo to set.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return Returns the pageSize.
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize The pageSize to set.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
