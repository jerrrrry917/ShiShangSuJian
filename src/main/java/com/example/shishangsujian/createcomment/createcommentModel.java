package com.example.shishangsujian.createcomment;

import jakarta.persistence.*;

@Entity
@Table(name = "submitanswers")
public class createcommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dictionaryId")
    private String dictionaryId;

    @Column(name = "memberId")
    private String memberId;

    @Column(name = "staffId")
    private String staffId;

    @Column(name = "ordersId")
    private String ordersId;

    @Column(name = "createtime")
    private String createtime;

    @Column(name = "questionId")
    private String questionId;

    @Column(name = "answersId")
    private String answersId;

    @Column(name = "question")
    private String question;

    @Column(name = "answers")
    private String answers;

    @Column(name = "type")
    private Integer type;

    @Column(name = "scores")
    private Integer scores;

    @Column(name = "tableName")
    private String tableName;

    @Column(name = "tableId")
    private Integer tableId;

    @Column(name = "content")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswersId() {
        return answersId;
    }

    public void setAnswersId(String answersId) {
        this.answersId = answersId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
