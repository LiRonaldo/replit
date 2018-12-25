package com.yuxiang.li.reptile.common;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by yuxiang.li on 2018/12/4.
 * 判断规则
 */
@Component
public class JudgeRule {

    @Autowired
    private MailServiceUtils mailServiceUtils;


    public boolean handle(int ruleType, int j, Elements tr) {
        if (ruleType == Constant.SIX_NUM) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int k = 0; k < 6; k++) {
                int result = Integer.parseInt(tr.get(j - k).getElementsByTag("td").get(2).text().substring(8));
                if (map.get(result) == null) {
                    map.put(result, 1);
                } else {
                    int count = map.get(result);
                    map.put(result, ++count);
                }
            }
            int sum1 = 0;
            int sum2 = 0;
            if (map.get(0) != null) {
                sum1 += map.get(0);
            }
            if (map.get(3) != null) {
                sum1 += map.get(3);
            }
            if (map.get(6) != null) {
                sum1 += map.get(6);
            }
            if (map.get(9) != null) {
                sum1 += map.get(9);
            }
            if (sum1 == 6) {
                mailServiceUtils.sendEmail(Constant.TO_USER, Constant.SIX_0369);
                System.out.println("--------4个数0369发送邮件----------");
            }

            if (map.get(1) != null) {
                sum2 += map.get(1);
            }
            if (map.get(3) != null) {
                sum2 += map.get(3);
            }
            if (map.get(4) != null) {
                sum2 += map.get(4);
            }
            if (map.get(7) != null) {
                sum2 += map.get(7);
            }
            if (sum2 == 6) {
                mailServiceUtils.sendEmail(Constant.TO_USER, Constant.SIX_1347);
                System.out.println("--------4个数1347发送邮件----------");
            }
            if (j >= 9) {
                HashMap<Integer, Integer> map2 = new HashMap<>();
                for (int k = 0; k < 10; k++) {
                    int result = Integer.parseInt(tr.get(j - k).getElementsByTag("td").get(2).text().substring(8));
                    if (map2.get(result) == null) {
                        map2.put(result, 1);
                    } else {
                        int count = map2.get(result);
                        map2.put(result, ++count);
                    }
                }
                if (map2.size() <= 4) {
                    mailServiceUtils.sendEmail(Constant.TO_USER, Constant.SIX);
                    System.out.println("--------4个数发送邮件----------");
                }
            }
        } else {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int k = 0; k < 12; k++) {
                int result = Integer.parseInt(tr.get(j - k).getElementsByTag("td").get(2).text().substring(8));
                if (map.get(result) == null) {
                    map.put(result, 1);
                } else {
                    int count = map.get(result);
                    map.put(result, ++count);
                }
            }
            //5个数重复
            if (map.size() <= 5) {
                mailServiceUtils.sendEmail(Constant.TO_USER, Constant.FIVE_NUM_CHANGE);
                System.out.println("--------5个数发送邮件----------");
            }
        }
        return true;

    }
}
