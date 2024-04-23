package com.example.shishangsujian.staffInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class staffInfoController {

    @Autowired
    private staffInfoService staffInfoService;

    /**
     * 查询指定日期范围内的订单，包括订单状态和员工ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param status 订单状态
     * @param staffId 员工ID
     * @return 订单列表
     */
    @GetMapping("/staffInfo/dates")
    public List<staffOrdersInfoModel> findByDates(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String status, @RequestParam String staffId) {
        return staffInfoService.findByCreateDatesBetween(startDate, endDate, status, staffId);
    }

    /**
     * 查询某一天的订单，包括订单状态和员工ID
     * @param date 日期
     * @param status 订单状态
     * @param staffId 员工ID
     * @return 订单列表
     */
    @GetMapping("/staffInfo/date")
    public List<staffOrdersInfoModel> findByDate(@RequestParam String date, @RequestParam String status, @RequestParam String staffId) {
        return staffInfoService.findByCreateDates(date, status, staffId);
    }

    /**
     * 根据订单状态查询订单数量
     * @param status 订单状态
     * @return 订单数量
     */
    @GetMapping("/staffInfo/count")
    public int countByStatus(@RequestParam String status) {
        return staffInfoService.countNumberByStatus(status);
    }

    /**
     * 查询当前月份，特定员工的状态为3和4的订单总金额
     * @param staffId 员工ID
     * @return 总金额
     */
    @GetMapping("/staffInfo/monthlyTotal")
    public double getCurrentMonthOrdersTotalByStatusAndStaffId(@RequestParam String staffId) {
        return staffInfoService.findCurrentMonthOrdersTotalByStatusAndStaffId(staffId);
    }

    /**
     * 查询给定日期和员工ID的状态为3和4的订单总金额
     * @param date 指定的日期，格式应为"yyyy-MM-dd"，如"2023-04-01"
     * @param staffId 员工ID
     * @return 总金额
     */
    @GetMapping("/staffInfo/todayTotal")
    public double getTodayOrdersTotalByStatusAndStaffId(@RequestParam String date, @RequestParam String staffId) {
        return staffInfoService.findTodayOrdersTotalByStatusAndStaffId(date, staffId);
    }

    /**
     * 查询好中坏
     * @param staffId
     * @param question
     * @return
     */
    @GetMapping("/staffInfo/skillCount")
    public Map<String, Long> commentCount(@RequestParam String staffId,@RequestParam String question){
        return staffInfoService.countAnswersByStaffIdAndQuestion(staffId,question);
    }

    /**
     * 统计一段时间内的评论
     * @param staffId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/staffInfo/comments")
    public List<staffReplyModel> getComments(@RequestParam int staffId, @RequestParam String startDate, @RequestParam String endDate) {
        try {
            // 尝试获取评论
            List<staffReplyModel> replies = staffInfoService.findRepliesByStaffIdAndDateRange(staffId, startDate, endDate);
            return replies;
        } catch (Exception e) {
            // 如果发生异常，比如日期解析异常，返回一个空列表
            return Collections.emptyList();
        }
    }
}
