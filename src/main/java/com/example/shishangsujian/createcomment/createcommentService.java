package com.example.shishangsujian.createcomment;

import com.example.shishangsujian.createcomment.membergrade.*;
import com.example.shishangsujian.createorders.*;
import com.example.shishangsujian.ordersrecord.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/*
先创建memebergrade信息后再创建submitanswers信息
 */
@Service
public class createcommentService {
    @Autowired
    private createcommentRepository createcommentRepository;
    @Autowired
    private createordersRepository createordersRepository;
    @Autowired
    private membergradeRepository membergradeRepository;
    @Autowired
    private ordersrecordRepository ordersrecordRepository;

    @Autowired
    private membergradeService service;
    public void createcomment(String serial, String questionId, String answerId){
        createcommentModel comment=new createcommentModel();
        createordersModel order=createordersRepository.findBySerial(serial);
        comment.setDictionaryId("2");

        String memberIdString = Integer.toString(order.getMemberId());
        comment.setMemberId(memberIdString);

        comment.setStaffId(order.getStaffId());

        comment.setOrdersId(order.getSerial());

        comment.setCreatetime(order.getCreatetime());

        comment.setQuestionId(questionId);

        comment.setAnswersId(answerId);

        if(questionId.equals("82")) {
            comment.setQuestion("师傅的卫生情况");
            if(answerId.equals("165")){
                comment.setAnswers("好");
                comment.setScores(1);
            } else if (answerId.equals("166")) {
                comment.setAnswers("一般");
                comment.setScores(0);
            } else if (answerId.equals("167")) {
                comment.setAnswers("差");
                comment.setScores(-1);
            }
        }

        else if (questionId.equals("6")) {
            comment.setQuestion("师傅的服务态度");
            if(answerId.equals("11")){
                comment.setAnswers("好");
                comment.setScores(1);
            } else if (answerId.equals("10")) {
                comment.setAnswers("一般");
                comment.setScores(0);
            } else if (answerId.equals("12")) {
                comment.setAnswers("差");
                comment.setScores(-1);
            }
        }

        else if(questionId.equals("5")) {
            comment.setQuestion("师傅的技术水平");
            if(answerId.equals("8")){
                comment.setAnswers("好");
                comment.setScores(2);
            } else if (answerId.equals("7")) {
                comment.setAnswers("一般");
                comment.setScores(0);
            } else if (answerId.equals("9")) {
                comment.setAnswers("差");
                comment.setScores(-2);
            }
        }

        comment.setType(0);

        comment.setTableName("membergrade");

        membergradeModel model=membergradeRepository.findBySerial(serial);
        comment.setTableId(model.getId().intValue());

        if(model.getSumValue()==-999)
            model.setSumValue(comment.getScores());

        else{
            int score=model.getSumValue()+comment.getScores();
            model.setSumValue(score);
        }
        createcommentRepository.save(comment);
        membergradeRepository.save(model);
        System.out.println("保存评价成功");
    }

    //评价后修改订单Status为4
    public void setStatus(String serial){
        ordersrecordModel model=ordersrecordRepository.findBySerial(serial);
        model.setStatus("4");
        ordersrecordRepository.save(model);
    }
}
