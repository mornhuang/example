package com.redsaga.hibernatesample.step3.action;

import com.redsaga.hibernatesample.step3.*;

import java.util.Date;
import java.util.Iterator;


public class VoteAction extends AbstractAction {

	private Article article;
    private Integer parentId;
    private int selection;

	public VoteAction() {
		article = new Article();
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
        Vote vote = (Vote) fs.getPost(parentId);

        article.setLastUpdateTime(new Date());
        article.setLastUpdateBy((User) get("loginUser"));
        article.setCreateBy(article.getLastUpdateBy());
        article.setHits(0);
        article.setBytes(article.getBody().length());

        fs.replyVote(vote,article,selection);

        set("vote",vote);

		return SUCCESS;
	}

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }
}
