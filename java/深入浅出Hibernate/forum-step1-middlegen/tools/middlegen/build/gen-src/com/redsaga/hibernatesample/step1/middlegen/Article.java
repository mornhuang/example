package com.redsaga.hibernatesample.step1.middlegen;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="article"
 *     
*/
public class Article implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int articleType;

    /** persistent field */
    private String title;

    /** nullable persistent field */
    private String body;

    /** persistent field */
    private Date createTime;

    /** persistent field */
    private int hits;

    /** nullable persistent field */
    private Integer bytes;

    /** persistent field */
    private Date lastUpdateTime;

    /** persistent field */
    private com.redsaga.hibernatesample.step1.middlegen.User userByLastUpdateBy;

    /** persistent field */
    private com.redsaga.hibernatesample.step1.middlegen.User userByCreateBy;

    /** persistent field */
    private com.redsaga.hibernatesample.step1.middlegen.Article article;

    /** persistent field */
    private com.redsaga.hibernatesample.step1.middlegen.Board board;

    /** persistent field */
    private Set articles;

    /** full constructor */
    public Article(int articleType, String title, String body, Date createTime, int hits, Integer bytes, Date lastUpdateTime, com.redsaga.hibernatesample.step1.middlegen.User userByLastUpdateBy, com.redsaga.hibernatesample.step1.middlegen.User userByCreateBy, com.redsaga.hibernatesample.step1.middlegen.Article article, com.redsaga.hibernatesample.step1.middlegen.Board board, Set articles) {
        this.articleType = articleType;
        this.title = title;
        this.body = body;
        this.createTime = createTime;
        this.hits = hits;
        this.bytes = bytes;
        this.lastUpdateTime = lastUpdateTime;
        this.userByLastUpdateBy = userByLastUpdateBy;
        this.userByCreateBy = userByCreateBy;
        this.article = article;
        this.board = board;
        this.articles = articles;
    }

    /** default constructor */
    public Article() {
    }

    /** minimal constructor */
    public Article(int articleType, String title, Date createTime, int hits, Date lastUpdateTime, com.redsaga.hibernatesample.step1.middlegen.User userByLastUpdateBy, com.redsaga.hibernatesample.step1.middlegen.User userByCreateBy, com.redsaga.hibernatesample.step1.middlegen.Article article, com.redsaga.hibernatesample.step1.middlegen.Board board, Set articles) {
        this.articleType = articleType;
        this.title = title;
        this.createTime = createTime;
        this.hits = hits;
        this.lastUpdateTime = lastUpdateTime;
        this.userByLastUpdateBy = userByLastUpdateBy;
        this.userByCreateBy = userByCreateBy;
        this.article = article;
        this.board = board;
        this.articles = articles;
    }

    /** 
     *            @hibernate.id
     *             generator-class="increment"
     *             type="java.lang.Integer"
     *             column="id"
     *         
     */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     *            @hibernate.property
     *             column="article_type"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getArticleType() {
        return this.articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    /** 
     *            @hibernate.property
     *             column="title"
     *             length="255"
     *             not-null="true"
     *         
     */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** 
     *            @hibernate.property
     *             column="body"
     *             length="65535"
     *         
     */
    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /** 
     *            @hibernate.property
     *             column="create_time"
     *             length="19"
     *             not-null="true"
     *         
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     *            @hibernate.property
     *             column="hits"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getHits() {
        return this.hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    /** 
     *            @hibernate.property
     *             column="bytes"
     *             length="11"
     *         
     */
    public Integer getBytes() {
        return this.bytes;
    }

    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    /** 
     *            @hibernate.property
     *             column="last_update_time"
     *             length="19"
     *             not-null="true"
     *         
     */
    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="last_update_by"         
     *         
     */
    public com.redsaga.hibernatesample.step1.middlegen.User getUserByLastUpdateBy() {
        return this.userByLastUpdateBy;
    }

    public void setUserByLastUpdateBy(com.redsaga.hibernatesample.step1.middlegen.User userByLastUpdateBy) {
        this.userByLastUpdateBy = userByLastUpdateBy;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="create_by"         
     *         
     */
    public com.redsaga.hibernatesample.step1.middlegen.User getUserByCreateBy() {
        return this.userByCreateBy;
    }

    public void setUserByCreateBy(com.redsaga.hibernatesample.step1.middlegen.User userByCreateBy) {
        this.userByCreateBy = userByCreateBy;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="parent_id"         
     *         
     */
    public com.redsaga.hibernatesample.step1.middlegen.Article getArticle() {
        return this.article;
    }

    public void setArticle(com.redsaga.hibernatesample.step1.middlegen.Article article) {
        this.article = article;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="board_id"         
     *         
     */
    public com.redsaga.hibernatesample.step1.middlegen.Board getBoard() {
        return this.board;
    }

    public void setBoard(com.redsaga.hibernatesample.step1.middlegen.Board board) {
        this.board = board;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="parent_id"
     *            @hibernate.collection-one-to-many
     *             class="com.redsaga.hibernatesample.step1.middlegen.Article"
     *         
     */
    public Set getArticles() {
        return this.articles;
    }

    public void setArticles(Set articles) {
        this.articles = articles;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof Article) ) return false;
        Article castOther = (Article) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

}
