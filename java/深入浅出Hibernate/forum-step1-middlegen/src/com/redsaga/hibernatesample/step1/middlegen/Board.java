package com.redsaga.hibernatesample.step1.middlegen;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="board"
 *     
*/
public class Board implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String remark;

    /** persistent field */
    private Date createTime;

    /** persistent field */
    private com.redsaga.hibernatesample.step1.middlegen.User user;

    /** persistent field */
    private com.redsaga.hibernatesample.step1.middlegen.Board board;

    /** persistent field */
    private Set articles;

    /** persistent field */
    private Set boards;

    /** full constructor */
    public Board(String name, String remark, Date createTime, com.redsaga.hibernatesample.step1.middlegen.User user, com.redsaga.hibernatesample.step1.middlegen.Board board, Set articles, Set boards) {
        this.name = name;
        this.remark = remark;
        this.createTime = createTime;
        this.user = user;
        this.board = board;
        this.articles = articles;
        this.boards = boards;
    }

    /** default constructor */
    public Board() {
    }

    /** minimal constructor */
    public Board(String name, Date createTime, com.redsaga.hibernatesample.step1.middlegen.User user, com.redsaga.hibernatesample.step1.middlegen.Board board, Set articles, Set boards) {
        this.name = name;
        this.createTime = createTime;
        this.user = user;
        this.board = board;
        this.articles = articles;
        this.boards = boards;
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
     *             column="name"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** 
     *            @hibernate.property
     *             column="remark"
     *             length="255"
     *         
     */
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="create_by"         
     *         
     */
    public com.redsaga.hibernatesample.step1.middlegen.User getUser() {
        return this.user;
    }

    public void setUser(com.redsaga.hibernatesample.step1.middlegen.User user) {
        this.user = user;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="parent_id"         
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
     *             column="board_id"
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

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="parent_id"
     *            @hibernate.collection-one-to-many
     *             class="com.redsaga.hibernatesample.step1.middlegen.Board"
     *         
     */
    public Set getBoards() {
        return this.boards;
    }

    public void setBoards(Set boards) {
        this.boards = boards;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
