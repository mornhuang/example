package com.redsaga.hibernatesample.step1.middlegen;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="user"
 *     
*/
public class User implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String name;

    /** persistent field */
    private String pwd;

    /** persistent field */
    private Set articlesByLastUpdateBy;

    /** persistent field */
    private Set articlesByCreateBy;

    /** persistent field */
    private Set boards;

    /** full constructor */
    public User(String name, String pwd, Set articlesByLastUpdateBy, Set articlesByCreateBy, Set boards) {
        this.name = name;
        this.pwd = pwd;
        this.articlesByLastUpdateBy = articlesByLastUpdateBy;
        this.articlesByCreateBy = articlesByCreateBy;
        this.boards = boards;
    }

    /** default constructor */
    public User() {
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
     *             column="pwd"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="last_update_by"
     *            @hibernate.collection-one-to-many
     *             class="com.redsaga.hibernatesample.step1.middlegen.Article"
     *         
     */
    public Set getArticlesByLastUpdateBy() {
        return this.articlesByLastUpdateBy;
    }

    public void setArticlesByLastUpdateBy(Set articlesByLastUpdateBy) {
        this.articlesByLastUpdateBy = articlesByLastUpdateBy;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="create_by"
     *            @hibernate.collection-one-to-many
     *             class="com.redsaga.hibernatesample.step1.middlegen.Article"
     *         
     */
    public Set getArticlesByCreateBy() {
        return this.articlesByCreateBy;
    }

    public void setArticlesByCreateBy(Set articlesByCreateBy) {
        this.articlesByCreateBy = articlesByCreateBy;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="create_by"
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
