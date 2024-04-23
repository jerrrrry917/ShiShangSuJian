package com.example.shishangsujian.staffInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class staffInfoService {
    @Autowired
    private findRepository findRepository;

    @Autowired
    private commentRepository commentRepository;

    @Autowired
    private replyRepository replyRepository;

    /**
     * 查找某一段日期的订单，同时考虑订单状态和员工ID
     * @param startDate 开始日期，格式为"2023-1-1"
     * @param endDate 结束日期
     * @param status 订单状态
     * @param staffId 员工ID
     * @return 订单列表
     */
    public List<staffOrdersInfoModel> findByCreateDatesBetween(String startDate, String endDate, String status, String staffId){
        return findRepository.findByCreateDateBetweenAndStatusAndStaffId(startDate, endDate, status, staffId);
    }

    /**
     * 获取某一日的订单，同时考虑订单状态和员工ID
     * @param date 日期，格式为"2023-1-1"
     * @param status 订单状态
     * @param staffId 员工ID
     * @return 订单列表
     */
    public List<staffOrdersInfoModel> findByCreateDates(String date, String status, String staffId){
        return findRepository.findByCreateDateAndStatusAndStaffId(date, status, staffId);
    }

    /**
     * 根据订单状态查找订单数量，用于获取已支付订单数和未支付订单数
     * @param status 订单状态
     * @return 订单数量
     */
    public int countNumberByStatus(String status){
        List<staffOrdersInfoModel> orders = findRepository.findByStatus(status);
        return orders.size();
    }

    /**
     * 查询当前月份的指定状态（3和4）的订单，并计算这些订单的总金额
     * @param staffId 员工ID
     * @return 总金额
     */
    public double findCurrentMonthOrdersTotalByStatusAndStaffId(String staffId) {
        LocalDate now = LocalDate.now();
        String startDate = now.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = now.withDayOfMonth(now.lengthOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<staffOrdersInfoModel> orders = findRepository.findByCreateDateBetweenAndStatusAndStaffId(startDate, endDate, "3", staffId);
        orders.addAll(findRepository.findByCreateDateBetweenAndStatusAndStaffId(startDate, endDate, "4", staffId));

        return orders.stream().mapToDouble(staffOrdersInfoModel::getMoney).sum();
    }

    /**
     * 查询给定日期和员工ID的订单，状态为3和4，计算总金额
     * @param date 指定的日期，格式为"2023-04-01"
     * @param staffId 员工ID
     * @return 总金额
     */
    public double findTodayOrdersTotalByStatusAndStaffId(String date, String staffId) {
        List<staffOrdersInfoModel> ordersStatus3 = findRepository.findByCreateDateAndStatusAndStaffId(date, "3", staffId);
        List<staffOrdersInfoModel> ordersStatus4 = findRepository.findByCreateDateAndStatusAndStaffId(date, "4", staffId);

        double totalAmount = 0;
        totalAmount += ordersStatus3.stream().mapToDouble(staffOrdersInfoModel::getMoney).sum();
        totalAmount += ordersStatus4.stream().mapToDouble(staffOrdersInfoModel::getMoney).sum();
        return totalAmount;
    }

    //@TODO
    /**
     * 统计评论中好中坏数量
     * @param staffId
     * @param question
     * @return
     */
    public Map<String, Long> countAnswersByStaffIdAndQuestion(String staffId, String question) {
        // 从数据库获取所有相关评论
        List<staffCommentModel> comments = commentRepository.findByStaffIdAndQuestion(staffId, question);

        // 对答案进行统计
        Map<String, Long> answerCounts = comments.stream()
                .map(staffCommentModel::getAnswers)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return answerCounts;
    }

    /**
     * 查询某时间段内所有评价
     * @param staffId
     * @param startDateStr
     * @param endDateStr
     * @return
     * @throws Exception
     */
    public List<staffReplyModel> findRepliesByStaffIdAndDateRange(int staffId, String startDateStr, String endDateStr) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);

        return replyRepository.findByStaffIdAndCreatetimeBetween(staffId, startDate, endDate);
    }
}
