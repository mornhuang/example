package com.redsaga.hibernatesample.step4.action;

import com.redsaga.hibernatesample.step4.ForumService;
import com.redsaga.hibernatesample.step4.ForumServiceFactory;
import com.redsaga.hibernatesample.step4.User;
import com.redsaga.hibernatesample.step4.Board;
import com.redsaga.hibernatesample.step4.dao.RootDAO;
import org.hibernate.Session;


public class AddChildBoardAction extends AbstractAction {

	private Board board;
    private Integer parentBoardId;

	public AddChildBoardAction() {
		board = new Board();
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		Board parentBoard = fs.getBoard(parentBoardId);
        fs.addChildBoard(parentBoard,board);
		set("board", board);
		return SUCCESS;
	}

    public Board getBoard() {
        return board;
    }

    public Integer getParentBoardId() {
        return parentBoardId;
    }

    public void setParentBoardId(Integer parentBoardId) {
        this.parentBoardId = parentBoardId;
    }

}
