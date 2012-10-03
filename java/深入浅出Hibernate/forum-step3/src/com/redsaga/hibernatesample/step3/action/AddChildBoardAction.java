package com.redsaga.hibernatesample.step3.action;

import com.redsaga.hibernatesample.step3.ForumService;
import com.redsaga.hibernatesample.step3.ForumServiceFactory;
import com.redsaga.hibernatesample.step3.User;
import com.redsaga.hibernatesample.step3.Board;
import com.redsaga.hibernatesample.step3.dao.RootDAO;
import net.sf.hibernate.Session;


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
