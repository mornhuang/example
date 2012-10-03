package com.redsaga.hibernatesample.step4.action;

import com.redsaga.hibernatesample.step4.*;

import java.util.Date;


public class ReplyAction extends AbstractAction {

	private Article article;
    private Integer parentId;

	public ReplyAction() {
		article = new Article();
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
        Article parent = fs.getPost(parentId);

        article.setLastUpdateTime(new Date());
        article.setLastUpdateBy((com.redsaga.hibernatesample.step4.User) get("loginUser"));
        article.setCreateBy(article.getLastUpdateBy());
        article.setHits(0);
        article.setBytes(article.getBody().length());

        fs.replyPost(parent,article);
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
}
