package com.redsaga.hibernatesample.step3.action;

import com.redsaga.hibernatesample.step3.*;


public class VoteResultAction extends AbstractAction {

    private Integer articleId;
    private Article article;

	public VoteResultAction() {
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
        article = (Vote) fs.getPost(articleId);
        return SUCCESS;
	}

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
