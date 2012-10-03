package com.redsaga.hibernatesample.step4.action;

import com.redsaga.hibernatesample.step4.*;

import java.util.Date;


public class AddNewVoteAction extends AbstractAction {

	private Vote vote;
    private String options;
    private Integer boardId;

	public AddNewVoteAction() {
		vote = new Vote();
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
        Board board = fs.getBoard(boardId);

        vote.setLastUpdateTime(new Date());
        vote.setLastUpdateBy((User) get("loginUser"));
        vote.setCreateBy(vote.getLastUpdateBy());
        vote.setHits(0);
        vote.setBytes(vote.getBody().length());

        String[] optionTokens = options.split("\r");
        for (int i=0;i<optionTokens.length;i++)
        {
            VoteOption vo = new VoteOption();
            vo.setOptionText(optionTokens[i]);
            vo.setPoll(vote);
            vo.setAgreeNumber(0);
            vote.addToOptionSet(vo);

        }
        fs.addNewPost(board,vote);
		return SUCCESS;
	}

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

}
