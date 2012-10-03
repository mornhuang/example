package com.redsaga.hibernatesample.step3.action;

import com.redsaga.hibernatesample.step3.ForumService;
import com.redsaga.hibernatesample.step3.ForumServiceFactory;
import com.redsaga.hibernatesample.step3.User;
import com.redsaga.hibernatesample.step3.Board;


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
