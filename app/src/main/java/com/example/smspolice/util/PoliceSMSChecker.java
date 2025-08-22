package com.example.smspolice.util;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PoliceSMSChecker {
    
    private static final String TAG = "PoliceSMSChecker";
    
    // 交警相关关键词
    private static final List<String> POLICE_KEYWORDS = Arrays.asList(
            "交警", "交通", "违章", "超速", "闯红灯", "停车", "驾驶证", "行驶证",
            "扣分", "罚款", "违法", "事故", "处理", "通知", "提醒", "警告",
            "限行", "限号", "禁行", "单行道", "公交车道", "应急车道"
    );
    
    // 交警部门常见号码前缀
    private static final List<String> POLICE_NUMBER_PREFIXES = Arrays.asList(
            "12123", "122", "110", "10086", "10000", "10010"
    );
    
    // 交警部门常见号码模式
    private static final List<Pattern> POLICE_NUMBER_PATTERNS = Arrays.asList(
            Pattern.compile("^1[0-9]{10}$"), // 11位手机号
            Pattern.compile("^[0-9]{5,6}$"), // 5-6位短号
            Pattern.compile("^[0-9]{3,4}$")  // 3-4位特服号
    );
    
    /**
     * 检查是否为交警短信
     * @param sender 发送者号码
     * @param messageBody 短信内容
     * @return true表示是交警短信
     */
    public static boolean isPoliceSMS(String sender, String messageBody) {
        if (sender == null || messageBody == null) {
            return false;
        }
        
        Log.d(TAG, "检查短信 - 发送者: " + sender + ", 内容: " + messageBody);
        
        // 检查发送者号码
        boolean isPoliceNumber = isPoliceNumber(sender);
        
        // 检查短信内容关键词
        boolean hasPoliceKeywords = hasPoliceKeywords(messageBody);
        
        // 如果号码是交警号码或内容包含交警关键词，则认为是交警短信
        boolean isPoliceSMS = isPoliceNumber || hasPoliceKeywords;
        
        Log.d(TAG, "检查结果 - 交警号码: " + isPoliceNumber + 
                ", 交警关键词: " + hasPoliceKeywords + 
                ", 最终结果: " + isPoliceSMS);
        
        return isPoliceSMS;
    }
    
    /**
     * 检查发送者号码是否为交警号码
     */
    private static boolean isPoliceNumber(String sender) {
        // 移除所有非数字字符
        String cleanNumber = sender.replaceAll("[^0-9]", "");
        
        // 检查是否为交警部门常见号码前缀
        for (String prefix : POLICE_NUMBER_PREFIXES) {
            if (cleanNumber.startsWith(prefix)) {
                return true;
            }
        }
        
        // 检查号码模式
        for (Pattern pattern : POLICE_NUMBER_PATTERNS) {
            if (pattern.matcher(cleanNumber).matches()) {
                // 进一步检查是否为交警相关号码
                if (isLikelyPoliceNumber(cleanNumber)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * 进一步判断号码是否可能是交警号码
     */
    private static boolean isLikelyPoliceNumber(String number) {
        // 这里可以添加更多的判断逻辑
        // 比如检查号码是否在已知的交警号码数据库中
        
        // 简单判断：如果号码以特定数字开头，可能是交警号码
        return number.startsWith("12") || number.startsWith("11") || 
               number.startsWith("10") || number.startsWith("12");
    }
    
    /**
     * 检查短信内容是否包含交警相关关键词
     */
    private static boolean hasPoliceKeywords(String messageBody) {
        String lowerMessage = messageBody.toLowerCase();
        
        for (String keyword : POLICE_KEYWORDS) {
            if (lowerMessage.contains(keyword.toLowerCase())) {
                Log.d(TAG, "发现交警关键词: " + keyword);
                return true;
            }
        }
        
        return false;
    }
} 