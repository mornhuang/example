package com.redsaga.hibernatesample.step4.action;

import com.redsaga.hibernatesample.step4.ForumService;
import com.redsaga.hibernatesample.step4.ForumServiceFactory;
import com.redsaga.hibernatesample.step4.User;
import com.redsaga.hibernatesample.step4.Board;


public class AddBoardAction extends AbstractAction {

	private Board board;
	
	public AddBoardAction() {
		board = new Board();
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		fs.addBoard(board);
		set("board", board);
		return SUCCESS;
	}

    public Board getBoard() {
        return board;
    }

}
