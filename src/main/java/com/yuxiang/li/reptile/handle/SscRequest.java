package com.yuxiang.li.reptile.handle;

import com.yuxiang.li.reptile.common.Constant;
import com.yuxiang.li.reptile.common.JudgeRule;
import com.yuxiang.li.reptile.common.MailServiceUtils;
import com.yuxiang.li.reptile.common.TimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by yuxiang.li on 2018/12/3.
 * 请求时时彩网址
 */
@Component
public class SscRequest {
    @Autowired
    private MailServiceUtils mailServiceUtils;
    @Autowired
    private JudgeRule judgeRule;
    @Value("${request.address}")
    private String address;
    @Autowired
    private TimeUtils timeUtils;



    @Scheduled(cron = "0 4,14,24,34,44,54 10-21 * * ?")
    public void handle() {
        System.out.println("-----------10点之前------------");
        try {
            int i;
            Document doc = Jsoup.connect(address).timeout(5000).get();
            Elements tbodys = doc.getElementsByTag("tbody");
            Element tr1 = tbodys.get(0);
            Element totle = tr1.append(tbodys.get(1).toString());
            Elements totleTr = totle.getElementsByTag("tr");
            for (i = 0; i < 120; i++) {
                if ("".equals(totleTr.get(i).getElementsByTag("td").get(0).text())) {
                    break;
                }
            }
                //6个数
                if (i > 5 && i < 12) {
                    int j = i - 1;
                    String lotteryDate = totleTr.get(j).getElementsByTag("td").get(1).text();
                    if (timeUtils.judgeTime(Constant.TEN_MINUTE_TIMES, lotteryDate)) {
                        judgeRule.handle(Constant.SIX_NUM, j, totleTr);
                    }
                } else if (i >=12) {
                    int j = i - 1;
                    String lotteryDate = totleTr.get(j).getElementsByTag("td").get(1).text();
                    if (timeUtils.judgeTime(Constant.TEN_MINUTE_TIMES, lotteryDate)) {
                        judgeRule.handle(Constant.FIVE_NUM, j, totleTr);
                    }
                    if (timeUtils.judgeTime(Constant.TEN_MINUTE_TIMES, lotteryDate)) {
                        judgeRule.handle(Constant.SIX_NUM, j, totleTr);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Scheduled(cron = "0 0/1 22-23,0-1 * * ?")
    public void handleFive() {
        System.out.println("-----------10点之后-------------");
        try {
            int i;
            Document doc = Jsoup.connect(address).timeout(5000).get();
            Elements tbodys = doc.getElementsByTag("tbody");
            Element tr1 = tbodys.get(0);
            Element totle = tr1.append(tbodys.get(1).toString());
            Elements totleTr = totle.getElementsByTag("tr");
            for (i = 0; i < 119; i++) {
                if ("".equals(totleTr.get(i).getElementsByTag("td").get(0).text())) {
                    break;
                }
            }
                //6个数
                if (i > 5 && i < 12) {
                    int j = i - 1;
                    String lotteryDate = totleTr.get(j).getElementsByTag("td").get(1).text();
                    if (timeUtils.judgeTime(Constant.FIVE_MINUTE_TIMES, lotteryDate)) {
                        judgeRule.handle(Constant.SIX_NUM, j, totleTr);
                    }
                } else if (i >=12) {
                    int j = i - 1;
                    String lotteryDate = totleTr.get(j).getElementsByTag("td").get(1).text();
                    if (timeUtils.judgeTime(Constant.FIVE_MINUTE_TIMES, lotteryDate)) {
                        judgeRule.handle(Constant.FIVE_NUM, j, totleTr);
                    }
                    if (timeUtils.judgeTime(Constant.FIVE_MINUTE_TIMES, lotteryDate)) {
                        judgeRule.handle(Constant.SIX_NUM, j, totleTr);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
