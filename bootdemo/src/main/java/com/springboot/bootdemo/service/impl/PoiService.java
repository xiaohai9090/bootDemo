package com.springboot.bootdemo.service.impl;

import com.springboot.bootdemo.dao.PayHistoryDao;
import com.springboot.bootdemo.domain.PayHistory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiaohai
 * @date: 2021/1/11 17:40
 * @name: PoiService.class
 */
@Service
public class PoiService {

    @Resource
    private PayHistoryDao payHistoryDao;

    public void exportDateExcel(HttpServletResponse response){
        System.out.println(22222);
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        Row row = sh.createRow(0);

        Cell cell0 = row.createCell(0);
        Cell cell1 = row.createCell(1);
        Cell cell2 = row.createCell(2);
        Cell cell3 = row.createCell(3);
        Cell cell4 = row.createCell(4);
        Cell cell5 = row.createCell(5);
        Cell cell6 = row.createCell(6);
        Cell cell7 = row.createCell(7);
        Cell cell8 = row.createCell(8);
        Cell cell9 = row.createCell(9);
        cell0.setCellValue("充值记录ID");
        cell1.setCellValue("玩家ID");
        cell2.setCellValue("代充玩家ID");
        cell3.setCellValue("充值时间");
        cell4.setCellValue("订单号");
        cell5.setCellValue("充值道具");
        cell6.setCellValue("充值金额");
        cell7.setCellValue("充值方式");
        cell8.setCellValue("订单状态");
        cell9.setCellValue("苹果交易ID");

//        payHistoryDao.showPayHistory()
        int pageSize = 10000;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startTime","2020-01-01 00:00:00");
        paramMap.put("endTime","2020-01-31 23:59:59");
        int count = payHistoryDao.payHistoryCount(paramMap);
        int pageNum = count % pageSize > 0 ? count / pageSize + 1 : count / pageSize ;

        for (int i = 0; i < pageNum; i++){

            paramMap.put("start",i * pageSize);
            paramMap.put("size",pageSize);

            List<PayHistory> list = payHistoryDao.showPayHistory(paramMap);
            int len = list.size() < pageSize ? list.size() : pageSize;
            for (int j = 0; j < len; j++){
                PayHistory payHistory = list.get(j);
                Row rowValue = sh.createRow(i * pageSize + 1 + j);
                Cell cell11 = rowValue.createCell(0);
                Cell cell12 = rowValue.createCell(1);
                Cell cell13 = rowValue.createCell(2);
                Cell cell14 = rowValue.createCell(3);
                Cell cell15 = rowValue.createCell(4);
                Cell cell16 = rowValue.createCell(5);
                Cell cell17 = rowValue.createCell(6);
                Cell cell18 = rowValue.createCell(7);
                Cell cell19 = rowValue.createCell(8);
                Cell cell20 = rowValue.createCell(9);
                cell11.setCellValue(payHistory.getPayHistoryId());
                cell12.setCellValue(payHistory.getPlayerIndex());
                cell13.setCellValue(payHistory.getDaiChongPlayerIndex());
                cell14.setCellValue(payHistory.getPayTime());
                cell15.setCellValue(payHistory.getOrderNo());
                cell16.setCellValue(payHistory.getBuyItemID());
                cell17.setCellValue(payHistory.getAmount());
                cell18.setCellValue(payHistory.getFlagAccess());
                cell19.setCellValue(payHistory.getState());
                cell20.setCellValue(payHistory.getTransaction_id());
            }

            list.clear();
        }

        try {
            String fileName = "payhistory.xlsx";

            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));

            wb.write(response.getOutputStream());
            wb.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
