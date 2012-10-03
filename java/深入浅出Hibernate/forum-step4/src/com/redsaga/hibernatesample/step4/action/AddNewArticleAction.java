package com.redsaga.hibernatesample.step4.action;

import com.redsaga.hibernatesample.step4.*;

import java.util.Date;


public class AddNewArticleAction extends AbstractAction {

	private Article article;
    private Integer boardId;

	public AddNewArticleAction() {
		article = new Article();
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
        Board board = fs.getBoard(boardId);

        article.setLastUpdateTime(new Date());
        article.setLastUpdateBy((com.redsaga.hibernatesample.step4.User) get("loginUser"));
        article.setCreateBy(article.getLastUpdateBy());
        article.setHits(0);
        article.setBytes(article.getBody().length());
        fs.addNewPost(board,article);
		return SUCCESS;
	}

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
