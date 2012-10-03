package com.redsaga.hibernatesample.step4.action;

import com.redsaga.hibernatesample.step4.*;


public class ShowArticleAction extends AbstractAction {
    private final String SIMPLE_ARTICLE = "simpleArticle";
    private final String VOTE = "vote";

    private Integer articleId;
    private Article article;

	public ShowArticleAction() {
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
        article = fs.getPost(articleId);
        if (article instanceof Vote)
            return VOTE;
        else
            return SIMPLE_ARTICLE;
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
